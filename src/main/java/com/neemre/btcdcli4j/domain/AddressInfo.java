package com.neemre.btcdcli4j.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.domain.enums.ScriptTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInfo extends Entity {
	
	@JsonProperty("isvalid")
	private Boolean isValid;
	private String address;
	@JsonProperty("ismine")
	private Boolean isMine;
	@JsonProperty("iswatchonly")
	private Boolean isWatchOnly;
	@JsonProperty("isscript")
	private Boolean isScript;
	private ScriptTypes script;
	private String hex;
	private List<String> addresses;
	@JsonProperty("sigrequired")
	private Integer sigRequired;
	@JsonProperty("pubkey")
	private String pubKey;
	@JsonProperty("iscompressed")
	private Boolean isCompressed;
	private String account;
}