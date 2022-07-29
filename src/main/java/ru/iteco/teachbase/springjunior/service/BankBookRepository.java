package ru.iteco.teachbase.springjunior.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.teachbase.springjunior.model.entity.BankBookEntity;
import ru.iteco.teachbase.springjunior.model.entity.CurrencyEntity;

import java.util.List;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {
    List<BankBookEntity> findAllByUserId(Integer userId);
    boolean existsByUserIdAndNumberAndCurrency(int userId, String number, CurrencyEntity currency);

    @Transactional
    int deleteAllByUserId(int userId);
}