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
public enum ScriptTypes {
	
	PUB_KEY("pubkey"),
	PUB_KEY_HASH("pubkeyhash"),
	SCRIPT_HASH("scripthash"),
	MULTISIG("multisig"),
	NULL_DATA("nulldata"),
	NONSTANDARD("nonstandard"),
	WITNESS_V0_KEYHASH("witness_v0_keyhash"),
	WITNESS_V0_SCRIPTHASH("witness_v0_scripthash"),
	WITNESS_V1_TAPROOT("witness_v1_taproot"),
	WITNESS_UNKNOWN("witness_unknown"),
	WITNESS_MWEB_PEGIN("witness_mweb_pegin"),
	WITNESS_MWEB_HOGADDR("witness_mweb_hogaddr");
	
	private final String name;

	
	@JsonValue
	public String getName() {
		return name;
	}	

	@JsonCreator
	public static ScriptTypes forName(String name) {
		if (name != null) {
			for (ScriptTypes scriptType : ScriptTypes.values()) {
				if (name.equals(scriptType.getName())) {
					return scriptType;
				}
			}
		}
		throw new IllegalArgumentException(String.format("%s - %s", Errors.ARGS_BTCD_SCRIPTTYPE_UNSUPPORTED.getDescription(), name));
	}
}