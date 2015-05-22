package com.neemre.btcdcli4j.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ChainTypes {

	MAINNET("main"),
	TESTNET("test"),
	REGTEST("regtest");

	private final String name;


	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static ChainTypes forName(String name) {
		if(name != null) {
			for(ChainTypes chainType : ChainTypes.values()) {
				if(name.equals(chainType.getName())) {
					return chainType;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_CHAINTYPE_UNSUPPORTED.getDescription());
	}
}