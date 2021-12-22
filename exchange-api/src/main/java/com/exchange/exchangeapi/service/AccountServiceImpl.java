package com.exchange.exchangeapi.service;



import java.math.BigDecimal;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exchange.exchangeapi.enums.AccountType;
import com.exchange.exchangeapi.exception.ExchangeException;
import com.exchange.exchangeapi.model.Account;
import com.exchange.exchangeapi.repository.AccountRepository;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account finById(Long id) {
		return accountRepository.findById(id).get();
	}

	@Override
	public Account find(Long ownerId, AccountType accountType, String currency) {
		return accountRepository.findByOwnerIdAndTypeAndCurrency(ownerId, accountType, currency);
	}

	@Override
	@Transactional
	public void transfer(Long companyId, Long traderId, BigDecimal fromAmount, BigDecimal toAmount, String fromCurrency,
			String toCurrency) throws ExchangeException {
		
		Account toCompanyAccount = accountRepository.findByOwnerIdAndTypeAndCurrency(companyId, AccountType.COMPANY, toCurrency);
		
		if (toAmount.compareTo(toCompanyAccount.getBalance()) >= 0) {
			log.info("Company account: insufficient funds");
			throw new ExchangeException("Company account: insufficient funds");
		}
		
		toCompanyAccount.setBalance(toCompanyAccount.getBalance().subtract(toAmount));
		log.info("Transfer: TO company account balance " + toCompanyAccount.getBalance());
		accountRepository.save(toCompanyAccount);
		
		Account fromCompanyAccount = accountRepository.findByOwnerIdAndTypeAndCurrency(companyId, AccountType.COMPANY, fromCurrency);
		fromCompanyAccount.setBalance(fromCompanyAccount.getBalance().add(fromAmount));
		log.info("Transfer: FROM company account balance " + fromCompanyAccount.getBalance());
		accountRepository.save(fromCompanyAccount);

		Account toTraderAccount = accountRepository.findByOwnerIdAndTypeAndCurrency(traderId, AccountType.TRADER, toCurrency);
		toTraderAccount.setBalance(toTraderAccount.getBalance().add(toAmount));
		log.info("Transfer: TO trader account balance " + toTraderAccount.getBalance());
		accountRepository.save(toTraderAccount);
		
		Account fromTraderAccount = accountRepository.findByOwnerIdAndTypeAndCurrency(traderId, AccountType.TRADER, fromCurrency);
		
		if (fromAmount.compareTo(fromTraderAccount.getBalance()) >= 0) {
			log.info("Trader account: insufficient funds");
			throw new ExchangeException("Trader account: insufficient funds");
		}
		
		fromTraderAccount.setBalance(fromTraderAccount.getBalance().subtract(fromAmount));
		log.info("Transfer: FROM trader account balance " + fromTraderAccount.getBalance());
		accountRepository.save(fromTraderAccount);
		
	}

}
