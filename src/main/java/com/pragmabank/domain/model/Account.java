package com.pragmabank.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private final UUID id;
    private final String accountNumber;
    private final UUID customerId;
    private BigDecimal balance;
    private final AccountStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String lastTransactionId;

    public enum AccountStatus {
        ACTIVE, FROZEN, CLOSED
    }

    public Account(@NotNull UUID id,
                  @NotNull @Size(min = 10, max = 20) String accountNumber,
                  @NotNull UUID customerId,
                  @PositiveOrZero BigDecimal initialBalance,
                  @NotNull AccountStatus status) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.lastTransactionId = null;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getLastTransactionId() {
        return lastTransactionId;
    }

    public void deposit(@PositiveOrZero BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void withdraw(@PositiveOrZero BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (this.status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void updateLastTransactionId(String transactionId) {
        this.lastTransactionId = transactionId;
        this.updatedAt = LocalDateTime.now();
    }

    public void freezeAccount() {
        if (this.status == AccountStatus.CLOSED) {
            throw new IllegalStateException("Cannot freeze a closed account");
        }
        this.status = AccountStatus.FROZEN;
        this.updatedAt = LocalDateTime.now();
    }

    public void closeAccount() {
        if (this.status == AccountStatus.CLOSED) {
            throw new IllegalStateException("Account is already closed");
        }
        if (this.balance.compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("Cannot close account with positive balance");
        }
        this.status = AccountStatus.CLOSED;
        this.updatedAt = LocalDateTime.now();
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}