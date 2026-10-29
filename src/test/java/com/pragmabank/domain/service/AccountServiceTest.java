package com.pragmabank.domain.service;


import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.port.AccountRepository;
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
@DisplayName("Pruebas unitarias para AccountService")
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account cuentaPrueba;
    private UUID customerId;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        cuentaPrueba = new Account(
            UUID.randomUUID(),
            "ACC-2024-001",
            customerId,
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
    }

    @Test
    @DisplayName("Crear cuenta - debe persistir y retornar cuenta con ID asignado")
    void crearCuenta_debePersistirYRetornarCuenta() {
        when(accountRepository.save(any(Account.class))).thenReturn(cuentaPrueba);

        Account resultado = accountService.crearCuenta(customerId, "ACC-2024-001", new BigDecimal("1000.00"));

        assertNotNull(resultado);
        assertEquals("ACC-2024-001", resultado.getAccountNumber());
        assertEquals(customerId, resultado.getCustomerId());
        assertEquals(AccountStatus.ACTIVE, resultado.getStatus());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("Buscar cuenta por ID - debe retornar cuenta cuando existe")
    void buscarPorId_debeRetornarCuentaCuandoExiste() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));

        Optional<Account> resultado = accountService.buscarPorId(cuentaPrueba.getId());

        assertTrue(resultado.isPresent());
        assertEquals(cuentaPrueba.getId(), resultado.get().getId());
    }

    @Test
    @DisplayName("Buscar cuenta por ID - debe retornar vacío cuando no existe")
    void buscarPorId_debeRetornarVacioCuandoNoExiste() {
        UUID idInexistente = UUID.randomUUID();
        when(accountRepository.findById(idInexistente)).thenReturn(Optional.empty());

        Optional<Account> resultado = accountService.buscarPorId(idInexistente);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Buscar cuenta por número - debe retornar cuenta cuando existe")
    void buscarPorNumero_debeRetornarCuentaCuandoExiste() {
        when(accountRepository.findByAccountNumber("ACC-2024-001")).thenReturn(Optional.of(cuentaPrueba));

        Optional<Account> resultado = accountService.buscarPorNumero("ACC-2024-001");

        assertTrue(resultado.isPresent());
        assertEquals("ACC-2024-001", resultado.get().getAccountNumber());
    }

    @Test
    @DisplayName("Depositar - debe incrementar balance cuando cuenta está activa")
    void depositar_debeIncrementarBalanceCuandoCuentaActiva() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.depositar(cuentaPrueba.getId(), new BigDecimal("500.00"));

        assertEquals(new BigDecimal("1500.00"), resultado.getBalance());
    }

    @Test
    @DisplayName("Depositar - debe lanzar excepción cuando cantidad es negativa")
    void depositar_debeLanzarExcepcionCuandoCantidadNegativa() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));

        assertThrows(IllegalArgumentException.class, () -> 
            accountService.depositar(cuentaPrueba.getId(), new BigDecimal("-100.00"))
        );
    }

    @Test
    @DisplayName("Retirar - debe decrementar balance cuando hay fondos suficientes")
    void retirar_debeDecrementarBalanceCuandoHayFondosSuficientes() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.retirar(cuentaPrueba.getId(), new BigDecimal("300.00"));

        assertEquals(new BigDecimal("700.00"), resultado.getBalance());
    }

    @Test
    @DisplayName("Retirar - debe lanzar excepción cuando fondos insuficientes")
    void retirar_debeLanzarExcepcionCuandoFondosInsuficientes() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));

        assertThrows(Account.InsufficientFundsException.class, () -> 
            accountService.retirar(cuentaPrueba.getId(), new BigDecimal("5000.00"))
        );
    }

    @Test
    @DisplayName("Freezar cuenta - debe cambiar estado a FROZEN")
    void freezeAccount_debeCambiarEstadoAFrozen() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.freezeAccount(cuentaPrueba.getId());

        assertEquals(AccountStatus.FROZEN, resultado.getStatus());
    }

    @Test
    @DisplayName("Cerrar cuenta - debe cambiar estado a CLOSED")
    void closeAccount_debeCambiarEstadoAClosed() {
        when(accountRepository.findById(cuentaPrueba.getId())).thenReturn(Optional.of(cuentaPrueba));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Account resultado = accountService.closeAccount(cuentaPrueba.getId());

        assertEquals(AccountStatus.CLOSED, resultado.getStatus());
    }

    @Test
    @DisplayName("Buscar cuentas por cliente - debe retornar lista de cuentas del cliente")
    void buscarPorCliente_debeRetornarListaDeCuentas() {
        when(accountRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cuentaPrueba));

        Optional<Account> resultado = accountService.buscarPorCliente(customerId);

        assertTrue(resultado.isPresent());
        assertEquals(customerId, resultado.get().getCustomerId());
    }
}