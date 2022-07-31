package ru.iteco.teachbase.springjunior.account.model.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }
}
