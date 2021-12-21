package com.exchange.converterapi.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exchange.converterapi.client.model.Symbol;
import com.exchange.converterapi.client.model.Currency;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface FixerClient {

	@RequestMapping(method = RequestMethod.GET, value = "/latest")
    Currency currency(@RequestParam(value = "access_key") String accessKey);
	
	@RequestMapping(method = RequestMethod.GET, value = "/symbols")
    Symbol symbol(@RequestParam(value = "access_key") String accessKey);
	
}
