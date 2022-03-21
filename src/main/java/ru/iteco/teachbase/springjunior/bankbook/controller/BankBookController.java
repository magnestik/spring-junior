package ru.iteco.teachbase.springjunior.bankbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookDto;
import ru.iteco.teachbase.springjunior.bankbook.service.BankBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-book")
public class BankBookController {
    private final BankBookService bankBookService;

    @GetMapping(value = {"/by-user-id/{userId}", "/by-user-id"})
    public List<BankBookDto> findAllByUserId(@PathVariable Integer userId) {
        return bankBookService.findAllByUserId(userId);
    }

    @GetMapping(value = {"/{bankBookId}", ""})
    public BankBookDto findById(@PathVariable Integer bankBookId) {
        return bankBookService.findById(bankBookId);
    }

    @PostMapping
    public BankBookDto create(@RequestBody BankBookDto bankBook) {
        return bankBookService.create(bankBook);
    }

    @PutMapping
    public BankBookDto update(@RequestBody BankBookDto bankBook) {
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
