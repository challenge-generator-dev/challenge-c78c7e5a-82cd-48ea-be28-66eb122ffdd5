package com.pragmabank.domain.port;

import com.pragmabank.domain.model.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByCustomerId(UUID customerId);

    void deleteById(UUID id);
}