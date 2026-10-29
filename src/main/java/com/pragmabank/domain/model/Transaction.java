package com.pragmabank.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final String transactionNumber;
    private final UUID sourceAccountId;
    private final UUID targetAccountId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDateTime timestamp;
    private final TransactionStatus status;
    private String failureReason;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED
    }

    public Transaction(@NotNull UUID id,
                      @NotNull String transactionNumber,
                      @NotNull UUID sourceAccountId,
                      UUID targetAccountId,
                      @NotNull @Positive BigDecimal amount,
                      @NotNull TransactionType type) {
        this.id = id;
        this.transactionNumber = transactionNumber;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = TransactionStatus.PENDING;
        this.failureReason = null;
    }

    public UUID getId() {
        return id;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public UUID getSourceAccountId() {
        return sourceAccountId;
    }

    public UUID getTargetAccountId() {
        return targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void complete() {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException("Transaction is not pending");
        }
        this.status = TransactionStatus.COMPLETED;
    }

    public void fail(String reason) {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException("Transaction is not pending");
        }
        this.status = TransactionStatus.FAILED;
        this.failureReason = reason;
    }

    public boolean isTransfer() {
        return this.type == TransactionType.TRANSFER && this.targetAccountId != null;
    }
}