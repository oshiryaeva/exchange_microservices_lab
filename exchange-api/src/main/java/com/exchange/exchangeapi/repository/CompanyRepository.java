package com.exchange.exchangeapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.exchangeapi.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
}
