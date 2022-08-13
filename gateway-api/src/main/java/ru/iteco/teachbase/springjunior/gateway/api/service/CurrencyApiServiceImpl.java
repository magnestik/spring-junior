package ru.iteco.teachbase.springjunior.gateway.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.iteco.teachbase.springjunior.gateway.api.model.ConverterRequest;

import java.math.BigDecimal;

@Component
public class CurrencyApiServiceImpl implements CurrencyApiService {
    private final RestTemplate restTemplate;
    private final TokenApiService tokenApiService;

    @Value("${service.currency.audience}")
    private String audience;

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${service.currency.uri}")
    private String currencyUri;

    @Value("${service.currency.path.all-exchange}")
    private String allExchangePath;

    @Value("${service.currency.path.convert}")
    private String convertPath;

    public CurrencyApiServiceImpl(RestTemplate restTemplate, TokenApiService tokenApiService) {
        this.restTemplate = restTemplate;
        this.tokenApiService = tokenApiService;
    }

    @Override
    public String getAllExchange() {
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        RequestEntity<Void> requestEntity = RequestEntity
            .get(currencyUri + allExchangePath)
            .headers(headers)
            .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String convert() {
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        ConverterRequest converterRequest = ConverterRequest.builder()
            .from("RUB")
            .to("EUR")
            .amount(BigDecimal.TEN)
            .build();

        RequestEntity<ConverterRequest> requestEntity = RequestEntity
            .post(currencyUri + convertPath)
            .headers(headers)
            .body(converterRequest);

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        return responseEntity.getBody();
    }
}
