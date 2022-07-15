package ru.iteco.teachbase.springjunior.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorDto {
    private String status;
    private String message;
    private Integer id;
}
