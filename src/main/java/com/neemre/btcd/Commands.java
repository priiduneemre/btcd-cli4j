package com.neemre.btcd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Commands {
	
	GET_INFO("getinfo"),
	GET_DIFFICULTY("getdifficulty");
	
	@Getter
	@Setter
	String name;
}