package com.exchange.converterapi.client.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class Currency {

    public Map<String, BigDecimal> rates;

    private boolean success;
    
    private Long timestamp;
    
    private Date date;
    
    private String base;
	
}