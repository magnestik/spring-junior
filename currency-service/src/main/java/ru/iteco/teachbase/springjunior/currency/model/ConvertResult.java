package ru.iteco.teachbase.springjunior.currency.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConvertResult {
    private ConverterRequest query;
    private BigDecimal result;
}
