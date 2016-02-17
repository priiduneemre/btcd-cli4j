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
public enum CheckLevels {

	LEVEL_0(0),
	LEVEL_1(1),
	LEVEL_2(2),
	LEVEL_3(3),
	LEVEL_4(4);

	private final Integer identifier;


	@JsonValue
	public Integer getIdentifier() {
		return identifier;
	}

	@JsonCreator
	public static CheckLevels forIdentifier(Integer identifier) {
		if (identifier != null) {
			for (CheckLevels checkLevel : CheckLevels.values()) {
				if (identifier.equals(checkLevel.getIdentifier())) {
					return checkLevel;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_CHECKLEVEL_UNSUPPORTED.getDescription());
	}	
}