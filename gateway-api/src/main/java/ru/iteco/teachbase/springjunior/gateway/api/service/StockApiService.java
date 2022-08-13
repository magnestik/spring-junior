package ru.iteco.teachbase.springjunior.gateway.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.iteco.teachbase.springjunior.gateway.api.model.EodMultipleRequest;

import java.net.URI;
import java.util.List;

@Component
public class StockApiService {
    private final RestTemplate restTemplate;
    private final TokenApiService tokenApiService;

    @Value("${service.stock.audience}")
    private String audience;

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${service.stock.uri}")
    private String stockUri;

    @Value("${service.stock.path.quoteByTickers}")
    private String quoteByTickersPath;

    @Value("${service.stock.path.endOfDayMultiple}")
    private String endOfDayMultiplePath;

    public StockApiService(RestTemplate restTemplate, TokenApiService tokenApiService) {
        this.restTemplate = restTemplate;
        this.tokenApiService = tokenApiService;
    }

    public String getQuoteByTickers(List<String> tickers) {
        return getApiResponse(quoteByTickersPath, tickers, tickers.getClass());
    }

    public String getEndOfDayMultiple(EodMultipleRequest eodMultipleRequest) {
        return getApiResponse(endOfDayMultiplePath, eodMultipleRequest, eodMultipleRequest.getClass());
    }

    private String getApiResponse(String path, Object body, Class<?> requestType) {
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        URI uriComponents = UriComponentsBuilder
            .fromHttpUrl(stockUri)
            .path(path)
            .build(true).toUri();
        RequestEntity<?> requestEntity = RequestEntity.post(uriComponents).headers(headers).body(requestType.cast(body));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uriComponents, requestEntity, String.class);
        return responseEntity.getBody();
    }
}
