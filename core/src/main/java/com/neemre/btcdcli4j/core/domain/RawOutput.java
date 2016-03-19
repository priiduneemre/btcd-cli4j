package com.neemre.btcdcli4j.core.domain;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.neemre.btcdcli4j.core.common.Defaults;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawOutput extends Entity {

	@Setter(AccessLevel.NONE)
	private BigDecimal value;
	private Integer n;
	private PubKeyScript scriptPubKey;


	public RawOutput(BigDecimal value, Integer n, PubKeyScript scriptPubKey) {
		setValue(value);
		setN(n);
		setScriptPubKey(scriptPubKey);
	}

	public void setValue(BigDecimal value) {
		this.value = value.setScale(Defaults.DECIMAL_SCALE, Defaults.ROUNDING_MODE);
	}
}