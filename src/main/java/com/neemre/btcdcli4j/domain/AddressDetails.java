package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.jsonrpc.deserialization.AddressDetailsDeserializer;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = AddressDetailsDeserializer.class)
public class AddressDetails extends Entity {
	
	private String address;
	@Setter(AccessLevel.NONE)
	private BigDecimal balance;
	private String account;
	
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}