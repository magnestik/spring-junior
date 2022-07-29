package ru.iteco.teachbase.springjunior.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.model.entity.BankBookEntity;
import ru.iteco.teachbase.springjunior.model.entity.UserEntity;
import ru.iteco.teachbase.springjunior.model.exception.BankBookNotFoundException;
import ru.iteco.teachbase.springjunior.model.exception.TransactionException;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class TransactionService {
    private final BankBookRepository bankBookRepository;

    public TransactionService(BankBookRepository bankBookRepository) {
        this.bankBookRepository = bankBookRepository;
    }

    public void transactionBetweenBankBooks(BigDecimal amount, Integer sourceId, Integer targetId){
        BankBookEntity bankBookSource = bankBookRepository.findById(sourceId)
                .orElseThrow(() -> new BankBookNotFoundException("Счёт-источник не найден!"));
        BankBookEntity bankBookTarget = bankBookRepository.findById(targetId)
                .orElseThrow(() -> new BankBookNotFoundException("Счёт-источник не найден!"));

        transactionBetweenBankBooks(amount, bankBookSource, bankBookTarget);
    }

    public void transactionBetweenBankBooks(BigDecimal amount, BankBookEntity source, BankBookEntity target){
        if (!source.getCurrency().equals(target.getCurrency())) {
            throw new TransactionException("Валюты источника и получателя не совпадают");
        }
        if (amount.compareTo(source.getAmount()) > 0) {
            throw new TransactionException("На счёте-источнике не хватает средств для перевода");
        }
        source.setAmount(source.getAmount().subtract(amount));
        target.setAmount(target.getAmount().add(amount));
        bankBookRepository.saveAll(Arrays.asList(source, target));
    }

    public void transactionOtherUsers(BigDecimal amount, BankBookEntity source, UserEntity user){
        BankBookEntity targetBankBook = user.getBankBooks().stream()
                .filter(bankBookEntity -> bankBookEntity.getCurrency().equals(source.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new TransactionException("У пользователя {} отсутствует счёт с валютой счёта-источника"));
        if (amount.compareTo(source.getAmount()) > 0) {
            throw new TransactionException("На счёте-источнике не хватает средств для перевода");
        }
        source.setAmount(source.getAmount().subtract(amount));
        targetBankBook.setAmount(targetBankBook.getAmount().add(amount));
        bankBookRepository.saveAll(Arrays.asList(source, targetBankBook));
    }
}
