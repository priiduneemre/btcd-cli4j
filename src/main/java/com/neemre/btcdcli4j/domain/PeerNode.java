package com.neemre.btcdcli4j.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.common.Defaults;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeerNode extends Entity {
	
	private Integer id;
	private String addr;
	@JsonProperty("addrlocal")
	private String addrLocal;
	private String services;
	@JsonProperty("lastsend")
	private Long lastSend;
	@JsonProperty("lastrecv")
	private Long lastRecv;
	@JsonProperty("bytessent")
	private Long bytesSent;
	@JsonProperty("bytesrecv")
	private Long bytesRecv;
	@JsonProperty("conntime")
	private Long connTime;
	@Setter(AccessLevel.NONE)
	@JsonProperty("pingtime")
	private BigDecimal pingTime;
	@Setter(AccessLevel.NONE)
	@JsonProperty("pingwait")
	private BigDecimal pingWait;
	private Integer version;
	@JsonProperty("subver")
	private String subVer;
	private Boolean inbound;
	@JsonProperty("startingheight")
	private Integer startingHeight;
	@JsonProperty("banscore")
	private Integer banScore;
	@JsonProperty("synced_headers")
	private Integer syncedHeaders;
	@JsonProperty("synced_blocks")
	private Integer syncedBlocks;
	@JsonProperty("syncnode")
	private Boolean syncNode;
	@JsonProperty("inflight")
	private List<Integer> inFlight;
	private Boolean whitelisted;
	
	
	public void setPingTime(BigDecimal pingTime) {
		this.pingTime = pingTime.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}

	public void setPingWait(BigDecimal pingWait) {
		this.pingWait = pingWait.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}