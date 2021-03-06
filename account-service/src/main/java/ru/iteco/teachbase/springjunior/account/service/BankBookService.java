package ru.iteco.teachbase.springjunior.account.service;

import ru.iteco.teachbase.springjunior.account.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {
    List<BankBookDto> findAllByUserId(Integer userId);

    BankBookDto findById(Integer bankBookId);

    BankBookDto create(BankBookDto bankBook);

    BankBookDto update(BankBookDto bankBook);

    void deleteById(Integer bankBookId);

    List<BankBookDto> getAll();

    void deleteAllByUserId(Integer userId);
}
