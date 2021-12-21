package com.exchange.exchangeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.exchangeapi.enums.AccountType;
import com.exchange.exchangeapi.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByOwnerIdAndTypeAndCurrency(Long ownerId, AccountType type, String currency);

}
