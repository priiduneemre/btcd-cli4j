package com.neemre.btcd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Commands {
	
	GET_INFO("getinfo");
	
	@Getter
	@Setter
	String name;
}
