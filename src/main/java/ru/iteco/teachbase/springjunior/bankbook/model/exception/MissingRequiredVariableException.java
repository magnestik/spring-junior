package ru.iteco.teachbase.springjunior.bankbook.model.exception;

public class MissingRequiredVariableException extends RuntimeException {
    public MissingRequiredVariableException() {
    }

    public MissingRequiredVariableException(String message) {
        super(message);
    }

    public MissingRequiredVariableException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingRequiredVariableException(Throwable cause) {
        super(cause);
    }

    public MissingRequiredVariableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
