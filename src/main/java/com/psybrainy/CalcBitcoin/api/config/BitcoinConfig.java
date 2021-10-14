package com.psybrainy.CalcBitcoin.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BitcoinConfig {

    @Bean("BitcoinApi")
    public WebClient getWebClient(){
        return WebClient.builder().baseUrl("https://cex.io/api/last_price/BTC/USD").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
