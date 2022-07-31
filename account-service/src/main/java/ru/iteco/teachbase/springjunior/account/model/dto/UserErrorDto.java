package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserErrorDto {
    private String status;
    private String message;
    private Integer id;
}
