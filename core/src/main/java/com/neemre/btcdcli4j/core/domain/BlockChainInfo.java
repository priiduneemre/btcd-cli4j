package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.neemre.btcdcli4j.core.common.Defaults;
import com.neemre.btcdcli4j.core.domain.enums.ChainTypes;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockChainInfo extends Entity {

	private ChainTypes chain;
	private Integer blocks;
	private Integer headers;
	@JsonProperty("bestblockhash")
	private String bestBlockHash;
	@Setter(AccessLevel.NONE)
	private BigDecimal difficulty;
	@Setter(AccessLevel.NONE)
	@JsonProperty("verificationprogress")
	private BigDecimal verificationProgress;
	@JsonProperty("chainwork")
	private String chainWork;


	public BlockChainInfo(ChainTypes chain, Integer blocks, Integer headers, String bestBlockHash,
			BigDecimal difficulty, BigDecimal verificationProgress, String chainWork) {
		setChain(chain);
		setBlocks(blocks);
		setHeaders(headers);
		setBestBlockHash(bestBlockHash);
		setDifficulty(difficulty);
		setVerificationProgress(verificationProgress);
		setChainWork(chainWork);
	}

	public void setDifficulty(BigDecimal difficulty) {
		this.difficulty = difficulty.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}

	public void setVerificationProgress(BigDecimal verificationProgress) {
		this.verificationProgress = verificationProgress.setScale(Defaults.DECIMAL_SCALE, 
				Defaults.ROUNDING_MODE);
	}
}