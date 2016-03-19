package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neemre.btcdcli4j.core.common.Defaults;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalletInfo extends Entity {

	@JsonProperty("walletversion")
	private Integer walletVersion;
	@Setter(AccessLevel.NONE)
	private BigDecimal balance;
	@JsonProperty("txcount")
	private Integer txCount;
	@JsonProperty("keypoololdest")
	private Long keypoolOldest;
	@JsonProperty("keypoolsize")
	private Integer keypoolSize;
	@JsonProperty("unlocked_until")
	private Long unlockedUntil;


	public WalletInfo(Integer walletVersion, BigDecimal balance, Integer txCount, Long keypoolOldest,
			Integer keypoolSize, Long unlockedUntil) {
		setWalletVersion(walletVersion);
		setBalance(balance);
		setTxCount(txCount);
		setKeypoolOldest(keypoolOldest);
		setKeypoolSize(keypoolSize);
		setUnlockedUntil(unlockedUntil);
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}