package ru.iteco.teachbase.springjunior.account.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthDto {
    private String login;
    private String password;
}
