package com.exchange.traderapi.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exchange.traderapi.queue.model.ConversionRequestResult;
import com.exchange.traderapi.service.TraderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class QueueConsumer {

	@Autowired
	private TraderService traderService;

	@RabbitListener(queues = {"${receiver.queue.name}"})
    public void receive(String message) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		ConversionRequestResult conversionRequestResult = null;
		try {
			conversionRequestResult = objectMapper.readValue(message, ConversionRequestResult.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		traderService.finalize(conversionRequestResult);
    }
	
}
