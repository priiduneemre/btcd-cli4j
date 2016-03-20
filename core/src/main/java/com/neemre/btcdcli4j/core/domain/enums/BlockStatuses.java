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
public enum BlockStatuses {

	ACCEPTED("null"),
	DUPLICATE("duplicate"),
	DUPLICATE_INVALID("duplicate-invalid"),
	DUPLICATE_INCONCLUSIVE("duplicate-inconclusive"),
	INCONCLUSIVE("inconclusive"),
	REJECTED("rejected");

	private final String name;


	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static BlockStatuses forName(String name) {
		if (name == null) {
			return ACCEPTED;
		} else {
			for (BlockStatuses blockStatus : BlockStatuses.values()) {
				if (name.equals(blockStatus.getName())) {
					return blockStatus;
				}
			}
		}
		throw new IllegalArgumentException(Errors.ARGS_BTCD_BLOCKSTATUS_UNSUPPORTED.getDescription());
	}
}