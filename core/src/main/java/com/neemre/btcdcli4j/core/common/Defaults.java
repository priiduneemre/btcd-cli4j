package com.neemre.btcdcli4j.core.common;

import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**A set of default values for commonly used call parameters.*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Defaults {
	
	public static final int DECIMAL_SCALE = 8;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	
	public static final int WALLET_AUTH_TIMEOUT = 60;
}