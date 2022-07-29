package ru.iteco.teachbase.springjunior.model.exception;

public class BankBookNotFoundException extends RuntimeException {
    public BankBookNotFoundException(String message) {
        super(message);
    }
}
