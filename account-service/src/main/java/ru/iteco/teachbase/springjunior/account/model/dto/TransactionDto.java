package ru.iteco.teachbase.springjunior.account.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TransactionDto implements Serializable {

    private final Integer sourceBankBookId;
    private final Integer sourceBankBookUserId;
    private final Integer targetBankBookId;
    private final Integer targetBankBookUserId;
    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final String currency;

}
