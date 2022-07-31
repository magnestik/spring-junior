package ru.iteco.teachbase.springjunior.currency.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConverterRequest {
    private String from;
    private String to;
    private BigDecimal amount;
}
