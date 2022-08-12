package ru.iteco.teachbase.springjunior.gateway.api;

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
