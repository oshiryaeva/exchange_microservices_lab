package com.exchange.converterapi.service;

import java.math.BigDecimal;

import com.exchange.converterapi.client.model.Currency;
import com.exchange.converterapi.client.model.Symbol;


public interface CurrencyService {

	Currency getCurrency(String base);
	
	Symbol getSymbol();
	
	BigDecimal calculate(BigDecimal amount, String target);
	
}
