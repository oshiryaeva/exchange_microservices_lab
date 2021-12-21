package com.exchange.exchangeapi.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.converter.name}", url = "${feign.converter.url}")
public interface ConverterClient {

	@RequestMapping(method = RequestMethod.GET, value = "/convert")
    BigDecimal convert(@RequestParam("amount") BigDecimal amount, @RequestParam("target") String target);
	
}
