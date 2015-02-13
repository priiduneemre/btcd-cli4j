package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info extends Entity {

	private Integer version;
	@JsonProperty("protocolversion")
	private Integer protocolVersion;
	@JsonProperty("walletversion")
	private Integer walletVersion;
	private BigDecimal balance;
	private Integer blocks;
	@JsonProperty("timeoffset")
	private Integer timeOffset;
	private Integer connections;
	private String proxy;
	private BigDecimal difficulty;
	@JsonProperty("testnet")
	private Boolean testNet;
	@JsonProperty("keypoololdest")
	private Long keyPoolOldest;
	@JsonProperty("keypoolsize")
	private Integer keyPoolSize;
	@JsonProperty("unlocked_until")
	private Long unlockedUntil;
	@JsonProperty("paytxfee")
	private BigDecimal payTxFee; 
	@JsonProperty("relayfee")
	private BigDecimal relayFee;
	private String errors;
}