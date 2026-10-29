package com.pragmabank.infrastructure.adapter;

import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.port.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountJpaRepository implements AccountRepository {

    private final JpaRepository<Account, UUID> jpaRepository;

    public AccountJpaRepository(JpaRepository<Account, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Account save(Account account) {
        return jpaRepository.save(account);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return jpaRepository.findAll().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    @Override
    public Optional<Account> findByCustomerId(UUID customerId) {
        return jpaRepository.findAll().stream()
                .filter(account -> account.getCustomerId().equals(customerId))
                .findFirst();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    public List<Account> findAll() {
        return jpaRepository.findAll();
    }

    public boolean existsByAccountNumber(String accountNumber) {
        return findByAccountNumber(accountNumber).isPresent();
    }

    public boolean existsByCustomerId(UUID customerId) {
        return findByCustomerId(customerId).isPresent();
    }
}