package com.pragmabank.infrastructure.controller;





import com.pragmabank.application.usecase.TransferFailedException;
import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.application.usecase.AccountInactiveException;
import com.pragmabank.application.usecase.AccountNotFoundException;
import com.pragmabank.application.usecase.TransferRequest;
import com.pragmabank.application.usecase.TransferResponse;
import com.pragmabank.application.usecase.TransferUseCase;
import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.model.Transaction.TransactionStatus;
import com.pragmabank.domain.model.Transaction.TransactionType;
import com.pragmabank.domain.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransferUseCase transferUseCase;

    public TransactionController(TransactionService transactionService, TransferUseCase transferUseCase) {
        this.transactionService = transactionService;
        this.transferUseCase = transferUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable UUID id) {
        return transactionService.findById(id)
                .map(transaction -> ResponseEntity.ok(TransactionResponse.fromDomain(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{transactionNumber}")
    public ResponseEntity<TransactionResponse> getTransactionByNumber(@PathVariable String transactionNumber) {
        return transactionService.findByTransactionNumber(transactionNumber)
                .map(transaction -> ResponseEntity.ok(TransactionResponse.fromDomain(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccount(
            @PathVariable UUID accountId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {
        List<Transaction> transactions = transactionService.findByAccountId(accountId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/source/{sourceAccountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsBySourceAccount(@PathVariable UUID sourceAccountId) {
        List<Transaction> transactions = transactionService.findBySourceAccountId(sourceAccountId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/target/{targetAccountId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByTargetAccount(@PathVariable UUID targetAccountId) {
        List<Transaction> transactions = transactionService.findByTargetAccountId(targetAccountId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByType(@PathVariable TransactionType type) {
        List<Transaction> transactions = transactionService.findByType(type);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByStatus(@PathVariable TransactionStatus status) {
        List<Transaction> transactions = transactionService.findByStatus(status);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest request) {
        try {
            TransferResponse response = transferUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (TransferUseCase.AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (TransferUseCase.AccountInactiveException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (TransferUseCase.InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (TransferUseCase.TransferFailedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> createDeposit(@Valid @RequestBody CreateTransactionRequest request) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                generateTransactionNumber(),
                null,
                request.accountId(),
                request.amount(),
                TransactionType.DEPOSIT,
                java.time.LocalDateTime.now(),
                TransactionStatus.COMPLETED
        );
        Transaction created = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.fromDomain(created));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionResponse> createWithdrawal(@Valid @RequestBody CreateTransactionRequest request) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                generateTransactionNumber(),
                request.accountId(),
                null,
                request.amount(),
                TransactionType.WITHDRAWAL,
                java.time.LocalDateTime.now(),
                TransactionStatus.COMPLETED
        );
        Transaction created = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.fromDomain(created));
    }

    private String generateTransactionNumber() {
        return "TXN-" + System.currentTimeMillis() + "-" + 
               UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public record CreateTransactionRequest(
            @NotNull(message = "El ID de la cuenta es obligatorio")
            UUID accountId,

            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record TransactionResponse(
            UUID id,
            String transactionNumber,
            UUID sourceAccountId,
            UUID targetAccountId,
            BigDecimal amount,
            String type,
            String timestamp,
            String status,
            String failureReason
    ) {
        public static TransactionResponse fromDomain(Transaction transaction) {
            return new TransactionResponse(
                    transaction.getId(),
                    transaction.getTransactionNumber(),
                    transaction.getSourceAccountId(),
                    transaction.getTargetAccountId(),
                    transaction.getAmount(),
                    transaction.getType().name(),
                    transaction.getTimestamp() != null ? transaction.getTimestamp().toString() : null,
                    transaction.getStatus().name(),
                    transaction.getFailureReason()
            );
        }
    }
}