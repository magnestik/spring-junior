package ru.iteco.teachbase.springjunior.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudGatewayConfig {

    private final TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory;
    @Value("${service.stock.uri}")
    private String stockUri;
    @Value("${service.currency.uri}")
    private String currencyUri;

    @Value("${service.currency.path.all-exchange}")
    private String allExchangePath;

    @Value("${service.stock.path.quoteByTickers}")
    private String quoteByTickers;

    public CloudGatewayConfig(TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory) {
        this.tokenRelayGatewayFilterFactory = tokenRelayGatewayFilterFactory;
    }

    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route("getQuoteByTickers", predicateSpec -> predicateSpec.path(quoteByTickers)
                .filters(gatewayFilterSpec ->
                    gatewayFilterSpec
                        .filter(tokenRelayGatewayFilterFactory.apply())
                        .removeRequestHeader("Cookie"))
                .uri(stockUri))
            .route("all-exchange", predicateSpec -> predicateSpec.path(allExchangePath)
                .filters(gatewayFilterSpec ->
                    gatewayFilterSpec
                        .filter(tokenRelayGatewayFilterFactory.apply())
                        .removeRequestHeader("Cookie"))
                .uri(currencyUri))
            .build();
    }
}
