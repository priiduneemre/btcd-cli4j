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
public enum TransactionCategories {
	
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
	public static TransactionCategories forName(String name) {
		if(name != null) {
			for(TransactionCategories transactionCategory : TransactionCategories.values()) {
				if(name.toLowerCase().equals(transactionCategory.getName())) {
					return transactionCategory;
				}
			}
		}
		return null;
	}
}