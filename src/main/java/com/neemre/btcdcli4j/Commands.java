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
	WALLET_PASSPHRASE_CHANGE("walletpassphrasechange", 2, 2),
	
	LIST_ACCOUNTS("listaccounts", 0, 2),
	GET_NEW_ADDRESS("getnewaddress", 0, 1),
	GET_BALANCE("getbalance", 0, 3),
	GET_ACCOUNT("getaccount", 1, 1),
	GET_ACCOUNT_ADDRESS("getaccountaddress", 1, 1),
	GET_RECEIVED_BY_ACCOUNT("getreceivedbyaccount", 1, 2),
	GET_RECEIVED_BY_ADDRESS("getreceivedbyaddress", 1, 2),
	GET_ADDRESSES_BY_ACCOUNT("getaddressesbyaccount", 1, 1),
	GET_PEER_INFO("getpeerinfo", 0, 0),
	SET_ACCOUNT("setaccount", 2, 2),
	SET_TX_FEE("settxfee", 1, 1);
	
	
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