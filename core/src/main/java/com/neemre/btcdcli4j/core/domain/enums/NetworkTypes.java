package com.neemre.btcdcli4j.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum NetworkTypes {

	IPV4("ipv4"),
	IPV6("ipv6"),
	ONION("onion"),
	I2P("i2p"),
	EMPTY("");

	private final String name;


	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static NetworkTypes forName(String name) {
		if (name != null) {
			for (NetworkTypes networkType : NetworkTypes.values()) {
				if (name.equals(networkType.getName())) {
					return networkType;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_NETWORKTYPE_UNSUPPORTED.getDescription());
	}
}