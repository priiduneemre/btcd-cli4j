package com.neemre.btcdcli4j.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ChainTypes {

	MAINNET("main"),
	TESTNET("test"),
	REGTEST("regtest");

	@Setter
	public String name;


	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static ChainTypes forName(String name) {
		if(name != null) {
			for(ChainTypes chainType : ChainTypes.values()) {
				if(name.toLowerCase().equals(chainType.getName())) {
					return chainType;
				}
			}
		}
		return null;
	}
}