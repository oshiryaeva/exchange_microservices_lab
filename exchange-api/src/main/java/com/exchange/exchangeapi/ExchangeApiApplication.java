package com.exchange.exchangeapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.exchange.exchangeapi")
@EnableRabbit
@EnableFeignClients
@EnableTransactionManagement
public class ExchangeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApiApplication.class, args);
	}

}
