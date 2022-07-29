package ru.iteco.teachbase.springjunior.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.iteco.teachbase.springjunior.validation.Created;
import ru.iteco.teachbase.springjunior.validation.Currency;
import ru.iteco.teachbase.springjunior.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class BankBookDto {
    @Null(groups = Created.class)
    @NotNull(groups = Update.class)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotBlank(message = "пустой!")
    private String number;
    @PositiveOrZero
    @NotNull
    private BigDecimal amount;
    @Currency
    @NotNull
    private String currency;
}
