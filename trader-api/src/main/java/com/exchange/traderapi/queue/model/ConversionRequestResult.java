package com.exchange.traderapi.queue.model;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConversionRequestResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private boolean result;
	
}
