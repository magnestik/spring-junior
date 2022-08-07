package ru.iteco.teachbase.springjunior.stock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.teachbase.springjunior.stock.model.EodMultipleRequest;
import ru.iteco.teachbase.springjunior.stock.model.EodMultipleResult;
import ru.iteco.teachbase.springjunior.stock.model.QuoteResult;
import ru.iteco.teachbase.springjunior.stock.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PreAuthorize("hasAnyAuthority(base, pro)")
    @PostMapping("/getQuoteByTickers")
    public QuoteResult getQuoteByTickers(@RequestBody List<String> tickers) {
        return stockService.getQuoteByTickers(tickers);
    }

    @PreAuthorize("hasAuthority(pro)")
    @PostMapping("/getEndOfDayMultiple")
    public EodMultipleResult getEndOfDayMultiple(@RequestBody EodMultipleRequest eodMultipleRequest) {
        return stockService.getEndOfDayMultiple(eodMultipleRequest);
    }
}
