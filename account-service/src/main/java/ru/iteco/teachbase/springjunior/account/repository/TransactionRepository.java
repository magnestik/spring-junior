package ru.iteco.teachbase.springjunior.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.teachbase.springjunior.account.model.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}