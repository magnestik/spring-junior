package ru.iteco.teachbase.springjunior.account.homework.service;

import ru.iteco.teachbase.springjunior.account.homework.model.ExternalInfo;

public interface ExternalService {
    ExternalInfo getExternalInfo(Integer id);
}