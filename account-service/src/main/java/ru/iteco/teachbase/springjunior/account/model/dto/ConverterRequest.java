package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ConverterRequest {
    private String from;
    private String to;
    private BigDecimal amount;
}
