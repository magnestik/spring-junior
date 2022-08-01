package ru.iteco.teachbase.springjunior.currency.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.iteco.teachbase.springjunior.currency.model.ConvertResult;
import ru.iteco.teachbase.springjunior.currency.model.ConverterRequest;
import ru.iteco.teachbase.springjunior.currency.model.LatestResult;

@Service
public class FixerApiImpl implements FixerApi {
    private final RestTemplate restTemplateExchange;
    @Value("${currency.token}")
    private String token;
    @Value("${currency.fixer-api-url.convert}")
    private String convertUrl;
    @Value("${currency.fixer-api-url.latest}")
    private String latestUrl;

    public FixerApiImpl(RestTemplate restTemplateExchange) {
        this.restTemplateExchange = restTemplateExchange;
    }

    @Override
    public ConvertResult convert(ConverterRequest request) {
        RequestEntity<Void> requestEntity = RequestEntity
            .get(convertUrl, request.getTo(), request.getFrom(), request.getAmount())
            .header("apikey", token)
            .build();
        ResponseEntity<ConvertResult> responseEntity = restTemplateExchange.exchange(requestEntity, ConvertResult.class);
        return responseEntity.getBody();
    }

    @Override
    public LatestResult latestByRub() {
        RequestEntity<Void> requestEntity = RequestEntity
            .get(latestUrl)
            .header("apikey", token)
            .build();
        ResponseEntity<LatestResult> responseEntity = restTemplateExchange.exchange(requestEntity, LatestResult.class);
        return responseEntity.getBody();
    }
}
