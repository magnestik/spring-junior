package ru.iteco.teachbase.springjunior.account.aop;

public interface ExternalService {
    String getInfo(Long id) throws RuntimeException;
    String getCustomInfo();
}
