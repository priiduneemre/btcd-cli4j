package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemPoolTransaction extends Entity {

	private String txId;
	private Integer size;
	@Setter(AccessLevel.NONE)
	private BigDecimal fee;
	private Long time;
	private Integer height;
	@Setter(AccessLevel.NONE)
	@JsonProperty("startingpriority")
	private BigDecimal startingPriority;
	@Setter(AccessLevel.NONE)
	@JsonProperty("currentpriority")
	private BigDecimal currentPriority;
	private List<String> depends;


	public void setFee(BigDecimal fee) {
		this.fee = fee.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}

	public void setStartingPriority(BigDecimal startingPriority) {
		this.startingPriority = startingPriority.setScale(Defaults.DECIMAL_SCALE, 
				Defaults.ROUNDING_MODE);
	}

	public void setCurrentPriority(BigDecimal currentPriority) {
		this.currentPriority = currentPriority.setScale(Defaults.DECIMAL_SCALE, 
				Defaults.ROUNDING_MODE);
	}
}