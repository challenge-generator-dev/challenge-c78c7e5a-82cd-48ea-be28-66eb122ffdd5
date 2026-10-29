package com.pragmabank.domain.service;

import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.model.Account.InsufficientFundsException;
import com.pragmabank.domain.port.AccountRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio de dominio que encapsula la lógica de negocio para la gestión de cuentas bancarias.
 * Este servicio es parte del núcleo del dominio y no conoce detalles de infraestructura.
 */
public class AccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(100.00);
    private static final BigDecimal MAX_WITHDRAWAL_LIMIT = BigDecimal.valueOf(10000.00);
    
    private final AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    /**
     * Crea una nueva cuenta bancaria con el saldo inicial especificado.
     * @param id identificador único de la cuenta
     * @param accountNumber número de cuenta único
     * @param customerId identificador del cliente propietario
     * @param initialBalance saldo inicial de la cuenta
     * @return la cuenta creada
     * @throws IllegalArgumentException si el saldo inicial es menor al mínimo requerido
     */
    public Account createAccount(UUID id, String accountNumber, UUID customerId, BigDecimal initialBalance) {
        logger.info("Creando cuenta {} para cliente {}", accountNumber, customerId);
        
        if (initialBalance.compareTo(MINIMUM_BALANCE) < 0) {
            logger.warn("Intento de crear cuenta {} con saldo insuficiente: {}", accountNumber, initialBalance);
            throw new IllegalArgumentException(
                "El saldo inicial debe ser al menos " + MINIMUM_BALANCE + " para activar la cuenta"
            );
        }
        
        Account account = new Account(
            id,
            accountNumber,
            customerId,
            initialBalance,
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            LocalDateTime.now(),
            null
        );
        
        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta {} creada exitosamente con ID {}", accountNumber, savedAccount.getId());
        return savedAccount;
    }
    
    /**
     * Procesa un depósito en una cuenta existente.
     * @param accountId identificador de la cuenta
     * @param amount monto a depositar
     * @return la cuenta con el saldo actualizado
     * @throws IllegalArgumentException si el monto es cero o negativo
     */
    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Procesando depósito de {} en cuenta {}", amount, accountId);
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getStatus() != AccountStatus.ACTIVE) {
            logger.warn("Intento de depósito en cuenta {} con estado {}", accountId, account.getStatus());
            throw new IllegalStateException("No se puede depositar en una cuenta que no está activa");
        }
        
        account.deposit(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * Procesa un retiro de una cuenta existente verificando fondos suficientes.
     * @param accountId identificador de la cuenta
     * @param amount monto a retirar
     * @return la cuenta con el saldo actualizado
     * @throws IllegalArgumentException si el monto es cero o negativo
     * @throws InsufficientFundsException si no hay fondos suficientes
     */
    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Procesando retiro de {} en cuenta {}", amount, accountId);
        
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }
        
        if (amount.compareTo(MAX_WITHDRAWAL_LIMIT) > 0) {
            logger.warn("Intento de retiro que excede el límite: {} > {}", amount, MAX_WITHDRAWAL_LIMIT);
            throw new IllegalArgumentException(
                "El monto del retiro no puede exceder " + MAX_WITHDRAWAL_LIMIT + " por transacción"
            );
        }
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getStatus() != AccountStatus.ACTIVE) {
            logger.warn("Intento de retiro en cuenta {} con estado {}", accountId, account.getStatus());
            throw new IllegalStateException("No se puede retirar de una cuenta que no está activa");
        }
        
        BigDecimal projectedBalance = account.getBalance().subtract(amount);
        if (projectedBalance.compareTo(MINIMUM_BALANCE) < 0) {
            logger.warn("Fondos insuficientes en cuenta {}: saldo={}, retiro={}, mínimo={}",
                accountId, account.getBalance(), amount, MINIMUM_BALANCE);
            throw new InsufficientFundsException(
                "Saldo insuficiente. Saldo actual: " + account.getBalance() + 
                ", monto solicitado: " + amount + 
                ", saldo mínimo requerido: " + MINIMUM_BALANCE
            );
        }
        
        account.withdraw(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * Obtiene una cuenta por su identificador.
     * @param id identificador de la cuenta
     * @return un Optional con la cuenta si existe
     */
    public Optional<Account> getAccountById(UUID id) {
        logger.debug("Buscando cuenta por ID: {}", id);
        return accountRepository.findById(id);
    }
    
    /**
     * Obtiene una cuenta por su número de cuenta.
     * @param accountNumber número de cuenta
     * @return un Optional con la cuenta si existe
     */
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }
    
    /**
     * Obtiene todas las cuentas de un cliente.
     * @param customerId identificador del cliente
     * @return lista de cuentas del cliente
     */
    public List<Account> getAccountsByCustomerId(UUID customerId) {
        logger.debug("Buscando cuentas para cliente: {}", customerId);
        return accountRepository.findByCustomerId(customerId)
            .map(List::of)
            .orElse(List.of());
    }
    
    /**
     * Congela una cuenta, impidiendo operaciones de depósito y retiro.
     * @param accountId identificador de la cuenta
     * @return la cuenta congelada
     */
    public Account freezeAccount(UUID accountId) {
        logger.info("Congelando cuenta: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getStatus() == AccountStatus.CLOSED) {
            throw new IllegalStateException("No se puede congelar una cuenta cerrada");
        }
        
        account.freezeAccount();
        Account updatedAccount = accountRepository.save(account);
        logger.info("Cuenta {} congelada exitosamente", accountId);
        return updatedAccount;
    }
    
    /**
     * Cierra una cuenta permanentemente.
     * @param accountId identificador de la cuenta
     */
    public void closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta: {}", accountId);
        
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));
        
        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException(
                "No se puede cerrar una cuenta con saldo positivo. Saldo actual: " + account.getBalance()
            );
        }
        
        account.closeAccount();
        accountRepository.save(account);
        logger.info("Cuenta {} cerrada exitosamente", accountId);
    }
    
    /**
     * Verifica si una cuenta tiene saldo suficiente para una operación.
     * @param accountId identificador de la cuenta
     * @param amount monto a verificar
     * @return true si tiene fondos suficientes
     */
    public boolean hasSufficientFunds(UUID accountId, BigDecimal amount) {
        return accountRepository.findById(accountId)
            .map(account -> account.getBalance().subtract(amount).compareTo(MINIMUM_BALANCE) >= 0)
            .orElse(false);
    }
}