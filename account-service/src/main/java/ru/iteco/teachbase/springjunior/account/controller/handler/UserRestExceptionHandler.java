package ru.iteco.teachbase.springjunior.account.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.teachbase.springjunior.account.controller.UserRestController;
import ru.iteco.teachbase.springjunior.account.model.dto.UserErrorDto;
import ru.iteco.teachbase.springjunior.account.model.exception.UserNotFoundException;

@RestControllerAdvice(basePackageClasses = UserRestController.class)
public class UserRestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public UserErrorDto handleUserNotFoundException(UserNotFoundException exception) {
        return new UserErrorDto("USER_NOT_FOUND", exception.getMessage(), exception.getIdNotFound());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public UserErrorDto handleUserNotFoundException(Exception exception) {
        return new UserErrorDto("EXCEPTION", exception.getMessage(), null);
    }
}
