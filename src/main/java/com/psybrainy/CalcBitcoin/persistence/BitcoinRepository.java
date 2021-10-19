package com.psybrainy.CalcBitcoin.persistence;

import com.psybrainy.CalcBitcoin.persistence.entity.BitcoinMayorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinRepository extends JpaRepository<BitcoinMayorEntity, Long> {

    BitcoinRepository findByValorMaximo(Double valorMaximo);
}
