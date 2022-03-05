package ru.iteco.teachbase.springjunior.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String status;
    private String message;
    private Integer id;
}
