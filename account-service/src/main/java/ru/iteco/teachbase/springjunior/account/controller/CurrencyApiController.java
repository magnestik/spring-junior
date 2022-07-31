package ru.iteco.teachbase.springjunior.account.controller;

import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.account.model.dto.ConvertResult;
import ru.iteco.teachbase.springjunior.account.model.dto.ConverterRequest;
import ru.iteco.teachbase.springjunior.account.model.dto.LatestResult;
import ru.iteco.teachbase.springjunior.account.service.CurrencyServiceApi;

@RestController
@RequestMapping("/currency")
public class CurrencyApiController {
    private final CurrencyServiceApi currencyServiceApi;

    public CurrencyApiController(CurrencyServiceApi currencyServiceApi) {
        this.currencyServiceApi = currencyServiceApi;
    }

    @PostMapping("/convert")
    public ConvertResult convertAmount(@RequestBody ConverterRequest converterRequest) {
        return currencyServiceApi.convert(converterRequest);
    }

    @GetMapping("/latest")
    public LatestResult latestByRub() {
        return currencyServiceApi.getAllExchangeApi();
    }
}
