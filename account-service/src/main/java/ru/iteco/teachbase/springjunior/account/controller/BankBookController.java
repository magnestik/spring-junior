package ru.iteco.teachbase.springjunior.account.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.account.model.dto.BankBookDto;
import ru.iteco.teachbase.springjunior.account.service.BankBookService;
import ru.iteco.teachbase.springjunior.account.validation.Created;
import ru.iteco.teachbase.springjunior.account.validation.Update;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-book")
public class BankBookController {
    private final BankBookService bankBookService;

    @GetMapping(value = { "/by-user-id"})
    public List<BankBookDto> findAllByUserId(@CookieValue Integer userId, @RequestHeader Map<String, String> headers) {
        log.info("Headers: {}", headers);
        return bankBookService.findAllByUserId(userId);
    }

    @GetMapping(value = {"/{bankBookId}", ""})
    public BankBookDto findById(@Min(2) @PathVariable Integer bankBookId) {
        return bankBookService.findById(bankBookId);
    }

    @Validated(Created.class)
    @PostMapping
    public BankBookDto create(@Valid @RequestBody BankBookDto bankBook) {
        return bankBookService.create(bankBook);
    }

    @Validated(Update.class)
    @PutMapping
    public BankBookDto update(@Valid @RequestBody BankBookDto bankBook) {
        return bankBookService.update(bankBook);
    }

    @DeleteMapping("/{bankBookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer bankBookId) {
        bankBookService.deleteById(bankBookId);
    }

    @DeleteMapping("/by-user-id/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllByUserId(@PathVariable Integer userId) {
        bankBookService.deleteAllByUserId(userId);
    }

    @GetMapping("/all")
    public List<BankBookDto> getAll() {
        return bankBookService.getAll();
    }
}
