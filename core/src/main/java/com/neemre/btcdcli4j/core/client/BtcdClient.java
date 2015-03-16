package com.neemre.btcdcli4j.core.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.domain.Account;
import com.neemre.btcdcli4j.core.domain.Address;
import com.neemre.btcdcli4j.core.domain.AddressInfo;
import com.neemre.btcdcli4j.core.domain.AddressOverview;
import com.neemre.btcdcli4j.core.domain.Block;
import com.neemre.btcdcli4j.core.domain.Info;
import com.neemre.btcdcli4j.core.domain.MemPoolInfo;
import com.neemre.btcdcli4j.core.domain.MiningInfo;
import com.neemre.btcdcli4j.core.domain.MultiSigAddress;
import com.neemre.btcdcli4j.core.domain.Output;
import com.neemre.btcdcli4j.core.domain.OutputOverview;
import com.neemre.btcdcli4j.core.domain.Payment;
import com.neemre.btcdcli4j.core.domain.PeerNode;
import com.neemre.btcdcli4j.core.domain.RawTransaction;
import com.neemre.btcdcli4j.core.domain.RedeemScript;
import com.neemre.btcdcli4j.core.domain.SignatureResult;
import com.neemre.btcdcli4j.core.domain.SinceBlock;
import com.neemre.btcdcli4j.core.domain.Transaction;
import com.neemre.btcdcli4j.core.domain.WalletInfo;

public interface BtcdClient {
	
	String addMultiSigAddress(Integer minSignatures, List<String> addresses) 
			throws BitcoindException, CommunicationException;

	String addMultiSigAddress(Integer minSignatures, List<String> addresses, String account) 
			throws BitcoindException, CommunicationException;
	
	void backupWallet(String filePath) throws BitcoindException, CommunicationException;
	
	MultiSigAddress createMultiSig(Integer minSignatures, List<String> addresses) 
			throws BitcoindException, CommunicationException;
	
	String createRawTransaction(List<OutputOverview> outputs, Map<String, BigDecimal> toAddresses) 
			throws BitcoindException, CommunicationException;
	
	RawTransaction decodeRawTransaction(String hexTransaction) throws BitcoindException, 
			CommunicationException;
	
	RedeemScript decodeScript(String hexRedeemScript) throws BitcoindException, 
			CommunicationException;
	
	String dumpPrivKey(String address) throws BitcoindException, CommunicationException;
	
	void dumpWallet(String filePath) throws BitcoindException, CommunicationException;
	
	String encryptWallet(String passphrase) throws BitcoindException, CommunicationException;

	String getAccount(String address) throws BitcoindException, CommunicationException;
	
	String getAccountAddress(String account) throws BitcoindException, CommunicationException;
	
	List<String> getAddressesByAccount(String account) throws BitcoindException, 
			CommunicationException;
	
	BigDecimal getBalance() throws BitcoindException, CommunicationException;
	
	BigDecimal getBalance(String account) throws BitcoindException, CommunicationException;
	
	BigDecimal getBalance(String account, Integer confirmations) throws BitcoindException, 
			CommunicationException;

	BigDecimal getBalance(String account, Integer confirmations, Boolean withWatchOnly) 
			throws BitcoindException, CommunicationException;
	
	String getBestBlockHash() throws BitcoindException, CommunicationException;
	
	Block getBlock(String headerHash) throws BitcoindException, CommunicationException;

	Object getBlock(String headerHash, Boolean isDecoded) throws BitcoindException, 
			CommunicationException;

	Integer getBlockCount() throws BitcoindException, CommunicationException;
	
	String getBlockHash(Integer blockHeight) throws BitcoindException, CommunicationException;
	
	BigDecimal getDifficulty() throws BitcoindException, CommunicationException;
	
	Boolean getGenerate() throws BitcoindException, CommunicationException;
	
	Long getHashesPerSec() throws BitcoindException, CommunicationException;
	
	Info getInfo() throws BitcoindException, CommunicationException;
	
	MemPoolInfo getMemPoolInfo() throws BitcoindException, CommunicationException;
	
	MiningInfo getMiningInfo() throws BitcoindException, CommunicationException;

	String getNewAddress() throws BitcoindException, CommunicationException;
	
	String getNewAddress(String account) throws BitcoindException, CommunicationException;
	
	List<PeerNode> getPeerInfo() throws BitcoindException, CommunicationException;
	
	String getRawChangeAddress() throws BitcoindException, CommunicationException;
	
	String getRawTransaction(String txId) throws BitcoindException, CommunicationException;

	Object getRawTransaction(String txId, Integer verbosity) throws BitcoindException, 
			CommunicationException;
	
	BigDecimal getReceivedByAccount(String account) throws BitcoindException, 
			CommunicationException;

	BigDecimal getReceivedByAccount(String account, Integer confirmations) throws BitcoindException, 
			CommunicationException;

	BigDecimal getReceivedByAddress(String address) throws BitcoindException, CommunicationException;

	BigDecimal getReceivedByAddress(String address, Integer confirmations) throws BitcoindException,
			CommunicationException;
	
	Transaction getTransaction(String txId) throws BitcoindException, CommunicationException;

	Transaction getTransaction(String txId, Boolean withWatchOnly) throws BitcoindException, 
			CommunicationException;
	
	BigDecimal getUnconfirmedBalance() throws BitcoindException, CommunicationException;
	
	WalletInfo getWalletInfo() throws BitcoindException, CommunicationException;
	
	void importAddress(String address) throws BitcoindException, CommunicationException;
	
	void importAddress(String address, String account) throws BitcoindException, 
			CommunicationException;

	void importAddress(String address, String account, Boolean withRescan) throws BitcoindException,
			CommunicationException;
	
	void importPrivKey(String privateKey) throws BitcoindException, CommunicationException;

	void importPrivKey(String privateKey, String account) throws BitcoindException, 
			CommunicationException;

	void importPrivKey(String privateKey, String account, Boolean withRescan) 
			throws BitcoindException, CommunicationException;
	
	void importWallet(String filePath) throws BitcoindException, CommunicationException;
	
	void keyPoolRefill() throws BitcoindException, CommunicationException;

	void keyPoolRefill(Integer keypoolSize) throws BitcoindException, CommunicationException;
	
	Map<String, BigDecimal> listAccounts() throws BitcoindException, CommunicationException;
	
	Map<String, BigDecimal> listAccounts(Integer confirmations) throws BitcoindException, 
			CommunicationException;
	
	Map<String, BigDecimal> listAccounts(Integer confirmations, Boolean withWatchOnly) 
			throws BitcoindException, CommunicationException;
	
	List<List<AddressOverview>> listAddressGroupings() throws BitcoindException, 
			CommunicationException;

	List<OutputOverview> listLockUnspent() throws BitcoindException, CommunicationException;
	
	List<Account> listReceivedByAccount() throws BitcoindException, CommunicationException;

	List<Account> listReceivedByAccount(Integer confirmations) throws BitcoindException, 
			CommunicationException;

	List<Account> listReceivedByAccount(Integer confirmations, Boolean withUnused) 
			throws BitcoindException, CommunicationException;

	List<Account> listReceivedByAccount(Integer confirmations, Boolean withUnused, 
			Boolean withWatchOnly) throws BitcoindException, CommunicationException;
	
	List<Address> listReceivedByAddress() throws BitcoindException, CommunicationException;

	List<Address> listReceivedByAddress(Integer confirmations) throws BitcoindException, 
			CommunicationException;

	List<Address> listReceivedByAddress(Integer confirmations, Boolean withUnused) 
			throws BitcoindException, CommunicationException;

	List<Address> listReceivedByAddress(Integer confirmations, Boolean withUnused,
			Boolean withWatchOnly) throws BitcoindException, CommunicationException;
	
	SinceBlock listSinceBlock() throws BitcoindException, CommunicationException;

	SinceBlock listSinceBlock(String headerHash) throws BitcoindException, CommunicationException;

	SinceBlock listSinceBlock(String headerHash, Integer confirmations) throws BitcoindException, 
			CommunicationException;

	SinceBlock listSinceBlock(String headerHash, Integer confirmations, Boolean withWatchOnly) 
			throws BitcoindException, CommunicationException;
	
	List<Payment> listTransactions() throws BitcoindException, CommunicationException;

	List<Payment> listTransactions(String account) throws BitcoindException, CommunicationException;

	List<Payment> listTransactions(String account, Integer count) throws BitcoindException, 
			CommunicationException;

	List<Payment> listTransactions(String account, Integer count, Integer offset) 
			throws BitcoindException, CommunicationException;

	List<Payment> listTransactions(String account, Integer count, Integer offset, 
			Boolean withWatchOnly) throws BitcoindException, CommunicationException;
	
	List<Output> listUnspent() throws BitcoindException, CommunicationException;

	List<Output> listUnspent(Integer minConfirmations) throws BitcoindException, 
			CommunicationException;

	List<Output> listUnspent(Integer minConfirmations, Integer maxConfirmations) 
			throws BitcoindException, CommunicationException;

	List<Output> listUnspent(Integer minConfirmations, Integer maxConfirmations,
			List<String> addresses) throws BitcoindException, CommunicationException;
	
	Boolean lockUnspent(Boolean isUnlocked) throws BitcoindException, CommunicationException;

	Boolean lockUnspent(Boolean isUnlocked, List<OutputOverview> outputs) throws BitcoindException,
			CommunicationException;
	
	Boolean move(String fromAccount, String toAccount, BigDecimal amount) throws BitcoindException, 
			CommunicationException;

	Boolean move(String fromAccount, String toAccount, BigDecimal amount, Integer dummy, 
			String comment) throws BitcoindException, CommunicationException;
	
	void ping() throws BitcoindException, CommunicationException;
	
	String sendFrom(String fromAccount, String toAddress, BigDecimal amount) 
			throws BitcoindException, CommunicationException;

	String sendFrom(String fromAccount, String toAddress, BigDecimal amount, Integer confirmations) 
			throws BitcoindException, CommunicationException;

	String sendFrom(String fromAccount, String toAddress, BigDecimal amount, Integer confirmations,
			String comment) throws BitcoindException, CommunicationException;

	String sendFrom(String fromAccount, String toAddress, BigDecimal amount, Integer confirmations,
			String comment, String commentTo) throws BitcoindException, CommunicationException;
	
	String sendMany(String fromAccount, Map<String, BigDecimal> toAddresses) 
			throws BitcoindException, CommunicationException;

	String sendMany(String fromAccount, Map<String, BigDecimal> toAddresses, Integer confirmations) 
			throws BitcoindException, CommunicationException;

	String sendMany(String fromAccount, Map<String, BigDecimal> toAddresses, Integer confirmations, 
			String comment) throws BitcoindException, CommunicationException;
	
	String sendRawTransaction(String hexTransaction) throws BitcoindException, 
			CommunicationException;

	String sendRawTransaction(String hexTransaction, Boolean withHighFees) throws BitcoindException, 
			CommunicationException;
	
	String sendToAddress(String toAddress, BigDecimal amount) throws BitcoindException, 
			CommunicationException;

	String sendToAddress(String toAddress, BigDecimal amount, String comment) 
			throws BitcoindException, CommunicationException;

	String sendToAddress(String toAddress, BigDecimal amount, String comment, String commentTo) 
			throws BitcoindException, CommunicationException;
	
	void setAccount(String address, String account) throws BitcoindException, 
			CommunicationException;
	
	void setGenerate(Boolean isGenerate) throws BitcoindException, CommunicationException;
	
	void setGenerate(Boolean isGenerate, Integer processors) throws BitcoindException, 
			CommunicationException;
	
	Boolean setTxFee(BigDecimal txFee) throws BitcoindException, CommunicationException;

	String signMessage(String address, String message) throws BitcoindException, 
			CommunicationException;
	
	SignatureResult signRawTransaction(String hexTransaction) throws BitcoindException, 
			CommunicationException;

	SignatureResult signRawTransaction(String hexTransaction, List<Output> outputs) 
			throws BitcoindException, CommunicationException;

	SignatureResult signRawTransaction(String hexTransaction, List<Output> outputs, 
			List<String> privateKeys) throws BitcoindException, CommunicationException;

	SignatureResult signRawTransaction(String hexTransaction, List<Output> outputs, 
			List<String> privateKeys, String sigHashType) throws BitcoindException, 
			CommunicationException;
	
	String stop() throws BitcoindException, CommunicationException;
	
	AddressInfo validateAddress(String address) throws BitcoindException, CommunicationException;
	
	Boolean verifyMessage(String address, String signature, String message) 
			throws BitcoindException, CommunicationException;
	
	void walletLock() throws BitcoindException, CommunicationException;

	void walletPassphrase(String passphrase, Integer authTimeout) throws BitcoindException, 
			CommunicationException;

	void walletPassphraseChange(String curPassphrase, String newPassphrase) 
			throws BitcoindException, CommunicationException;
}