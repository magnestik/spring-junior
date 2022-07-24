package ru.iteco.teachbase.springjunior.bankbook.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookDto;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookEntity;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookByUserExistException;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookChangeNumberException;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookNotFoundException;

import java.util.List;

@Service
public class BankBookServiceImpl implements BankBookService {
    private final BankBookRepository bankBookRepository;
    private final BankBookMapper bankBookMapper;

    public BankBookServiceImpl(BankBookRepository bankBookRepository, BankBookMapper bankBookMapper) {
        this.bankBookRepository = bankBookRepository;
        this.bankBookMapper = bankBookMapper;
    }

    @Override
    public List<BankBookDto> findAllByUserId(Integer userId) {
        List<BankBookEntity> bankBookEntities = bankBookRepository.findAllByUserId(userId);
        if (bankBookEntities.isEmpty()) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов!");
        }
        return bankBookMapper.mapToDtos(bankBookEntities);
    }

    @Override
    public BankBookDto findById(Integer bankBookId) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookId)
            .orElseThrow(() -> new BankBookNotFoundException("Счёт не найден!"));
        return bankBookMapper.mapToDto(bankBookEntity);
    }

    @Override
    public BankBookDto create(BankBookDto bankBook) {
        if (bankBookRepository.existsByUserIdAndNumberAndCurrency(bankBook.getUserId(), bankBook.getNumber(), bankBook.getCurrency())) {
            throw new BankBookByUserExistException("Cчёт с данной валютой для данного клиента уже имеется в хранилище!",
                bankBook.getUserId());
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBook);
        bankBookEntity = bankBookRepository.save(bankBookEntity);
        return bankBookMapper.mapToDto(bankBookEntity);
    }

    @Override
    public BankBookDto update(BankBookDto bankBook) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBook.getId())
            .orElseThrow(() -> new BankBookNotFoundException("Счет не найден!"));
        if (!bankBook.getNumber().equals(bankBookEntity.getNumber())) {
            throw new BankBookChangeNumberException("Изменение номера счёта запрещено!");
        }
        bankBookEntity = bankBookMapper.mapToEntity(bankBook);
        bankBookEntity = bankBookRepository.save(bankBookEntity);
        return bankBookMapper.mapToDto(bankBookEntity);
    }

    @Override
    public void deleteById(Integer bankBookId) {
        bankBookRepository.deleteById(bankBookId);
    }

    @Override
    public List<BankBookDto> getAll() {
        List<BankBookEntity> bookEntities = bankBookRepository.findAll();
        return bankBookMapper.mapToDtos(bookEntities);
    }

    @Override
    public void deleteAllByUserId(Integer userId) {
        int count = bankBookRepository.deleteByUserId(userId);
        if (count == 0) {
            throw new BankBookNotFoundException("Счетов для данного пользователя не найдено!");
        }
    }
}
