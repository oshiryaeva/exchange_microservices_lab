package com.exchange.traderapi.model;

import java.io.Serializable;
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

import com.exchange.traderapi.enums.ConversionRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conversion_request")
public class ConversionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "gen_conversion_request_id", sequenceName = "seq_conversion_request_id", allocationSize = 1)
	@GeneratedValue(generator = "gen_conversion_request_id", strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "from", nullable = false)
	private String from;
	
	@Column(name = "to", nullable = false)
	private String to;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ConversionRequestStatus status;
	
	@Column(name = "company_id", nullable = false)
	private Long companyId;

	@Column(name = "trader_id", nullable = false)
	private Long traderId;
}