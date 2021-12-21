package com.exchange.traderapi.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.traderapi.exception.TraderException;
import com.exchange.traderapi.service.TraderService;

@RestController
public class TradeController {
	
	@Autowired
	private TraderService traderService;
	
	@GetMapping(value = "/convertRequest")
	public ResponseEntity<?> convert(@RequestParam("amount") BigDecimal amount, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("companyId") Long companyId) throws TraderException {
		return new ResponseEntity<>(traderService.convert(amount, from, to, companyId) + " number conversion request has been created", HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getConvertRequest")
	public ResponseEntity<?> convert(@RequestParam("id") Long id) throws TraderException {
		return new ResponseEntity<>(traderService.conversionRequest(id), HttpStatus.OK);
	}

}
