package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.neemre.btcdcli4j.domain.Account;
import com.neemre.btcdcli4j.domain.Address;
import com.neemre.btcdcli4j.domain.AddressOutline;
import com.neemre.btcdcli4j.domain.AddressInfo;
import com.neemre.btcdcli4j.domain.Block;
import com.neemre.btcdcli4j.domain.DetailedTransaction;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.domain.Output;
import com.neemre.btcdcli4j.domain.PeerNode;
import com.neemre.btcdcli4j.domain.WalletInfo;

public interface BtcdClient {
	
	void backupWallet(String filePath);
	
	String dumpPrivKey(String address);
	
	void dumpWallet(String filePath);
	
	String encryptWallet(String passphrase);

	String getAccount(String address);
	
	String getAccountAddress(String account);
	
	List<String> getAddressesByAccount(String account);
	
	BigDecimal getBalance();
	
	BigDecimal getBalance(String account);
	
	BigDecimal getBalance(String account, Integer confirmations);

	BigDecimal getBalance(String account, Integer confirmations, Boolean withWatchOnly);
	
	String getBestBlockHash();
	
	Block getBlock(String headerHash);

	Object getBlock(String headerHash, Boolean isDecoded);
	
	Integer getBlockCount();
	
	String getBlockHash(Integer blockHeight);
	
	BigDecimal getDifficulty();
	
	Boolean getGenerate();
	
	Long getHashesPerSec();
	
	Info getInfo();
	
	MemPoolInfo getMemPoolInfo();
	
	MiningInfo getMiningInfo();

	String getNewAddress();
	
	String getNewAddress(String account);
	
	List<PeerNode> getPeerInfo();
	
	String getRawChangeAddress();
	
	BigDecimal getReceivedByAccount(String account);

	BigDecimal getReceivedByAccount(String account, Integer confirmations);

	BigDecimal getReceivedByAddress(String address);

	BigDecimal getReceivedByAddress(String address, Integer confirmations);
	
	DetailedTransaction getTransaction(String txId);

	DetailedTransaction getTransaction(String txId, Boolean withWatchOnly);
	
	BigDecimal getUnconfirmedBalance();
	
	WalletInfo getWalletInfo();
	
	void importAddress(String address);
	
	void importAddress(String address, String account);

	void importAddress(String address, String account, Boolean withRescan);
	
	void importPrivKey(String privateKey);

	void importPrivKey(String privateKey, String account);

	void importPrivKey(String privateKey, String account, Boolean withRescan);
	
	void importWallet(String filePath);
	
	void keyPoolRefill();

	void keyPoolRefill(Integer keypoolSize);
	
	Map<String, BigDecimal> listAccounts();
	
	Map<String, BigDecimal> listAccounts(Integer confirmations);
	
	Map<String, BigDecimal> listAccounts(Integer confirmations, Boolean withWatchOnly);
	
	List<List<AddressOutline>> listAddressGroupings();

	List<Output> listLockUnspent();
	
	List<Account> listReceivedByAccount();

	List<Account> listReceivedByAccount(Integer confirmations);

	List<Account> listReceivedByAccount(Integer confirmations, Boolean withUnused);

	List<Account> listReceivedByAccount(Integer confirmations, Boolean withUnused, 
			Boolean withWatchOnly);
	
	List<Address> listReceivedByAddress();

	List<Address> listReceivedByAddress(Integer confirmations);

	List<Address> listReceivedByAddress(Integer confirmations, Boolean withUnused);

	List<Address> listReceivedByAddress(Integer confirmations, Boolean withUnused,
			Boolean withWatchOnly);
	
	Boolean lockUnspent(Boolean isUnlocked);

	Boolean lockUnspent(Boolean isUnlocked, List<Output> outputs);
	
	Boolean move(String fromAccount, String toAccount, BigDecimal amount);

	Boolean move(String fromAccount, String toAccount, BigDecimal amount, Integer dummy, 
			String comment);
	
	void ping();
	
	String sendFrom(String fromAccount, String toAddress, BigDecimal amount);

	String sendFrom(String fromAccount, String toAddress, BigDecimal amount, Integer confirmations);

	String sendFrom(String fromAccount, String toAddress, BigDecimal amount, Integer confirmations,
			String comment);

	String sendFrom(String fromAccount, String toAddress, BigDecimal amount, Integer confirmations,
			String comment, String commentTo);
	
	String sendToAddress(String toAddress, BigDecimal amount);

	String sendToAddress(String toAddress, BigDecimal amount, String comment);

	String sendToAddress(String toAddress, BigDecimal amount, String comment, String commentTo);
	
	void setAccount(String address, String account);
	
	void setGenerate(Boolean isGenerate);
	
	void setGenerate(Boolean isGenerate, Integer processors);
	
	Boolean setTxFee(BigDecimal txFee);

	String signMessage(String address, String message);
	
	String stop();
	
	AddressInfo validateAddress(String address);
	
	Boolean verifyMessage(String address, String signature, String message);
	
	void walletLock();

	void walletPassphrase(String passphrase, Integer authTimeout);

	void walletPassphraseChange(String curPassphrase, String newPassphrase);
}