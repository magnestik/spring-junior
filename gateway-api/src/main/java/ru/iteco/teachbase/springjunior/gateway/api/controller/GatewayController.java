package ru.iteco.teachbase.springjunior.gateway.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.iteco.teachbase.springjunior.gateway.api.model.EodMultipleRequest;
import ru.iteco.teachbase.springjunior.gateway.api.service.CurrencyApiService;
import ru.iteco.teachbase.springjunior.gateway.api.service.StockApiService;

import java.util.List;

@Slf4j
@Controller
public class GatewayController {
    private final CurrencyApiService currencyApiService;
    private final StockApiService stockApiService;

    public GatewayController(CurrencyApiService currencyApiService, StockApiService stockApiService) {
        this.currencyApiService = currencyApiService;
        this.stockApiService = stockApiService;
    }

    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            log.info("USER: {}", principal.getClaims());
            log.info("AUTH: {}", principal.getAuthorities());
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }

    @GetMapping("/all-exchange")
    public @ResponseBody String getAllExchange() {
        return currencyApiService.getAllExchange();
    }


    @GetMapping("/convert")
    public @ResponseBody String getConvert() {
        return currencyApiService.convert();
    }

    @PostMapping("/getQuoteByTickers")
    public @ResponseBody String getQuoteByTickers(@RequestBody List<String> tickers) {
        return stockApiService.getQuoteByTickers(tickers);
    }

    @PostMapping("/getEndOfDayMultiple")
    public @ResponseBody String getEndOfDayMultiple(@RequestBody EodMultipleRequest eodMultipleRequest) {
        return stockApiService.getEndOfDayMultiple(eodMultipleRequest);
    }

}
