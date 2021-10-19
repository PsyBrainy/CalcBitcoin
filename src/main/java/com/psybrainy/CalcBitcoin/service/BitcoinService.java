package com.psybrainy.CalcBitcoin.service;

import com.psybrainy.CalcBitcoin.dto.BitcoinCalcDto;
import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface BitcoinService {

    Flux<BitcoinDto> getBitcoin();
    Flux<BitcoinCalcDto> getCalc();
}
