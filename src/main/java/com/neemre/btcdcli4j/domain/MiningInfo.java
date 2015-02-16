package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiningInfo extends Entity {
	
	private Integer blocks;
	@JsonProperty("currentblocksize")
	private Integer currentBlockSize;
	@JsonProperty("currentblocktx")
	private Integer currentBlockTx;
	private BigDecimal difficulty;
	private String errors;
	@JsonProperty("genproclimit")
	private Integer genProcLimit;
	@JsonProperty("networkhashps")
	private Long networkHashPS;
	@JsonProperty("pooledtx")
	private Integer pooledTx;
	private Boolean testnet;
	private String chain;
	private Boolean generate;
	@JsonProperty("hashespersec")
	private Long hashesPerSec;
}