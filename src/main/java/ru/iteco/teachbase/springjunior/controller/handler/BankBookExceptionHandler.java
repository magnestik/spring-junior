package ru.iteco.teachbase.springjunior.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.teachbase.springjunior.controller.BankBookController;
import ru.iteco.teachbase.springjunior.model.dto.ErrorDto;
import ru.iteco.teachbase.springjunior.model.exception.BankBookByUserExistException;
import ru.iteco.teachbase.springjunior.model.exception.BankBookChangeNumberException;
import ru.iteco.teachbase.springjunior.model.exception.BankBookNotFoundException;

@RestControllerAdvice(basePackageClasses = BankBookController.class)
public class BankBookExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookByUserExistException.class)
    public ErrorDto handleBankBookByUserIdExist(BankBookByUserExistException exception) {
        return ErrorDto.builder().message(exception.getMessage()).status("BANK_BOOK_BY_USER_ID_EXIST").build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankBookChangeNumberException.class)
    public ErrorDto handleBankBookByUserIdExist(BankBookChangeNumberException exception) {
        return ErrorDto.builder().message(exception.getMessage()).status("CHANGE_NUMBER_NOT_ALLOWED").build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BankBookNotFoundException.class)
    public ErrorDto handleBankBookNotFound(BankBookNotFoundException exception) {
        return ErrorDto.builder().message(exception.getMessage()).status("NOT_FOUND").build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingPathVariableException.class)
    public ErrorDto handleMissingPathVariableException(MissingPathVariableException exception) {
        return ErrorDto.builder().message(exception.getMessage()).status("MISSING_REQUIRED_VARIABLE").build();
    }
}
