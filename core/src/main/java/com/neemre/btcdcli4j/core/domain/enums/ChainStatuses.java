package com.neemre.btcdcli4j.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;
import com.neemre.btcdcli4j.core.common.Errors;

@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ChainStatuses {

	ACTIVE("active"),
	INVALID("invalid"),
	HEADERS_ONLY("headers-only"),
	VALID_HEADERS("valid-headers"),
	VALID_FORK("valid-fork"),
	UNKNOWN("unknown");

	private final String name;


	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static ChainStatuses forName(String name) {
		if (name != null) {
			for (ChainStatuses chainStatus : ChainStatuses.values()) {
				if (name.equals(chainStatus.getName())) {
					return chainStatus;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_CHAINSTATUS_UNSUPPORTED.getDescription());
	}
}