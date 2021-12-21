package com.exchange.exchangeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.exchangeapi.model.Company;
import com.exchange.exchangeapi.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping(value = "/company")
	public List<Company> company() {
		return companyService.getCompany();
	}
	
}
