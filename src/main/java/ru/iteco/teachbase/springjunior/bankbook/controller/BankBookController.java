package ru.iteco.teachbase.springjunior.bankbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookDto;
import ru.iteco.teachbase.springjunior.bankbook.model.exception.MissingRequiredVariableException;
import ru.iteco.teachbase.springjunior.bankbook.service.BankBookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-book")
public class BankBookController {
    private final BankBookService bankBookService;

    @GetMapping(value = {"/by-user-id/{userId}", "/by-user-id"})
    public List<BankBookDto> findAllByUserId(@PathVariable Optional<Integer> userId) {
        Integer id = getVariableOrElseThrow(userId, "userId");
        return bankBookService.findAllByUserId(id);
    }

    @GetMapping(value = {"/{bankBookId}", ""})
    public BankBookDto findById(@PathVariable Optional<Integer> bankBookId) {
        Integer id = getVariableOrElseThrow(bankBookId, "bankBookId");
        return bankBookService.findById(id);
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

    private <T> T getVariableOrElseThrow(Optional<T> variable, String name) {
        String message = String.format("Не указан {%s} в запросе", name);
        return variable.orElseThrow(() -> new MissingRequiredVariableException(message));
    }
}
