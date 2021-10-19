package com.psybrainy.CalcBitcoin.controller;

import com.psybrainy.CalcBitcoin.dto.BitcoinCalcDto;
import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import com.psybrainy.CalcBitcoin.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinController {

    @Autowired
    private BitcoinService repo;


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BitcoinDto> getBitcoin(){
        return repo.getBitcoin();
    }

    @GetMapping(path = "/calc" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BitcoinCalcDto> getCalc(){
        return repo.getCalc();
    }
}
