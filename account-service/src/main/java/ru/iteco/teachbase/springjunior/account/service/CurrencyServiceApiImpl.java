package ru.iteco.teachbase.springjunior.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.iteco.teachbase.springjunior.account.model.dto.ConvertResult;
import ru.iteco.teachbase.springjunior.account.model.dto.ConverterRequest;
import ru.iteco.teachbase.springjunior.account.model.dto.LatestResult;

import java.util.UUID;

@Slf4j
@Component
public class CurrencyServiceApiImpl implements CurrencyServiceApi {
    private final RestTemplate restTemplateExchange;
    @Value("${apps.base-url.currency}")
    private String currencyUrl;

    public CurrencyServiceApiImpl(RestTemplate restTemplateExchange) {
        this.restTemplateExchange = restTemplateExchange;
    }

    @Override
    public LatestResult getAllExchangeApi() {
        String url = generateUrl("/latest");
        String traceId = UUID.randomUUID().toString();
        log.info("trace-id: {}", traceId);
        RequestEntity<Void> request = RequestEntity
            .get(url)
            .header("trace-id", traceId)
            .build();
        ResponseEntity<LatestResult> responseEntity = restTemplateExchange.exchange(request, LatestResult.class);
        if (responseEntity.getStatusCode().isError()) {
            log.error("Currency service returned error: {}", responseEntity);
        }
        return responseEntity.getBody();
    }

    private String generateUrl(String postfix) {
        return currencyUrl + postfix;
    }

    @Override
    public ConvertResult convert(ConverterRequest converterRequest) {
        String url = generateUrl("/convert");
        String traceId = UUID.randomUUID().toString();
        log.info("trace-id: {}", traceId);
        RequestEntity<ConverterRequest> request = RequestEntity
            .post(url)
            .header("trace-id", traceId)
            .body(converterRequest);

        return restTemplateExchange.exchange(request, ConvertResult.class).getBody();
    }
}
