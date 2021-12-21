package com.exchange.exchangeapi.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.exchange.exchangeapi.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"currency", "type", "owner_id"})
})
public class Account {

	@Id
	@SequenceGenerator(name = "gen_account_id", sequenceName = "seq_account_id", allocationSize = 1)
	@GeneratedValue(generator = "gen_account_id", strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "balance", precision = 19, scale = 2, nullable = false)
	private BigDecimal balance;
	
	@Column(name = "currency", nullable = false)
	private String currency;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private AccountType type;
	
	@Column(name = "owner_id", nullable = false)
	private Long ownerId;
	
}
