package com.exchange.exchangeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.exchangeapi.enums.AccountType;
import com.exchange.exchangeapi.model.Account;
import com.exchange.exchangeapi.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = "/account")
	public Account account(@RequestParam Long ownerId, @RequestParam String type, @RequestParam String currency) {
		return accountService.find(ownerId, AccountType.valueOf(type), currency);
	}

}
