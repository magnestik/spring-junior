package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Integer id;
    private String country;
    private String city;
    private String street;
    private String home;
}
