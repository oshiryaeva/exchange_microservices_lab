package com.exchange.traderapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class QueueSenderConfig {

	@Value("${sender.queue.name}")
	private String name;
	
    @Bean
    public Queue queue() {
        return new Queue(name, true);
    }
}
