package ru.iteco.teachbase.springjunior.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.teachbase.springjunior.account.model.dto.ErrorDto;
import ru.iteco.teachbase.springjunior.account.model.UserNotFoundException;

@RestControllerAdvice(basePackageClasses = UserRestController.class)
public class UserRestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDto handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorDto("USER_NOT_FOUND", exception.getMessage(), exception.getIdNotFound());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ErrorDto handleUserNotFoundException(Exception exception) {
        return new ErrorDto("EXCEPTION", exception.getMessage(), null);
    }
}
