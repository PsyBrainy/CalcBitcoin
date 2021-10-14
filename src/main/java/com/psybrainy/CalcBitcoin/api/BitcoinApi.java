package com.psybrainy.CalcBitcoin.api;

import com.google.gson.Gson;
import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BitcoinApi {

    private final WebClient webClient;

    @Autowired
    public BitcoinApi(@Qualifier("BitcoinApi") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<BitcoinDto> getBitcoin(){
        return this.webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .map(responce -> new Gson().fromJson(responce, BitcoinDto.class));
    }
}
