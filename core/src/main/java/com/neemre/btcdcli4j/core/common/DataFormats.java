package com.neemre.btcdcli4j.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**An enumeration specifying the data formats recognized by btcd-cli4j.*/
@Getter
@ToString
@AllArgsConstructor
public enum DataFormats {
	
	HEX(0, "text/plain"),
	JSON(1, "application/json"), 
	PLAIN_TEXT(2, "text/plain");
	
	private final int code;
	private final String mediaType;
}