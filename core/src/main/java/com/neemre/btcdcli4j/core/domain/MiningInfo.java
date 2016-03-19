package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.core.common.Defaults;
import com.neemre.btcdcli4j.core.domain.enums.ChainTypes;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiningInfo extends Entity {

	private Integer blocks;
	@JsonProperty("currentblocksize")
	private Integer currentBlockSize;
	@JsonProperty("currentblocktx")
	private Integer currentBlockTx;
	@Setter(AccessLevel.NONE)
	private BigDecimal difficulty;
	private String errors;
	@JsonProperty("genproclimit")
	private Integer genProcLimit;
	@JsonProperty("networkhashps")
	private BigInteger networkHashPs;
	@JsonProperty("pooledtx")
	private Integer pooledTx;
	private Boolean testnet;
	private ChainTypes chain;
	private Boolean generate;
	@JsonProperty("hashespersec")
	private Long hashesPerSec;


	public MiningInfo(Integer blocks, Integer currentBlockSize, Integer currentBlockTx, 
			BigDecimal difficulty, String errors, Integer genProcLimit, BigInteger networkHashPs,
			Integer pooledTx, Boolean testnet, ChainTypes chain, Boolean generate, 
			Long hashesPerSec) {
		setBlocks(blocks);
		setCurrentBlockSize(currentBlockSize);
		setCurrentBlockTx(currentBlockTx);
		setDifficulty(difficulty);
		setErrors(errors);
		setGenProcLimit(genProcLimit);
		setNetworkHashPs(networkHashPs);
		setPooledTx(pooledTx);
		setTestnet(testnet);
		setChain(chain);
		setGenerate(generate);
		setHashesPerSec(hashesPerSec);
	}

	public void setDifficulty(BigDecimal difficulty) {
		this.difficulty = difficulty.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}