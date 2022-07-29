package ru.iteco.teachbase.springjunior.controller;

import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.model.dto.TransactionDto;
import ru.iteco.teachbase.springjunior.service.TransactionService;

import static org.springframework.util.Assert.notNull;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/between-bank-book")
    public void transactBetweenBankBook(@RequestBody TransactionDto transaction) {
        notNull(transaction.getSourceBankBookId(), "Не заполнен счёт-источник");
        notNull(transaction.getTargetBankBookId(), "Не заполнен счёт-получатель");
        transactionService.transactionBetweenBankBooks(transaction.getAmount(),
                transaction.getSourceBankBookId(),
                transaction.getTargetBankBookId());
    }

    @PostMapping("/between-user")
    public void transactBetweenUser(@RequestBody TransactionDto transaction) {
        notNull(transaction.getSourceBankBookUserId(), "Не заполнен пользователь-источник");
        notNull(transaction.getTargetBankBookUserId(), "Не заполнен пользователь-получатель");
        transactionService.transactionOtherUser(transaction);
    }
}
