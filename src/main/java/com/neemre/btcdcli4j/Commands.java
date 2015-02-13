package com.neemre.btcdcli4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Commands {
	
	GET_INFO("getinfo"),
	GET_DIFFICULTY("getdifficulty"),
	GET_GENERATE("getgenerate");
	
	@Getter
	@Setter
	String name;
}