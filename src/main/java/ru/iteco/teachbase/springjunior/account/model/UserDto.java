package ru.iteco.teachbase.springjunior.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private AddressDto address;
}
