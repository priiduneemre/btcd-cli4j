package com.neemre.btcdcli4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Commands {
	
	GET_INFO("getinfo", 0, 0),
	GET_MINING_INFO("getmininginfo", 0, 0),
	GET_MEM_POOL_INFO("getmempoolinfo", 0, 0),
	GET_DIFFICULTY("getdifficulty", 0, 0),
	GET_GENERATE("getgenerate", 0, 0),
	SET_GENERATE("setgenerate", 1, 2),
	GET_HASHES_PER_SEC("gethashespersec", 0, 0);
	
	@Getter
	@Setter
	String name;
	@Getter
	@Setter
	int minParamCount;
	@Getter
	@Setter
	int maxParamCount;
}