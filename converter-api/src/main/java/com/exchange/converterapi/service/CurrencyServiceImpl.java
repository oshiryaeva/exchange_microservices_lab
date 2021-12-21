package com.exchange.converterapi.service;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exchange.converterapi.client.FixerClient;
import com.exchange.converterapi.client.model.Currency;
import com.exchange.converterapi.client.model.Symbol;
import com.exchange.converterapi.constant.CurrencyConstant;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	
	@Autowired
	private FixerClient fixerClient;
	
	@Value("${feign.accessKey}")
	private String accessKey;

	@Override
	public Currency getCurrency(String base) {
		return fixerClient.currency(accessKey);
	}

	@Override
	public Symbol getSymbol() {
		return fixerClient.symbol(accessKey);
	}

	@Override
	public BigDecimal calculate(BigDecimal amount, String target) {
		Currency currency = getCurrency(CurrencyConstant.BASE_CURRENCY);
		BigDecimal bigDecimal = currency.getRates().get(target);
		MathContext mc = new MathContext(2);
		return bigDecimal.multiply(amount, mc);
	}

}
