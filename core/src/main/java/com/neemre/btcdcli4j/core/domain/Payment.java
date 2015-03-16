package com.neemre.btcdcli4j.core.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment extends PaymentOverview {

	private Integer confirmations;
	private Boolean generated;
	@JsonProperty("blockhash")
	private String blockHash;
	@JsonProperty("blockindex")
	private Integer blockIndex;
	@JsonProperty("blocktime")
	private Long blockTime;
	@JsonProperty("txid")
	private String txId;
	@JsonProperty("walletconflicts")
	private List<String> walletConflicts;
	private Long time;
	@JsonProperty("timereceived")
	private Long timeReceived;
	private String comment;
	private String to;
	@JsonProperty("otheraccount")
	private String otherAccount;
}