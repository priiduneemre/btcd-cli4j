package com.neemre.btcdcli4j.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum DataFormats {
	
	HEX(0),
	JSON(1),
	PLAIN_TEXT(2),
	OBJECT(3);
	
	
	@Getter
	@Setter
	private int code;
}