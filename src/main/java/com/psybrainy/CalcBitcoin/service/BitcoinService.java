package com.psybrainy.CalcBitcoin.service;

import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface BitcoinService {

    Flux<BitcoinDto> getBitcoin();
}
