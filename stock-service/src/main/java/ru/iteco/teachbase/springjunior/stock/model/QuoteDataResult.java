package ru.iteco.teachbase.springjunior.stock.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteDataResult {
    private String ticker;
    private String name;
    private String currency;
    private BigDecimal price;
}
