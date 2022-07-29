package ru.iteco.teachbase.springjunior.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.model.dto.TransactionDto;
import ru.iteco.teachbase.springjunior.model.entity.BankBookEntity;
import ru.iteco.teachbase.springjunior.model.entity.StatusEntity;
import ru.iteco.teachbase.springjunior.model.entity.TransactionEntity;
import ru.iteco.teachbase.springjunior.model.entity.UserEntity;
import ru.iteco.teachbase.springjunior.model.exception.BankBookNotFoundException;
import ru.iteco.teachbase.springjunior.model.exception.TransactionException;
import ru.iteco.teachbase.springjunior.model.exception.UserNotFoundException;
import ru.iteco.teachbase.springjunior.repository.StatusRepository;
import ru.iteco.teachbase.springjunior.repository.TransactionRepository;
import ru.iteco.teachbase.springjunior.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class TransactionService {
    private final BankBookRepository bankBookRepository;
    private final TransactionRepository transactionRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public TransactionService(BankBookRepository bankBookRepository,
                              TransactionRepository transactionRepository,
                              StatusRepository statusRepository,
                              UserRepository userRepository) {
        this.bankBookRepository = bankBookRepository;
        this.transactionRepository = transactionRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    public void transactionBetweenBankBooks(BigDecimal amount, Integer sourceBankBookId, Integer targetBankBookId) {
        BankBookEntity bankBookSource = bankBookRepository.findById(sourceBankBookId)
                .orElseThrow(() -> new BankBookNotFoundException("Счёт-источник не найден!"));
        BankBookEntity bankBookTarget = bankBookRepository.findById(targetBankBookId)
                .orElseThrow(() -> new BankBookNotFoundException("Счёт-источник не найден!"));

        transactionBetweenBankBooks(amount, bankBookSource, bankBookTarget);
    }

    public void transactionOtherUser(TransactionDto transaction) {
        BankBookEntity sourceBankBook;
        if (transaction.getSourceBankBookId() != null) {
            sourceBankBook = bankBookRepository.findById(transaction.getSourceBankBookId())
                    .orElseThrow(() -> new BankBookNotFoundException("Счёт-источник не найден!"));
        } else {
            sourceBankBook =
                    bankBookRepository.findAllByUserId(transaction.getSourceBankBookUserId()).stream()
                            .filter(bankBook ->
                                    transaction.getCurrency().equals(bankBook.getCurrency().getName())
                                            && transaction.getAmount().compareTo(bankBook.getAmount()) >= 0)
                            .findFirst()
                            .orElseThrow(() -> new BankBookNotFoundException("Не найден подходящий счёт-источник"));
        }
        UserEntity user = userRepository.findById(transaction.getTargetBankBookUserId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден!"));
        BankBookEntity targetBankBook = user.getBankBooks().stream()
                .filter(bankBookEntity -> bankBookEntity.getCurrency().equals(sourceBankBook.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new TransactionException("У пользователя-получателя отсутствует счёт с валютой счёта-источника"));

        transactionBetweenBankBooks(transaction.getAmount(), sourceBankBook, targetBankBook);
    }

    private void transactionBetweenBankBooks(BigDecimal amount, BankBookEntity sourceBankBook, BankBookEntity targetBankBook) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .sourceBankBook(sourceBankBook)
                .targetBankBook(targetBankBook)
                .amount(amount)
                .initiationDate(LocalDateTime.now())
                .status(statusRepository.findByName(StatusEntity.Status.processing.name()))
                .build();
        transactionEntity = transactionRepository.save(transactionEntity);

        try {
            if (!sourceBankBook.getCurrency().equals(targetBankBook.getCurrency())) {
                throw new TransactionException("Валюты источника и получателя не совпадают");
            }
            if (amount.compareTo(sourceBankBook.getAmount()) > 0) {
                throw new TransactionException("На счёте-источнике не хватает средств для перевода");
            }

            sourceBankBook.setAmount(sourceBankBook.getAmount().subtract(amount));
            targetBankBook.setAmount(targetBankBook.getAmount().add(amount));
            bankBookRepository.saveAll(Arrays.asList(sourceBankBook, targetBankBook));

            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionEntity.setStatus(statusRepository.findByName(StatusEntity.Status.successful.name()));
            transactionRepository.save(transactionEntity);
        } catch (Exception e) {
            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionEntity.setStatus(statusRepository.findByName(StatusEntity.Status.declined.name()));
            transactionRepository.save(transactionEntity);
        }
    }
}
