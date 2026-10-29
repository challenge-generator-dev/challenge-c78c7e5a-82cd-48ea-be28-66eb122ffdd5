package com.pragmabank.application.usecase;

import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.service.AccountService;
import com.pragmabank.domain.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransferUseCase {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransferUseCase(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public TransferResponse execute(@Valid TransferRequest request) {
        Account sourceAccount = accountService.findById(request.sourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta origen no encontrada"));

        Account targetAccount = accountService.findById(request.targetAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Cuenta destino no encontrada"));

        if (sourceAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("La cuenta origen no está activa");
        }

        if (targetAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("La cuenta destino no está activa");
        }

        if (sourceAccount.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientFundsException("Fondos insuficientes en la cuenta origen");
        }

        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                generateTransactionNumber(),
                sourceAccount.getId(),
                targetAccount.getId(),
                request.amount(),
                TransactionType.TRANSFER,
                LocalDateTime.now(),
                TransactionStatus.PENDING
        );

        try {
            accountService.withdraw(sourceAccount.getId(), request.amount());
            accountService.deposit(targetAccount.getId(), request.amount());
            transaction.complete();
            transaction = transactionService.save(transaction);
        } catch (Exception e) {
            transaction.fail("Error durante la transferencia: " + e.getMessage());
            transaction = transactionService.save(transaction);
            throw new TransferFailedException("La transferencia falló: " + e.getMessage());
        }

        return new TransferResponse(
                transaction.getId(),
                transaction.getTransactionNumber(),
                sourceAccount.getId(),
                targetAccount.getId(),
                request.amount(),
                TransactionStatus.COMPLETED.name()
        );
    }

    private String generateTransactionNumber() {
        return "TXN-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public record TransferRequest(
            @NotNull(message = "El ID de la cuenta origen es obligatorio")
            UUID sourceAccountId,

            @NotNull(message = "El ID de la cuenta destino es obligatorio")
            UUID targetAccountId,

            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record TransferResponse(
            UUID transactionId,
            String transactionNumber,
            UUID sourceAccountId,
            UUID targetAccountId,
            BigDecimal amount,
            String status
    ) {}

    public static class AccountNotFoundException extends RuntimeException {
        public AccountNotFoundException(String message) {
            super(message);
        }
    }

    public static class AccountInactiveException extends RuntimeException {
        public AccountInactiveException(String message) {
            super(message);
        }
    }

    public static class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    public static class TransferFailedException extends RuntimeException {
        public TransferFailedException(String message) {
            super(message);
        }
    }
}