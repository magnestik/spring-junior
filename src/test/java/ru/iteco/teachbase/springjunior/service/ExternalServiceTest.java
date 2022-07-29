package ru.iteco.teachbase.springjunior.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExternalServiceTest {
    private final ExternalService externalService = new ExternalService();

    @Test
    void getInfo() {
        assertEquals("INFO with 1", externalService.getInfo("1"));
    }
}