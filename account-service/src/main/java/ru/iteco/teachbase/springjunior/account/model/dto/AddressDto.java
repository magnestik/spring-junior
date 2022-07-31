package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.iteco.teachbase.springjunior.account.validation.Created;
import ru.iteco.teachbase.springjunior.account.validation.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class AddressDto {
    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    private Integer id;
    private String country;
    private String city;
    private String street;
    private String home;
}
