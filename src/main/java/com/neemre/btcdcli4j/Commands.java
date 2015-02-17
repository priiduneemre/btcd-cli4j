package com.neemre.btcdcli4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Commands {
	
	ENCRYPT_WALLET("encryptwallet", 1, 1),
	GET_DIFFICULTY("getdifficulty", 0, 0),
	GET_GENERATE("getgenerate", 0, 0),
	GET_HASHES_PER_SEC("gethashespersec", 0, 0),
	GET_INFO("getinfo", 0, 0),
	GET_MEM_POOL_INFO("getmempoolinfo", 0, 0),
	GET_MINING_INFO("getmininginfo", 0, 0),
	SET_GENERATE("setgenerate", 1, 2),
	STOP("stop", 0, 0),
	WALLET_LOCK("walletlock", 0, 0),
	WALLET_PASSPHRASE("walletpassphrase", 2, 2),
	WALLET_PASSPHRASE_CHANGE("walletpassphrasechange", 2, 2);
	
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