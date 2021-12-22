package com.exchange.exchangeapi.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String message; 

}
