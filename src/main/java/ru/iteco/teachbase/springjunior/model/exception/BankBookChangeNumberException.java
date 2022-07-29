package ru.iteco.teachbase.springjunior.model.exception;

public class BankBookChangeNumberException extends RuntimeException {
    public BankBookChangeNumberException(String message) {
        super(message);
    }
}
