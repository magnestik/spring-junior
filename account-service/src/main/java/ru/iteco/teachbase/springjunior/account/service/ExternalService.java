package ru.iteco.teachbase.springjunior.account.service;

import org.springframework.stereotype.Service;

@Service
public class ExternalService {
    public String getInfo(String id) {
        return "INFO with " + id;
    }
}
