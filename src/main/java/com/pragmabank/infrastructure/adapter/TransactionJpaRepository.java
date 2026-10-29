package com.pragmabank.infrastructure.adapter;

import com.pragmabank.domain.model.Transaction;
import com.pragmabank.domain.port.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionJpaRepository implements TransactionRepository {

    private final JpaRepository<Transaction, UUID> jpaRepository;

    public TransactionJpaRepository(JpaRepository<Transaction, UUID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return jpaRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Transaction> findBySourceAccountId(UUID sourceAccountId) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getSourceAccountId() != null 
                        && t.getSourceAccountId().equals(sourceAccountId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByTargetAccountId(UUID targetAccountId) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getTargetAccountId() != null 
                        && t.getTargetAccountId().equals(targetAccountId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    public List<Transaction> findAll() {
        return jpaRepository.findAll();
    }

    public List<Transaction> findByAccountIdInvolved(UUID accountId) {
        List<Transaction> fromSource = findBySourceAccountId(accountId);
        List<Transaction> fromTarget = findByTargetAccountId(accountId);
        return fromSource.stream()
                .filter(t -> !fromTarget.contains(t))
                .collect(Collectors.toList());
    }

    public List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getTimestamp().isAfter(start) && t.getTimestamp().isBefore(end))
                .collect(Collectors.toList());
    }
}