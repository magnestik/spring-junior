package ru.iteco.teachbase.springjunior.stock.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EodMultipleRequest {
    private List<String> symbols;
    private LocalDate date;
}
