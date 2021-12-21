package com.exchange.exchangeapi.service;

import com.exchange.exchangeapi.queue.model.ConversionRequest;

public interface ConverterService {
	
	void convert(ConversionRequest conversionRequest);

}
