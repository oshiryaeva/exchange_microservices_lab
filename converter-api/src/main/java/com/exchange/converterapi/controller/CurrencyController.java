package com.exchange.converterapi.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.converterapi.client.model.Currency;
import com.exchange.converterapi.client.model.Symbol;
import com.exchange.converterapi.service.CurrencyService;

@RestController
public class CurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	@GetMapping(value = "/currency")
	public Currency currency() {
		return currencyService.getCurrency(null);
	}
	
	@GetMapping(value = "/symbol")
	public Symbol symbol() {
		return currencyService.getSymbol();
	}
	
	@GetMapping(value = "/convert")
	public BigDecimal convert(@RequestParam BigDecimal amount, @RequestParam String target) {
		return currencyService.calculate(amount, target);
	}

}
