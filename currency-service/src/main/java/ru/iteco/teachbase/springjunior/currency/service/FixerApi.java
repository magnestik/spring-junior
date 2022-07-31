package ru.iteco.teachbase.springjunior.currency.service;

import ru.iteco.teachbase.springjunior.currency.model.ConvertResult;
import ru.iteco.teachbase.springjunior.currency.model.ConverterRequest;
import ru.iteco.teachbase.springjunior.currency.model.LatestResult;

public interface FixerApi {

    ConvertResult convert(ConverterRequest request);

    LatestResult latestByRub();

}
