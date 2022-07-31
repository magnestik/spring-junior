package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class LatestResult {

    private String base;
    private LocalDate date;
    Map<String, BigDecimal> rates;

}
