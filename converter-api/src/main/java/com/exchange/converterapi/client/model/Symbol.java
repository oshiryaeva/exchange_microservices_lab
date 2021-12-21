package com.exchange.converterapi.client.model;


import java.util.Map;

import lombok.Data;

@Data
public class Symbol {

	private boolean success;
	private Map<String, String> symbols;
	
}
