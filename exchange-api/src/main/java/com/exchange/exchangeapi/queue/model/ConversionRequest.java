package com.exchange.exchangeapi.queue.model;

import java.io.Serializable;
import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConversionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private BigDecimal amount;
	
	private String from;
	
	private String to;
	
	private ConversionRequestStatus status;
	
	private Long companyId;
	
	private Long traderId;


}