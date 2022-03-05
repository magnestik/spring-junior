package ru.iteco.teachbase.springjunior.bankbook.model.exception;

public class BankBookChangeNumberException extends RuntimeException {
    public BankBookChangeNumberException() {
    }

    public BankBookChangeNumberException(String message) {
        super(message);
    }

    public BankBookChangeNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankBookChangeNumberException(Throwable cause) {
        super(cause);
    }

    public BankBookChangeNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
