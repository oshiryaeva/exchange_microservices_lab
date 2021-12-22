package com.exchange.exchangeapi.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exchange.exchangeapi.queue.model.ConversionRequestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class QueueProducer {

	@Autowired
    private RabbitTemplate rabbitTemplate;


	@Value("${sender.queue.name}")
	private String queueName;
	
    public void send(ConversionRequestResult conversionRequestResult) {
    	try {
			rabbitTemplate.convertAndSend(queueName, new ObjectMapper().writeValueAsString(conversionRequestResult));
			log.info("Exchange send " + queueName + ", result: " + conversionRequestResult.toString());
		} catch (JsonProcessingException | AmqpException e) {
			e.printStackTrace();
		}
    }
	
}
