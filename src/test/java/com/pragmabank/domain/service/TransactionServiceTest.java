package com.pragmabank.domain.service;


import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.port.AccountRepository;
import com.pragmabank.domain.port.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para TransactionService")
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account cuentaOrigen;
    private Account cuentaDestino;
    private Transaction transaccionPrueba;

    @BeforeEach
    void setUp() {
        UUID customerId = UUID.randomUUID();
        cuentaOrigen = new Account(
            UUID.randomUUID(),
            "ACC-ORIGEN-001",
            customerId,
            new BigDecimal("5000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
        cuentaDestino = new Account(
            UUID.randomUUID(),
            "ACC-DESTINO-001",
            UUID.randomUUID(),
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
        transaccionPrueba = new Transaction(
            UUID.randomUUID(),
            "TXN-2024-001",
            cuentaOrigen.getId(),
            cuentaDestino.getId(),
            new BigDecimal("500.00"),
            TransactionType.TRANSFER,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
    }

    @Test
    @DisplayName("Crear transacción - debe persistir y retornar transacción con estado PENDING")
    void crearTransaccion_debePersistirYRetornarTransaccion() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaccionPrueba);

        Transaction resultado = transactionService.crearTransaccion(
            cuentaOrigen.getId(),
            cuentaDestino.getId(),
            new BigDecimal("500.00"),
            TransactionType.TRANSFER
        );

        assertNotNull(resultado);
        assertEquals(TransactionStatus.PENDING, resultado.getStatus());
        assertEquals(TransactionType.TRANSFER, resultado.getType());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    @DisplayName("Ejecutar transferencia - debe completar transacción y actualizar saldos")
    void ejecutarTransferencia_debeCompletarTransaccionYActualizarSaldos() {
        when(accountRepository.findById(cuentaOrigen.getId())).thenReturn(Optional.of(cuentaOrigen));
        when(accountRepository.findById(cuentaDestino.getId())).thenReturn(Optional.of(cuentaDestino));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction resultado = transactionService.ejecutarTransferencia(
            cuentaOrigen.getId(),
            cuentaDestino.getId(),
            new BigDecimal("500.00")
        );

        assertEquals(TransactionStatus.COMPLETED, resultado.getStatus());
        assertEquals(new BigDecimal("4500.00"), cuentaOrigen.getBalance());
        assertEquals(new BigDecimal("1500.00"), cuentaDestino.getBalance());
    }

    @Test
    @DisplayName("Ejecutar transferencia - debe fallar cuando cuenta origen no existe")
    void ejecutarTransferencia_debeFallarCuandoCuentaOrigenNoExiste() {
        UUID idInexistente = UUID.randomUUID();
        when(accountRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> 
            transactionService.ejecutarTransferencia(idInexistente, cuentaDestino.getId(), new BigDecimal("500.00"))
        );
    }

    @Test
    @DisplayName("Ejecutar transferencia - debe fallar cuando fondos insuficientes")
    void ejecutarTransferencia_debeFallarCuandoFondosInsuficientes() {
        when(accountRepository.findById(cuentaOrigen.getId())).thenReturn(Optional.of(cuentaOrigen));
        when(accountRepository.findById(cuentaDestino.getId())).thenReturn(Optional.of(cuentaDestino));

        assertThrows(Account.InsufficientFundsException.class, () -> 
            transactionService.ejecutarTransferencia(
                cuentaOrigen.getId(),
                cuentaDestino.getId(),
                new BigDecimal("10000.00")
            )
        );
    }

    @Test
    @DisplayName("Completar transacción - debe cambiar estado a COMPLETED")
    void completarTransaccion_debeCambiarEstadoACompleted() {
        when(transactionRepository.findById(transaccionPrueba.getId())).thenReturn(Optional.of(transaccionPrueba));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction resultado = transactionService.completarTransaccion(transaccionPrueba.getId());

        assertEquals(TransactionStatus.COMPLETED, resultado.getStatus());
    }

    @Test
    @DisplayName("Fallar transacción - debe cambiar estado a FAILED y guardar razón")
    void failTransaccion_debeCambiarEstadoAFailedYGuardarRazon() {
        String razon = "Fondos insuficientes";
        when(transactionRepository.findById(transaccionPrueba.getId())).thenReturn(Optional.of(transaccionPrueba));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction resultado = transactionService.failTransaccion(transaccionPrueba.getId(), razon);

        assertEquals(TransactionStatus.FAILED, resultado.getStatus());
        assertEquals(razon, resultado.getFailureReason());
    }

    @Test
    @DisplayName("Buscar transacción por ID - debe retornar transacción cuando existe")
    void buscarPorId_debeRetornarTransaccionCuandoExiste() {
        when(transactionRepository.findById(transaccionPrueba.getId())).thenReturn(Optional.of(transaccionPrueba));

        Optional<Transaction> resultado = transactionService.buscarPorId(transaccionPrueba.getId());

        assertTrue(resultado.isPresent());
        assertEquals(transaccionPrueba.getId(), resultado.get().getId());
    }

    @Test
    @DisplayName("Buscar transacción por número - debe retornar transacción cuando existe")
    void buscarPorNumero_debeRetornarTransaccionCuandoExiste() {
        when(transactionRepository.findByTransactionNumber("TXN-2024-001")).thenReturn(Optional.of(transaccionPrueba));

        Optional<Transaction> resultado = transactionService.buscarPorNumero("TXN-2024-001");

        assertTrue(resultado.isPresent());
        assertEquals("TXN-2024-001", resultado.get().getTransactionNumber());
    }

    @Test
    @DisplayName("Es transferencia - debe retornar true cuando tipo es TRANSFER")
    void isTransfer_debeRetornarTrueCuandoTipoEsTransfer() {
        assertTrue(transaccionPrueba.isTransfer());
    }

    @Test
    @DisplayName("Es transferencia - debe retornar false cuando tipo no es TRANSFER")
    void isTransfer_debeRetornarFalseCuandoTipoNoEsTransfer() {
        Transaction deposito = new Transaction(
            UUID.randomUUID(),
            "TXN-2024-002",
            null,
            cuentaDestino.getId(),
            new BigDecimal("100.00"),
            TransactionType.DEPOSIT,
            LocalDateTime.now(),
            TransactionStatus.COMPLETED,
            null
        );

        assertFalse(deposito.isTransfer());
    }
}