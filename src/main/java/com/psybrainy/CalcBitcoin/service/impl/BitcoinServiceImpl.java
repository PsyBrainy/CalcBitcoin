package com.psybrainy.CalcBitcoin.service.impl;

import com.psybrainy.CalcBitcoin.api.BitcoinApi;
import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import com.psybrainy.CalcBitcoin.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class BitcoinServiceImpl implements BitcoinService {

    @Autowired
    private BitcoinApi repo;

    public Flux<BitcoinDto> getBitcoin(){

        return repo.getBitcoin().delaySubscription(Duration.ofSeconds(1)).repeat();
    }


}