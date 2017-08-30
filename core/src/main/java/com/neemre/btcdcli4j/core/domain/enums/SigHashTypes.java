package com.neemre.btcdcli4j.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.neemre.btcdcli4j.core.common.Errors;

@ToString
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum SigHashTypes {

	ALL("ALL"),
	NONE("NONE"),
	SINGLE("SINGLE"),
	ALL_ACP("ALL|ANYONECANPAY"),
	NONE_ACP("NONE|ANYONECANPAY"),
	SINGLE_ACP("SINGLE|ANYONECANPAY");
	
	private final String name;

	
	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static SigHashTypes forName(String name) {
		if (name != null) {
			for (SigHashTypes sigHashType : SigHashTypes.values()) {
				if (name.equals(sigHashType.getName())) {
					return sigHashType;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_SIGHASHTYPE_UNSUPPORTED.getDescription());
	}
}