package com.exchange.exchangeapi.queue;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exchange.exchangeapi.queue.model.ConversionRequestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class QueueProducer {

	@Autowired
    private RabbitTemplate rabbitTemplate;

	@Value("${sender.queue.name}")
	private String queueName;
	
    public void send(ConversionRequestResult conversionRequestResult) {
    	try {
			rabbitTemplate.convertAndSend(queueName, new ObjectMapper().writeValueAsString(conversionRequestResult));
		} catch (JsonProcessingException | AmqpException e) {
			e.printStackTrace();
		}
    }
	
}
