package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.common.Defaults;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
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
	@Setter(AccessLevel.NONE)
	private BigDecimal difficulty;
	@JsonProperty("chainwork")
	private String chainWork;
	@JsonProperty("previousblockhash")
	private String previousBlockHash;
	@JsonProperty("nextblockhash")
	private String nextBlockHash;
	
	
	public void setDifficulty(BigDecimal difficulty) {
		this.difficulty = difficulty.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}