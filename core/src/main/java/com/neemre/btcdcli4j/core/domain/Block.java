package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.core.common.Defaults;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
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
	private Long nonce;
	private String bits;
	@Setter(AccessLevel.NONE)
	private BigDecimal difficulty;
	@JsonProperty("chainwork")
	private String chainWork;
	@JsonProperty("previousblockhash")
	private String previousBlockHash;
	@JsonProperty("nextblockhash")
	private String nextBlockHash;


	public Block(String hash, Integer confirmations, Integer size, Integer height, Integer version,
			String merkleRoot, List<String> tx, Long time, Long nonce, String bits, 
			BigDecimal difficulty, String chainWork, String previousBlockHash, String nextBlockHash) {
		setHash(hash);
		setConfirmations(confirmations);
		setSize(size);
		setHeight(height);
		setVersion(version);
		setMerkleRoot(merkleRoot);
		setTx(tx);
		setTime(time);
		setNonce(nonce);
		setBits(bits);
		setDifficulty(difficulty);
		setChainWork(chainWork);
		setPreviousBlockHash(previousBlockHash);
		setNextBlockHash(nextBlockHash);
	}

	public void setDifficulty(BigDecimal difficulty) {
		this.difficulty = difficulty.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}