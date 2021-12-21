package com.exchange.exchangeapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.exchangeapi.model.Company;
import com.exchange.exchangeapi.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public List<Company> getCompany() {
		return companyRepository.findAll();
	}

}
