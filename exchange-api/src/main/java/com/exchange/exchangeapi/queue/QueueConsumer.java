package com.exchange.exchangeapi.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exchange.exchangeapi.queue.model.ConversionRequest;
import com.exchange.exchangeapi.service.ConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableRabbit
@Component
@Slf4j
public class QueueConsumer {
	
	@Autowired
	private ConverterService converterService;

	@RabbitListener(queues = {"${receiver.queue.name}"})
    public void receive(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        ConversionRequest conversionRequest = null;
		try {
			conversionRequest = objectMapper.readValue(message, ConversionRequest.class);
			log.info("Exchange receive " + conversionRequest.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
        converterService.convert(conversionRequest);
    }
	
}
