package ru.iteco.teachbase.springjunior.bankbook.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookEntity;

import java.util.List;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {
    List<BankBookEntity> findAllByUserId(Integer userId);
    boolean existsByUserIdAndNumberAndCurrency(int userId, String number, String currency);

    @Transactional
    int deleteByUserId(int userId);
}