package com.psybrainy.CalcBitcoin.controller.config;

import com.psybrainy.CalcBitcoin.dto.BitcoinCalcDto;
import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import com.psybrainy.CalcBitcoin.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterBitcoinConf {

    @Autowired
    private BitcoinService service;


    @Bean
    public RouterFunction<ServerResponse> getBitcoinRoute() {

        return route(GET("/bitcoin"),
                req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                           .body(service.getBitcoin(),
                        BitcoinDto.class))

                .and(route(GET("/bitcoin/calc"),
                        req -> ok().contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(service.getCalc(),
                                        BitcoinCalcDto.class)));
    }
}
