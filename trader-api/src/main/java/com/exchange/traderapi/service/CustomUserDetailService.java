package com.exchange.traderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exchange.traderapi.model.Trader;
import com.exchange.traderapi.repository.TraderRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private TraderRepository traderRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trader trader = traderRepository.findByUsername(username);
		
		if (trader == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new CustomUserDetail(trader.getUsername(), trader.getPassword());
	}

}
