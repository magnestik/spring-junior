package ru.iteco.teachbase.springjunior.account.model.exception;

public class BankBookChangeNumberException extends RuntimeException {
    public BankBookChangeNumberException(String message) {
        super(message);
    }
}
