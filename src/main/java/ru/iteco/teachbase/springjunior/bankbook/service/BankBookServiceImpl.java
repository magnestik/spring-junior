package ru.iteco.teachbase.springjunior.bankbook.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookByUserExistException;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookChangeNumberException;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookDto;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.BankBookNotFoundException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BankBookServiceImpl implements BankBookService {
    private final Map<Integer, BankBookDto> bankBookMap = new ConcurrentHashMap<>();
    private final AtomicInteger seqId = new AtomicInteger(1);

    @PostConstruct
    public void init() {
        int id = seqId.getAndIncrement();
        BankBookDto bankBookDto = BankBookDto.builder()
            .id(id)
            .userId(10)
            .number("number")
            .amount(BigDecimal.TEN)
            .currency("RUB")
            .build();
        bankBookMap.put(id, bankBookDto);
    }

    @Override
    public List<BankBookDto> findAllByUserId(Integer userId) {
        List<BankBookDto> bankBookDtos = bankBookMap.values().stream()
            .filter(bankBookDto -> bankBookDto.getUserId().equals(userId))
            .collect(Collectors.toList());
        if (bankBookDtos.isEmpty()) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов!");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto findById(Integer bankBookId) {
        BankBookDto bankBookDto = bankBookMap.get(bankBookId);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Счёт не найден!");
        }
        return bankBookDto;
    }

    @Override
    public BankBookDto create(BankBookDto bankBook) {
        bankBookMap.values().stream()
            .filter(bankBookDto -> bankBookDto.getUserId().equals(bankBook.getUserId()))
            .filter(bankBookDto -> bankBookDto.getNumber().equals(bankBook.getNumber()))
            .filter(bankBookDto -> bankBookDto.getCurrency().equals(bankBook.getCurrency()))
            .findFirst()
            .ifPresent(bankBookDto -> {
                throw new BankBookByUserExistException("Cчёт с данной валютой для данного клиента уже имеется в хранилище!",
                    bankBookDto.getUserId());
            });
        int id = seqId.getAndIncrement();
        bankBook.setId(id);
        bankBookMap.put(id, bankBook);
        return bankBook;
    }

    @Override
    public BankBookDto update(BankBookDto bankBook) {
        BankBookDto bankBookDto = bankBookMap.get(bankBook.getId());
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Счет не найден!");
        }
        if (!bankBook.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookChangeNumberException("Изменение номера счёта запрещено!");
        }
        bankBookMap.put(bankBook.getId(), bankBook);
        return bankBook;
    }

    @Override
    public void deleteById(Integer bankBookId) {
        if (bankBookMap.get(bankBookId) == null) {
            throw new BankBookNotFoundException("Счёт не найден!");
        }
        bankBookMap.remove(bankBookId);
    }

    @Override
    public List<BankBookDto> getAll() {
        return new ArrayList<>(bankBookMap.values());
    }

    @Override
    public void deleteAllByUserId(Integer userId) {
        List<Integer> bankBookIdsByUserId = bankBookMap.values().stream()
            .filter(bankBookDto -> userId.equals(bankBookDto.getUserId()))
            .map(BankBookDto::getId)
            .collect(Collectors.toList());
        if (bankBookIdsByUserId.isEmpty()) {
            throw new BankBookNotFoundException("Счетов для данного пользователя не найдено!");
        }
        bankBookIdsByUserId.forEach(bankBookMap::remove);
    }
}
