package ru.iteco.teachbase.springjunior.stock.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EodMultipleDate {
    private String symbol;
    private LocalDate date;
    private BigDecimal close;
}
