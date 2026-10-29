package com.pragmabank.application.usecase;


import com.pragmabank.domain.model.AccountStatus;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.port.AccountRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account execute(CreateAccountRequest request) {
        validateRequest(request);
        validateBusinessRules(request);
        
        Account account = new Account(
                UUID.randomUUID(),
                request.accountNumber(),
                request.customerId(),
                BigDecimal.ZERO,
                Account.AccountStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        
        return accountRepository.save(account);
    }

    private void validateRequest(CreateAccountRequest request) {
        if (request.accountNumber() == null || request.accountNumber().isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }
        if (request.customerId() == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo");
        }
    }

    private void validateBusinessRules(CreateAccountRequest request) {
        if (accountRepository.findByAccountNumber(request.accountNumber()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una cuenta con el número: " + request.accountNumber());
        }
    }

    public record CreateAccountRequest(
            @NotBlank(message = "El número de cuenta es requerido")
            String accountNumber,
            @NotNull(message = "El ID del cliente es requerido")
            UUID customerId,
            @Positive(message = "El balance inicial debe ser positivo")
            BigDecimal initialBalance
    ) {}
}