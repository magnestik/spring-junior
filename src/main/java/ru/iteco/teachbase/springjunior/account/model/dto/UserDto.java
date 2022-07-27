package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.iteco.teachbase.springjunior.validation.Created;
import ru.iteco.teachbase.springjunior.validation.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {
    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    private Integer id;
    private String name;
    private String email;
    private AddressDto address;
}
