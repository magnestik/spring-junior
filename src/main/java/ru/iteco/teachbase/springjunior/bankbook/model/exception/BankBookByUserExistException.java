package ru.iteco.teachbase.springjunior.bankbook.model.exception;

public class BankBookByUserExistException extends RuntimeException {
    private final Integer userId;

    public BankBookByUserExistException(Integer userId) {
        this.userId = userId;
    }

    public BankBookByUserExistException(String message, Integer userId) {
        super(message + " USER_ID: " + userId);
        this.userId = userId;
    }

    public BankBookByUserExistException(String message, Throwable cause, Integer userId) {
        super(message, cause);
        this.userId = userId;
    }

    public BankBookByUserExistException(Throwable cause, Integer userId) {
        super(cause);
        this.userId = userId;
    }

    public BankBookByUserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer userId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
