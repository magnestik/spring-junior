package ru.iteco.teachbase.springjunior.account.service;

import ru.iteco.teachbase.springjunior.account.model.dto.ConvertResult;
import ru.iteco.teachbase.springjunior.account.model.dto.ConverterRequest;
import ru.iteco.teachbase.springjunior.account.model.dto.LatestResult;

public interface CurrencyServiceApi {

    LatestResult getAllExchangeApi();

    ConvertResult convert(ConverterRequest converterRequest);
}
