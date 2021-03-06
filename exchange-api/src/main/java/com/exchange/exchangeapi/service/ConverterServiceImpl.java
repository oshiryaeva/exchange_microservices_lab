package com.exchange.exchangeapi.service;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.exchangeapi.client.ConverterClient;
import com.exchange.exchangeapi.queue.QueueProducer;
import com.exchange.exchangeapi.queue.model.ConversionRequest;
import com.exchange.exchangeapi.queue.model.ConversionRequestResult;

@Service
@Slf4j
public class ConverterServiceImpl implements ConverterService {
	
	@Autowired
	private ConverterClient converterClient;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private QueueProducer queueProducer;

	@Override
	public void convert(ConversionRequest conversionRequest) {
		
		if (!"EUR".equals(conversionRequest.getFrom())) {
			queueProducer.send(new ConversionRequestResult(conversionRequest.getId(), false));
			return;
		}
		
		BigDecimal calculatedToAmount = converterClient.convert(conversionRequest.getAmount(), conversionRequest.getTo());

		try {
			log.info("Starting transfer...");
			accountService.transfer(conversionRequest.getCompanyId(), conversionRequest.getTraderId(), conversionRequest.getAmount(), calculatedToAmount, conversionRequest.getFrom(), conversionRequest.getTo());
			log.info("Finishing transfer...");
		} catch (Exception e) {
			e.printStackTrace();
			queueProducer.send(new ConversionRequestResult(conversionRequest.getId(), false));
			return;
		}
		
		queueProducer.send(new ConversionRequestResult(conversionRequest.getId(), true));
	}

}
