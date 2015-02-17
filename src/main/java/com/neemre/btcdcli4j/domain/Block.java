package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Block extends Entity {
	
	private String hash;
	private Integer confirmations;
	private Integer size;
	private Integer height;
	private Integer version;
	@JsonProperty("merkleroot")
	private String merkleRoot;
	private List<String> tx;
	private Long time;
	private Integer nonce;
	private String bits;
	private BigDecimal difficulty;
	@JsonProperty("chainwork")
	private String chainWork;
	@JsonProperty("previousblockhash")
	private String previousBlockHash;
	@JsonProperty("nextblockhash")
	private String nextBlockHash;
}