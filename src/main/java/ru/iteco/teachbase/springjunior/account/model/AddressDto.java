package ru.iteco.teachbase.springjunior.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String country;
    private String city;
    private String street;
    private String home;
}
