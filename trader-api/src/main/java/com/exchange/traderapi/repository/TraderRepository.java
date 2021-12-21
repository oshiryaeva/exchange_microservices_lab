package com.exchange.traderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.traderapi.model.Trader;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long>{
	
	Trader findByUsername(String username);

}
