package com.neemre.btcdcli4j.common;

import java.math.RoundingMode;

public final class Defaults {
	
	public static final int DECIMAL_SCALE = 8;
	public static final RoundingMode DECIMAL_ROUNDING_MODE = RoundingMode.HALF_UP;
	
	public static final int WALLET_AUTH_TIMEOUT = 60;
	
	
	private Defaults() {}
}