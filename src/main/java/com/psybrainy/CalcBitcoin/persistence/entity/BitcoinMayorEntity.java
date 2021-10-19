package com.psybrainy.CalcBitcoin.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bitcoin_mayor_valor")
@Data
public class BitcoinMayorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorMaximo;

}
