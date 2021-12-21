package com.exchange.traderapi.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exchange.traderapi.model.ConversionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueProducer {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Value("${sender.queue.name}")
	private String queueName;

    public void send(ConversionRequest conversionRequest) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(queueName, new ObjectMapper().writeValueAsString(conversionRequest));
    }
    
}
