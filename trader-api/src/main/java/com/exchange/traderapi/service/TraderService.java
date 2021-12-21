package com.exchange.traderapi.service;

import java.math.BigDecimal;

import com.exchange.traderapi.exception.TraderException;
import com.exchange.traderapi.model.ConversionRequest;
import com.exchange.traderapi.queue.model.ConversionRequestResult;

public interface TraderService {
	
	Long convert(BigDecimal amount, String from, String to, Long companyId)  throws TraderException ;
	
	void finalize(ConversionRequestResult conversionRequestResult);
	
	ConversionRequest conversionRequest(Long id);

}
