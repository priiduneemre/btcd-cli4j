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
public enum PeerControls {

	ADD("add"),
	REMOVE("remove"),
	ONETRY("onetry");

	private final String name;


	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static PeerControls forName(String name) {
		if (name != null) {
			for (PeerControls peerControl : PeerControls.values()) {
				if (name.equals(peerControl.getName())) {
					return peerControl;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_PEERCONTROL_UNSUPPORTED.getDescription());
	}
}