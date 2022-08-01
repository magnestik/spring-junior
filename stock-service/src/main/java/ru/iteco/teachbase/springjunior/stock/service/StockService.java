package ru.iteco.teachbase.springjunior.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.iteco.teachbase.springjunior.stock.model.EodMultipleRequest;
import ru.iteco.teachbase.springjunior.stock.model.EodMultipleResult;
import ru.iteco.teachbase.springjunior.stock.model.QuoteResult;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StockService {
    private final RestTemplate stockRestTemplate;
    @Value("${stock.token}")
    private String token;
    @Value("${stock.url.stock}")
    private String stockUrl;

    public StockService(RestTemplate stockRestTemplate) {
        this.stockRestTemplate = stockRestTemplate;
    }

    public QuoteResult getQuoteByTickers(List<String> tickers) {
        URI uri = UriComponentsBuilder.fromHttpUrl(stockUrl)
            .path("/data")
            .path("/quote")
            .queryParam("symbols", String.join(",", tickers))
            .queryParam("api_token", token)
            .build(true)
            .toUri();

        ResponseEntity<QuoteResult> responseEntity = stockRestTemplate.getForEntity(uri, QuoteResult.class);
        if (responseEntity.getStatusCode().isError()) {
            log.error("StockData вернул ошибку: {}", responseEntity);
        }
        return responseEntity.getBody();
    }

    public EodMultipleResult getEndOfDayMultiple(EodMultipleRequest eodMultipleRequest) {

        URI uri = UriComponentsBuilder.fromHttpUrl(stockUrl)
            .path("/data")
            .path("/eod")
            .path("/multiple")
            .queryParam("api_token", token)
            .queryParam("symbols", String.join(",", eodMultipleRequest.getSymbols()))
            .queryParamIfPresent("date", Optional.ofNullable(eodMultipleRequest.getDate()))
            .build(true)
            .toUri();

        ResponseEntity<EodMultipleResult> responseEntity = stockRestTemplate.getForEntity(uri, EodMultipleResult.class);
        if (responseEntity.getStatusCode().isError()) {
            log.error("StockData вернул ошибку: {}", responseEntity);
        }
        return responseEntity.getBody();
    }
}
