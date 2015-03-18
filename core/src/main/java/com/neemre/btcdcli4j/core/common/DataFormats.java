package com.neemre.btcdcli4j.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**An enumeration specifying the data formats recognized by btcd-cli4j.*/
@Getter
@ToString
@AllArgsConstructor
public enum DataFormats {
	
	HEX(0),
	JSON(1),
	PLAIN_TEXT(2),
	OBJECT(3);
	
	private final int code;
}