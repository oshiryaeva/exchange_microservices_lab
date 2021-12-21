package com.exchange.traderapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exchange.traderapi.client.model.Account;


@FeignClient(name = "${feign.exchange.name}", url = "${feign..exchange.url}")
public interface ExchangeClient {

	@RequestMapping(method = RequestMethod.GET, value = "/account")
    Account account(@RequestParam(value = "ownerId") Long ownerId, @RequestParam(value = "type") String accountType, @RequestParam(value = "currency") String currency);
	
}
