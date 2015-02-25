package com.neemre.btcdcli4j.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Errors {

	EXAMPLE(1, "");
	
	@Getter
	@Setter
	int code;
	@Getter
	@Setter
	String message;
}