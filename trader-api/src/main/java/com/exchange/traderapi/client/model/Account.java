package com.exchange.traderapi.client.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account {

	private Long id;
	
	private BigDecimal balance;
	
	private String currency;
	
	private String type;
	
	private Long ownerId;
}
