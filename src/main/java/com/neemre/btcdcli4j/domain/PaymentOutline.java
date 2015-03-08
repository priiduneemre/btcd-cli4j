package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.enums.PaymentCategories;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOutline extends Entity {

	@JsonProperty("involvesWatchonly")
	public Boolean involvesWatchOnly;
	private String account;
	private String address;
	private PaymentCategories category;
	@Setter(AccessLevel.NONE)
	private BigDecimal amount;
	@JsonProperty("vout")
	private Integer vOut;
	@Setter(AccessLevel.NONE)
	private BigDecimal fee;
	
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}