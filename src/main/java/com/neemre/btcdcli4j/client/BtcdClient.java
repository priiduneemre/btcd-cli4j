package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;

import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;

public interface BtcdClient {
	
	String encryptWallet(String passphrase);
	
	BigDecimal getBalance();
	
	BigDecimal getBalance(String account);
	
	BigDecimal getBalance(String account, Integer confirmations);

	BigDecimal getBalance(String account, Integer confirmations, Boolean hasWatchOnly);
	
	BigDecimal getDifficulty();
	
	Boolean getGenerate();
	
	Integer getHashesPerSec();
	
	Info getInfo();
	
	MemPoolInfo getMemPoolInfo();
	
	MiningInfo getMiningInfo();
	
	void setGenerate(Boolean isGenerate);
	
	void setGenerate(Boolean isGenerate, Integer processors);
	
	String stop();
		
	void walletLock();

	void walletPassphrase(String passphrase, int authTimeout);

	void walletPassphraseChange(String curPassphrase, String newPassphrase);
}