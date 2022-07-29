package ru.iteco.teachbase.springjunior.model.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }
}
