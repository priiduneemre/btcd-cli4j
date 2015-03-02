package com.neemre.btcdcli4j.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum ChainTypes {

	MAINNET("main"),
	TESTNET("test"),
	REGTEST("regtest");

	@Setter
	public String label;


	@JsonValue
	public String getLabel() {
		return label;
	}

	@JsonCreator
	public static ChainTypes forLabel(String label) {
		if(label != null) {
			for(ChainTypes chainType : ChainTypes.values()) {
				if(label.toLowerCase().equals(chainType.getLabel())) {
					return chainType;
				}
			}
		}
		return null;
	}
}