package com.psybrainy.CalcBitcoin.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bitcoin_mayor_valor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitcoinMayorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorMaximo;

}
