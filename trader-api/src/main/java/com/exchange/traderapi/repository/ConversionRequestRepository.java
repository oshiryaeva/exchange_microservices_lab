package com.exchange.traderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.traderapi.model.ConversionRequest;

@Repository
public interface ConversionRequestRepository extends JpaRepository<ConversionRequest, Long>{

}
