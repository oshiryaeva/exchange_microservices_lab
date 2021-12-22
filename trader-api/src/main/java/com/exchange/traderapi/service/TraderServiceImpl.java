package com.exchange.traderapi.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.traderapi.client.ExchangeClient;
import com.exchange.traderapi.client.model.Account;
import com.exchange.traderapi.enums.ConversionRequestStatus;
import com.exchange.traderapi.exception.TraderException;
import com.exchange.traderapi.model.ConversionRequest;
import com.exchange.traderapi.model.Trader;
import com.exchange.traderapi.queue.QueueProducer;
import com.exchange.traderapi.queue.model.ConversionRequestResult;
import com.exchange.traderapi.repository.ConversionRequestRepository;
import com.exchange.traderapi.repository.TraderRepository;
import com.exchange.traderapi.util.SecurityUtil;

@Service
public class TraderServiceImpl implements TraderService {

	@Autowired
	private ExchangeClient exchangeClient;
	
	@Autowired
	private TraderRepository traderRepository;
	
	@Autowired 
	private ConversionRequestRepository conversionRequestRepository;
	
	@Autowired
	private QueueProducer queueProducer;
	
	@Override
	public Long convert(BigDecimal amount, String from, String to, Long companyId) throws TraderException {
		String currentUsername = SecurityUtil.getCurrentUsername();
		Trader trader = traderRepository.findByUsername(currentUsername);
		
		if (trader == null) {
			throw new TraderException("Trader not found");
		}
		
		Account account = exchangeClient.account(trader.getId(), "TRADER", from);
		
		if (account == null) {
			throw new TraderException("Account not found");
		}
	
		ConversionRequest conversionRequest = new ConversionRequest(null, amount, from, to, ConversionRequestStatus.STARTED, companyId, trader.getId());
		conversionRequest = conversionRequestRepository.save(conversionRequest);
		
		try {
			checkBalance(amount, account);
			queueProducer.send(conversionRequest);
		} catch (Exception e) {
			conversionRequest.setStatus(ConversionRequestStatus.FAILED);
			conversionRequest = conversionRequestRepository.save(conversionRequest);
			throw new TraderException("Conversion request could not be send");
		}
		
		
		return conversionRequest.getId();
	}

	@Override
	public void finalize(ConversionRequestResult conversionRequestResult) {
		if (conversionRequestResult != null) {
			Optional<ConversionRequest> conversionRequestOptional = conversionRequestRepository.findById(conversionRequestResult.getId());
			
			if (conversionRequestOptional.isPresent()) {
				ConversionRequest conversionRequest = conversionRequestOptional.get();
				
				if (conversionRequestResult.isResult()) {
					conversionRequest.setStatus(ConversionRequestStatus.COMPLETED);
				} else {
					conversionRequest.setStatus(ConversionRequestStatus.REJECTED);
				}
				
				conversionRequestRepository.save(conversionRequest);
			}
		}
		
	}
	
	private void checkBalance(BigDecimal amount, Account account) throws TraderException {
		if (amount.compareTo(account.getBalance()) >= 0) {
			throw new TraderException("Account balance: insufficient funds");
		}
	}

	@Override
	public ConversionRequest conversionRequest(Long id) {
		return conversionRequestRepository.findById(id).get();
	}

}
