package ru.iteco.teachbase.springjunior.currency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.iteco.teachbase.springjunior.currency.model.ConvertResult;
import ru.iteco.teachbase.springjunior.currency.model.ConverterRequest;
import ru.iteco.teachbase.springjunior.currency.model.LatestResult;
import ru.iteco.teachbase.springjunior.currency.service.FixerApi;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final FixerApi fixerApi;

    public CurrencyController(FixerApi fixerApi) {
        this.fixerApi = fixerApi;
    }

    @PostMapping("/convert")
    public ConvertResult convertAmount(@RequestHeader("trace-id") String traceId, @RequestBody ConverterRequest converterRequest) {
        log.info("Request with trace-id: {}", traceId);
        return fixerApi.convert(converterRequest);
    }

    @GetMapping("/latest")
    public LatestResult latestByRub(@RequestHeader Map<String, String> headers) {
        log.info("Request with trace-id: {}", headers);
        return fixerApi.latestByRub();
    }
}
