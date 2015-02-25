package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.domain.PeerNode;

public interface BtcdClient {
	
	String encryptWallet(String passphrase);

	String getAccount(String address);
	
	String getAccountAddress(String account);
	
	BigDecimal getBalance();
	
	BigDecimal getBalance(String account);
	
	BigDecimal getBalance(String account, Integer confirmations);

	BigDecimal getBalance(String account, Integer confirmations, Boolean withWatchOnly);
	
	BigDecimal getDifficulty();
	
	Boolean getGenerate();
	
	Long getHashesPerSec();
	
	Info getInfo();
	
	MemPoolInfo getMemPoolInfo();
	
	MiningInfo getMiningInfo();

	String getNewAddress();
	
	String getNewAddress(String account);
	
	List<PeerNode> getPeerInfo();
	
	BigDecimal getReceivedByAccount(String account);

	BigDecimal getReceivedByAccount(String account, int confirmations);

	BigDecimal getReceivedByAddress(String address);

	BigDecimal getReceivedByAddress(String address, int confirmations);
	
	Map<String, BigDecimal> listAccounts();
	
	Map<String, BigDecimal> listAccounts(int confirmations);
	
	Map<String, BigDecimal> listAccounts(int confirmations, boolean withWatchOnly);
	
	void setGenerate(Boolean isGenerate);
	
	void setGenerate(Boolean isGenerate, Integer processors);
	
	String stop();
		
	void walletLock();

	void walletPassphrase(String passphrase, int authTimeout);

	void walletPassphraseChange(String curPassphrase, String newPassphrase);
}