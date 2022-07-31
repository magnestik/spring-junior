package ru.iteco.teachbase.springjunior.account.model.exception;

public class BankBookByUserExistException extends RuntimeException {
    private final Integer userId;

    public BankBookByUserExistException(String message, Integer userId) {
        super(message + " USER_ID: " + userId);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}
