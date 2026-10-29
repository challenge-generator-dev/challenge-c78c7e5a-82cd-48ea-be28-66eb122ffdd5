package com.pragmabank.domain.service;

import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.port.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio de dominio que encapsula la lógica de negocio para la gestión de transacciones bancarias.
 * Coordina la creación, validación y seguimiento de transacciones entre cuentas.
 */
public class TransactionService {
    
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private static final BigDecimal MIN_TRANSACTION_AMOUNT = BigDecimal.valueOf(0.01);
    private static final BigDecimal MAX_TRANSACTION_AMOUNT = BigDecimal.valueOf(50000.00);
    
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }
    
    /**
     * Crea una nueva transacción de depósito en una cuenta.
     * @param id identificador único de la transacción
     * @param transactionNumber número único de transacción
     * @param targetAccountId cuenta destino del depósito
     * @param amount monto a depositar
     * @return la transacción creada y completada
     */
    public Transaction createDeposit(UUID id, String transactionNumber, UUID targetAccountId, BigDecimal amount) {
        logger.info("Creando depósito de {} en cuenta {}", amount, targetAccountId);
        
        validateTransactionAmount(amount);
        validateAccountExists(targetAccountId, "cuenta destino");
        
        Transaction transaction = new Transaction(
            id,
            transactionNumber,
            null,
            targetAccountId,
            amount,
            TransactionType.DEPOSIT,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
        
        try {
            accountService.deposit(targetAccountId, amount);
            transaction.complete();
            logger.info("Depósito completado: {}", transactionNumber);
        } catch (Exception e) {
            logger.error("Error al procesar depósito: {}", e.getMessage());
            transaction.fail("Error al procesar depósito: " + e.getMessage());
        }
        
        return transactionRepository.save(transaction);
    }
    
    /**
     * Crea una nueva transacción de retiro de una cuenta.
     * @param id identificador único de la transacción
     * @param transactionNumber número único de transacción
     * @param sourceAccountId cuenta origen del retiro
     * @param amount monto a retirar
     * @return la transacción creada
     */
    public Transaction createWithdrawal(UUID id, String transactionNumber, UUID sourceAccountId, BigDecimal amount) {
        logger.info("Creando retiro de {} desde cuenta {}", amount, sourceAccountId);
        
        validateTransactionAmount(amount);
        validateAccountExists(sourceAccountId, "cuenta origen");
        
        Transaction transaction = new Transaction(
            id,
            transactionNumber,
            sourceAccountId,
            null,
            amount,
            TransactionType.WITHDRAWAL,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
        
        try {
            accountService.withdraw(sourceAccountId, amount);
            transaction.complete();
            logger.info("Retiro completado: {}", transactionNumber);
        } catch (Exception e) {
            logger.error("Error al procesar retiro: {}", e.getMessage());
            transaction.fail("Error al procesar retiro: " + e.getMessage());
        }
        
        return transactionRepository.save(transaction);
    }
    
    /**
     * Crea una transferencia entre dos cuentas.
     * @param id identificador único de la transacción
     * @param transactionNumber número único de transacción
     * @param sourceAccountId cuenta origen
     * @param targetAccountId cuenta destino
     * @param amount monto a transferir
     * @return la transacción creada
     */
    public Transaction createTransfer(UUID id, String transactionNumber, UUID sourceAccountId, 
                                       UUID targetAccountId, BigDecimal amount) {
        logger.info("Creando transferencia de {} desde {} hacia {}", amount, sourceAccountId, targetAccountId);
        
        validateTransactionAmount(amount);
        validateAccountExists(sourceAccountId, "cuenta origen");
        validateAccountExists(targetAccountId, "cuenta destino");
        
        if (sourceAccountId.equals(targetAccountId)) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }
        
        Transaction transaction = new Transaction(
            id,
            transactionNumber,
            sourceAccountId,
            targetAccountId,
            amount,
            TransactionType.TRANSFER,
            LocalDateTime.now(),
            TransactionStatus.PENDING,
            null
        );
        
        try {
            accountService.withdraw(sourceAccountId, amount);
            accountService.deposit(targetAccountId, amount);
            transaction.complete();
            logger.info("Transferencia completada: {}", transactionNumber);
        } catch (Exception e) {
            logger.error("Error al procesar transferencia: {}", e.getMessage());
            transaction.fail("Error al procesar transferencia: " + e.getMessage());
        }
        
        return transactionRepository.save(transaction);
    }
    
    /**
     * Obtiene una transacción por su identificador.
     * @param id identificador de la transacción
     * @return un Optional con la transacción si existe
     */
    public Optional<Transaction> getTransactionById(UUID id) {
        logger.debug("Buscando transacción por ID: {}", id);
        return transactionRepository.findById(id);
    }
    
    /**
     * Obtiene una transacción por su número.
     * @param transactionNumber número de transacción
     * @return un Optional con la transacción si existe
     */
    public Optional<Transaction> getTransactionByNumber(String transactionNumber) {
        logger.debug("Buscando transacción por número: {}", transactionNumber);
        return transactionRepository.findByTransactionNumber(transactionNumber);
    }
    
    /**
     * Obtiene el historial de transacciones de una cuenta.
     * @param accountId identificador de la cuenta
     * @return lista de transacciones de la cuenta
     */
    public List<Transaction> getAccountTransactionHistory(UUID accountId) {
        logger.debug("Obteniendo historial de cuenta: {}", accountId);
        return transactionRepository.findByAccountId(accountId);
    }
    
    /**
     * Obtiene las transacciones enviadas por una cuenta.
     * @param accountId identificador de la cuenta
     * @return lista de transacciones enviadas
     */
    public List<Transaction> getSentTransactions(UUID accountId) {
        logger.debug("Obteniendo transacciones enviadas desde: {}", accountId);
        return transactionRepository.findBySourceAccountId(accountId);
    }
    
    /**
     * Obtiene las transacciones recibidas por una cuenta.
     * @param accountId identificador de la cuenta
     * @return lista de transacciones recibidas
     */
    public List<Transaction> getReceivedTransactions(UUID accountId) {
        logger.debug("Obteniendo transacciones recibidas en: {}", accountId);
        return transactionRepository.findByTargetAccountId(accountId);
    }
    
    /**
     * Obtiene transacciones dentro de un rango de fechas.
     * @param startDate fecha de inicio
     * @param endDate fecha de fin
     * @return lista de transacciones en el rango
     */
    public List<Transaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Buscando transacciones entre {} y {}", startDate, endDate);
        return transactionRepository.findByTimestampBetween(startDate, endDate);
    }
    
    /**
     * Calcula el flujo neto de una cuenta en un período.
     * @param accountId identificador de la cuenta
     * @param startDate inicio del período
     * @param endDate fin del período
     * @return flujo neto (entradas - salidas)
     */
    public BigDecimal calculateNetFlow(UUID accountId, LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Calculando flujo neto para cuenta {} entre {} y {}", accountId, startDate, endDate);
        
        BigDecimal incoming = transactionRepository.sumAmountsByTargetAccountIdInDateRange(
            accountId, startDate, endDate);
        BigDecimal outgoing = transactionRepository.sumAmountsBySourceAccountIdInDateRange(
            accountId, startDate, endDate);
        
        return incoming.subtract(outgoing);
    }
    
    /**
     * Valida que el monto de la transacción esté dentro de los límites permitidos.
     * @param amount monto a validar
     * @throws IllegalArgumentException si el monto es inválido
     */
    private void validateTransactionAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("El monto de la transacción no puede ser nulo");
        }
        
        if (amount.compareTo(MIN_TRANSACTION_AMOUNT) < 0) {
            throw new IllegalArgumentException(
                "El monto mínimo de transacción es " + MIN_TRANSACTION_AMOUNT
            );
        }
        
        if (amount.compareTo(MAX_TRANSACTION_AMOUNT) > 0) {
            throw new IllegalArgumentException(
                "El monto máximo de transacción es " + MAX_TRANSACTION_AMOUNT
            );
        }
    }
    
    /**
     * Valida que una cuenta exista.
     * @param accountId identificador de la cuenta
     * @param accountType descripción del tipo de cuenta para el mensaje de error
     * @throws IllegalArgumentException si la cuenta no existe
     */
    private void validateAccountExists(UUID accountId, String accountType) {
        if (!accountService.getAccountById(accountId).isPresent()) {
            throw new IllegalArgumentException(
                accountType + " no encontrada: " + accountId
            );
        }
    }
}