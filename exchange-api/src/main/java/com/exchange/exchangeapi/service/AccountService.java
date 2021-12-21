package com.exchange.exchangeapi.service;

import java.math.BigDecimal;

import com.exchange.exchangeapi.enums.AccountType;
import com.exchange.exchangeapi.exception.ExchangeException;
import com.exchange.exchangeapi.model.Account;

public interface AccountService {
	
	Account finById(Long id);
	
	Account find(Long ownerId, AccountType accountType, String currency);
	
	void transfer(Long companyId, Long traderId, BigDecimal fromAmount, BigDecimal toAmount, String fromCurrency, String toCurrency) throws ExchangeException;
	
}
