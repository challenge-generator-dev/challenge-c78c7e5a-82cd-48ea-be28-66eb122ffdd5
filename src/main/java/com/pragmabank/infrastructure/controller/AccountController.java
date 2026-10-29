package com.pragmabank.infrastructure.controller;


import com.pragmabank.domain.model.InsufficientFundsException;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable UUID id) {
        return accountService.findById(id)
                .map(account -> ResponseEntity.ok(AccountResponse.fromDomain(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountByNumber(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber)
                .map(account -> ResponseEntity.ok(AccountResponse.fromDomain(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponse>> getAccountsByCustomer(@PathVariable UUID customerId) {
        List<Account> accounts = accountService.findByCustomerId(customerId);
        List<AccountResponse> responses = accounts.stream()
                .map(AccountResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = new Account(
                UUID.randomUUID(),
                generateAccountNumber(),
                request.customerId(),
                request.initialBalance() != null ? request.initialBalance() : BigDecimal.ZERO,
                AccountStatus.ACTIVE,
                java.time.LocalDateTime.now(),
                null,
                null
        );
        Account created = accountService.save(account);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountResponse.fromDomain(created));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable UUID id,
            @Valid @RequestBody DepositRequest request) {
        return accountService.findById(id)
                .map(account -> {
                    account.deposit(request.amount());
                    Account updated = accountService.save(account);
                    return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable UUID id,
            @Valid @RequestBody WithdrawRequest request) {
        return accountService.findById(id)
                .map(account -> {
                    try {
                        account.withdraw(request.amount());
                        Account updated = accountService.save(account);
                        return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                    } catch (Account.InsufficientFundsException e) {
                        throw new RuntimeException("Fondos insuficientes", e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/freeze")
    public ResponseEntity<AccountResponse> freezeAccount(@PathVariable UUID id) {
        return accountService.findById(id)
                .map(account -> {
                    account.freezeAccount();
                    Account updated = accountService.save(account);
                    return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<AccountResponse> closeAccount(@PathVariable UUID id) {
        return accountService.findById(id)
                .map(account -> {
                    account.closeAccount();
                    Account updated = accountService.save(account);
                    return ResponseEntity.ok(AccountResponse.fromDomain(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        if (accountService.findById(id).isPresent()) {
            accountService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis() + "-" + 
               UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public record CreateAccountRequest(
            @NotNull(message = "El ID del cliente es obligatorio")
            UUID customerId,

            BigDecimal initialBalance
    ) {}

    public record DepositRequest(
            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record WithdrawRequest(
            @NotNull(message = "El monto es obligatorio")
            BigDecimal amount
    ) {}

    public record AccountResponse(
            UUID id,
            String accountNumber,
            UUID customerId,
            BigDecimal balance,
            String status,
            String createdAt,
            String updatedAt,
            String lastTransactionId
    ) {
        public static AccountResponse fromDomain(Account account) {
            return new AccountResponse(
                    account.getId(),
                    account.getAccountNumber(),
                    account.getCustomerId(),
                    account.getBalance(),
                    account.getStatus().name(),
                    account.getCreatedAt() != null ? account.getCreatedAt().toString() : null,
                    account.getUpdatedAt() != null ? account.getUpdatedAt().toString() : null,
                    account.getLastTransactionId()
            );
        }
    }
}