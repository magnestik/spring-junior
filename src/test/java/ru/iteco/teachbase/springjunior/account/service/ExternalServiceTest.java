package ru.iteco.teachbase.springjunior.account.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalServiceTest {
    private ExternalService externalService = new ExternalService();

    @Test
    void getInfo() {
        assertEquals("INFO with 1", externalService.getInfo("1"));
    }
}