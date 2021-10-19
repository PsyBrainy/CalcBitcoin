package com.psybrainy.CalcBitcoin.service.impl;

import com.psybrainy.CalcBitcoin.api.BitcoinApi;
import com.psybrainy.CalcBitcoin.dto.BitcoinCalcDto;
import com.psybrainy.CalcBitcoin.dto.BitcoinDto;
import com.psybrainy.CalcBitcoin.persistence.BitcoinRepository;
import com.psybrainy.CalcBitcoin.persistence.entity.BitcoinMayorEntity;
import com.psybrainy.CalcBitcoin.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BitcoinServiceImpl implements BitcoinService {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinRepository repo;

    public Flux<BitcoinDto> getBitcoin(){

        return bitcoinApi.getBitcoin();
    }


    private Mono<Double> valorMaximo(){
        return Flux.merge(getBitcoin())
                .take(2)
                .collect(Collectors.maxBy(Comparator.comparing(BitcoinDto::getLprice)))
                .mapNotNull(Optional::orElseThrow)
                .flatMap(c -> Mono.just(c.getLprice()))
                .doOnNext(aDouble -> {
                    BitcoinMayorEntity bitcoinMayor = new BitcoinMayorEntity();

                    BitcoinMayorEntity bitcoinPersistence = repo.findAll().stream().max(Comparator.comparing(BitcoinMayorEntity::getValorMaximo)).orElse(new BitcoinMayorEntity(1L, 0.0));

                    bitcoinMayor.setValorMaximo(aDouble);
                    bitcoinMayor.setId(bitcoinMayor.getId());

                    if(aDouble > bitcoinPersistence.getValorMaximo() && bitcoinPersistence.getValorMaximo() != null){
                        repo.save(bitcoinMayor);
                    }
                }).map(aDouble -> repo
                        .findAll()
                        .stream()
                        .max(Comparator.comparing(BitcoinMayorEntity::getValorMaximo)).orElse(new BitcoinMayorEntity())
                        .getValorMaximo())
                .log();
    }

    @Override
    public Flux<BitcoinCalcDto> getCalc() {


        Flux<BitcoinDto> bitcoinDtoFlux = Flux.merge(getBitcoin());

        BitcoinCalcDto bitcoinCalcDto = new BitcoinCalcDto();

        Mono<Double> promedioUltimoTimestamp = Flux.merge(bitcoinDtoFlux)
                .take(2)
                .collect(Collectors.averagingDouble(BitcoinDto::getLprice)).log();



        return Flux.zip(promedioUltimoTimestamp, valorMaximo(),

                (promedio, valorMax) -> {

                    bitcoinCalcDto.setPromedioUltimoTimestamp(promedio);
                    bitcoinCalcDto.setValorMaximoRegistrado(valorMax);

                    Double diferenciaPorcentual = ((promedio - valorMax) / promedio)*100;

                    bitcoinCalcDto.setDiferenciaPorcentual("%" + diferenciaPorcentual);
                    bitcoinCalcDto.setActual(new Timestamp(System.currentTimeMillis()));

                    return bitcoinCalcDto;
                }

        ).repeat();

    }
}
