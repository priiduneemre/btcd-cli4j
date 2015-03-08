package com.neemre.btcdcli4j.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum PaymentCategories {
	
	SEND("send"),
	RECEIVE("receive"),
	GENERATE("generate"),
	IMMATURE("immature"),
	ORPHAN("orphan"),
	MOVE("move");
	
	@Setter
	private String name;

	
	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static PaymentCategories forName(String name) {
		if(name != null) {
			for(PaymentCategories paymentCategory : PaymentCategories.values()) {
				if(name.toLowerCase().equals(paymentCategory.getName())) {
					return paymentCategory;
				}
			}
		}
		return null;
	}
}