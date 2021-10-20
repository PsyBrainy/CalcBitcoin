package com.psybrainy.CalcBitcoin.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BitcoinCalcDto {

    private Double promedioUltimoTimestamp;
    private Double valorMaximoRegistrado;
    private String diferenciaPorcentual;
    private Timestamp timestamp;
}
