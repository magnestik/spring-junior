package ru.iteco.teachbase.springjunior.bankbook.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookDto;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookEntity;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookByUserExistException;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookChangeNumberException;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookNotFoundException;
import ru.iteco.teachbase.springjunior.currency.CurrencyEntity;
import ru.iteco.teachbase.springjunior.currency.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankBookServiceImpl implements BankBookService {
    private final BankBookRepository bankBookRepository;
    private final BankBookMapper bankBookMapper;
    private final CurrencyRepository currencyRepository;

    public BankBookServiceImpl(BankBookRepository bankBookRepository, BankBookMapper bankBookMapper, CurrencyRepository currencyRepository) {
        this.bankBookRepository = bankBookRepository;
        this.bankBookMapper = bankBookMapper;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<BankBookDto> findAllByUserId(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookRepository.findAllByUserId(userId).stream()
                .map(bankBookMapper::mapToDto)
                .collect(Collectors.toList());
        if (bankBookDtos.isEmpty()) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов!");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto findById(Integer bankBookId) {
        return bankBookRepository.findById(bankBookId).map(bankBookMapper::mapToDto)
                .orElseThrow(() -> new BankBookNotFoundException("Счёт не найден!"));
    }

    @Override
    public BankBookDto create(BankBookDto bankBook) {
        CurrencyEntity currency = currencyRepository.findByName(bankBook.getCurrency());
        if (bankBookRepository.existsByUserIdAndNumberAndCurrency(bankBook.getUserId(), bankBook.getNumber(), currency)) {
            throw new BankBookByUserExistException("Cчёт с данной валютой для данного клиента уже имеется в хранилище!",
                    bankBook.getUserId());
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBook);
        bankBookEntity.setCurrency(currency);
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
        CurrencyEntity currency = currencyRepository.findByName(bankBook.getCurrency());
        bankBookEntity.setCurrency(currency);
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
        int count = bankBookRepository.deleteAllByUserId(userId);
        if (count == 0) {
            throw new BankBookNotFoundException("Счетов для данного пользователя не найдено!");
        }
    }
}
