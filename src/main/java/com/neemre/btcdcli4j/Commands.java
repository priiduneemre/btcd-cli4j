package com.neemre.btcdcli4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Commands {
	
	ENCRYPT_WALLET("encryptwallet", 1, 1),
	GET_ACCOUNT("getaccount", 1, 1),
	GET_ACCOUNT_ADDRESS("getaccountaddress", 1, 1),
	GET_ADDRESSES_BY_ACCOUNT("getaddressesbyaccount", 1, 1),
	GET_BALANCE("getbalance", 0, 3),
	GET_BEST_BLOCK_HASH("getbestblockhash", 0, 0),
	GET_BLOCK_COUNT("getblockcount", 0, 0),
	GET_BLOCK_HASH("getblockhash", 1, 1),
	GET_DIFFICULTY("getdifficulty", 0, 0),
	GET_GENERATE("getgenerate", 0, 0),
	GET_HASHES_PER_SEC("gethashespersec", 0, 0),
	GET_INFO("getinfo", 0, 0),
	GET_MEM_POOL_INFO("getmempoolinfo", 0, 0),
	GET_MINING_INFO("getmininginfo", 0, 0),
	GET_NEW_ADDRESS("getnewaddress", 0, 1),
	GET_PEER_INFO("getpeerinfo", 0, 0),
	GET_RAW_CHANGE_ADDRESS("getrawchangeaddress", 0, 0),
	GET_RECEIVED_BY_ACCOUNT("getreceivedbyaccount", 1, 2),
	GET_RECEIVED_BY_ADDRESS("getreceivedbyaddress", 1, 2),
	GET_UNCONFIRMED_BALANCE("getunconfirmedbalance", 0, 0),
	GET_WALLET_INFO("getwalletinfo", 0, 0),
	KEY_POOL_REFILL("keypoolrefill", 0, 1),
	LIST_ACCOUNTS("listaccounts", 0, 2),
	SET_ACCOUNT("setaccount", 2, 2),
	SET_GENERATE("setgenerate", 1, 2),
	SET_TX_FEE("settxfee", 1, 1),
	STOP("stop", 0, 0),
	WALLET_LOCK("walletlock", 0, 0),
	WALLET_PASSPHRASE("walletpassphrase", 2, 2),
	WALLET_PASSPHRASE_CHANGE("walletpassphrasechange", 2, 2),
	
	
	IMPORT_ADDRESS("importaddress", -1, -1),
	IMPORT_PRIV_KEY("importprivkey", -1, -1),
	
	SIGN_MESSAGE("signmessage", -1, -1),
	VERIFY_MESSAGE("verifymessage", -1, -1),
	VALIDATE_ADDRESS("validateaddress", -1, -1),
	
	ADD_MULTI_SIG_ADDRESS("addmultisigaddress", -1, -1),
	BACKUP_WALLET("backupwallet", -1, -1),
	DUMP_PRIV_KEY("dumpprivkey", -1, -1),
	DUMP_WALLET("dumpwallet", -1, -1),
	GET_TRANSACTION("gettransaction", -1, -1),
	LIST_ADDRESS_GROUPINGS("listaddressgroupings", -1, -1),
	LIST_LOCK_UNSPENT("listlockunspent", -1, -1),
	LIST_RECEIVED_BY_ACCOUNT("listreceivedbyaccount", -1, -1),
	LIST_RECEIVED_BY_ADDRESS("listreceivedbyaddress", -1, -1),
	LIST_SINCE_BLOCK("listsinceblock", -1, -1),
	LIST_TRANSACTIONS("listtransactions", -1, -1),
	LIST_UNSPENT("listunspent", -1, -1),
	LOCK_UNSPENT("lockunspent", -1, -1),
	MOVE("move", -1, -1),
	SEND_FROM("sendfrom", -1, -1),
	SEND_MANY("sendmany", -1, -1),
	SEND_TO_ADDRESS("sendtoaddress", -1, -1),
	GET_BLOCK("getblock", -1, -1);
	
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