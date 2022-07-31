package ru.iteco.teachbase.springjunior.account.model.exception;

public class BankBookNotFoundException extends RuntimeException {
    public BankBookNotFoundException(String message) {
        super(message);
    }
}
