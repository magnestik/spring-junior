package ru.iteco.teachbase.springjunior.bankbook.model;

import lombok.Builder;
import lombok.Data;
import ru.iteco.teachbase.springjunior.bankbook.validation.Created;
import ru.iteco.teachbase.springjunior.bankbook.validation.Currency;
import ru.iteco.teachbase.springjunior.bankbook.validation.Update;

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
    @NotBlank
    private String number;
    @PositiveOrZero
    private BigDecimal amount;
    @Currency
    private String currency;
}
