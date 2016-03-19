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
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.core.common.Defaults;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxOutSetInfo extends Entity {

	private Integer height;
	@JsonProperty("bestblock")
	private String bestBlock;
	private Long transactions;
	@JsonProperty("txouts")
	private Long txOuts;
	@JsonProperty("bytes_serialized")
	private Long bytesSerialized;
	@JsonProperty("hash_serialized")
	private String hashSerialized;
	@Setter(AccessLevel.NONE)
	@JsonProperty("total_amount")
	private BigDecimal totalAmount;


	public TxOutSetInfo(Integer height, String bestBlock, Long transactions, Long txOuts, 
			Long bytesSerialized, String hashSerialized, BigDecimal totalAmount) {
		setHeight(height);
		setBestBlock(bestBlock);
		setTransactions(transactions);
		setTxOuts(txOuts);
		setBytesSerialized(bytesSerialized);
		setHashSerialized(hashSerialized);
		setTotalAmount(totalAmount);
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}