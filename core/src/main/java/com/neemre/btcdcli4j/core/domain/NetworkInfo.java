package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.core.common.Defaults;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkInfo extends Entity {

	private Integer version;
	@JsonProperty("subversion")
	private String subVersion;
	@JsonProperty("protocolversion")
	private Integer protocolVersion;
	@JsonProperty("localservices")
	private String localServices;
	@JsonProperty("timeoffset")
	private Integer timeOffset;
	private Integer connections;
	private List<Network> networks;
	@JsonProperty("relayfee")
	private BigDecimal relayFee;
	@JsonProperty("localaddresses")
	private List<NetworkAddress> localAddresses;


	public void setRelayFee(BigDecimal relayFee) {
		this.relayFee = relayFee.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}