package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.domain.PeerNode;
import com.neemre.btcdcli4j.domain.WalletInfo;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClient;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClientImpl;
import com.neemre.btcdcli4j.util.CollectionUtils;
import com.neemre.btcdcli4j.util.NumberUtils;

public class BtcdClientImpl implements BtcdClient {

	private JsonRpcClient rpcClient;
	
	
	public BtcdClientImpl(HttpClient httpProvider, Properties nodeConfig) {
		rpcClient = new JsonRpcClientImpl(httpProvider, nodeConfig);
	}
	
	@Override
	public String encryptWallet(String passphrase) {
		String noticeMsgJson = rpcClient.execute(Commands.ENCRYPT_WALLET.getName(), passphrase);
		String noticeMsg = rpcClient.getParser().parseString(noticeMsgJson);
		return noticeMsg;
	}
	
	@Override
	public String getAccount(String address) {
		String accountJson = rpcClient.execute(Commands.GET_ACCOUNT.getName(), address);
		String account = rpcClient.getParser().parseString(accountJson);
		return account;
	}
	
	@Override
	public String getAccountAddress(String account) {
		String addressJson = rpcClient.execute(Commands.GET_ACCOUNT_ADDRESS.getName(), account);
		String address = rpcClient.getParser().parseString(addressJson);
		return address;
	}
	
	@Override
	public List<String> getAddressesByAccount(String account) {
		String addressesJson = rpcClient.execute(Commands.GET_ADDRESSES_BY_ACCOUNT.getName(), 
				account);
		List<String> addresses = rpcClient.getMapper().mapToList(addressesJson, String.class);
		return addresses;
	}

	@Override
	public BigDecimal getBalance() {
		String balanceJson = rpcClient.execute(Commands.GET_BALANCE.getName());
		BigDecimal balance = rpcClient.getParser().parseBigDecimal(balanceJson);
		return balance;
	}
	
	@Override
	public BigDecimal getBalance(String account) {
		String balanceJson = rpcClient.execute(Commands.GET_BALANCE.getName(), account);
		BigDecimal balance = rpcClient.getParser().parseBigDecimal(balanceJson);
		return balance;
	}
	
	@Override
	public BigDecimal getBalance(String account, Integer confirmations) {
		List<Object> params = CollectionUtils.asList(account, confirmations);
		String balanceJson = rpcClient.execute(Commands.GET_BALANCE.getName(), params);
		BigDecimal balance = rpcClient.getParser().parseBigDecimal(balanceJson);
		return balance;
	}

	@Override
	public BigDecimal getBalance(String account, Integer confirmations, Boolean withWatchOnly) {
		List<Object> params = CollectionUtils.asList(account, confirmations, withWatchOnly);
		String balanceJson = rpcClient.execute(Commands.GET_BALANCE.getName(), params);
		BigDecimal balance = rpcClient.getParser().parseBigDecimal(balanceJson);
		return balance;
	}
	
	@Override
	public String getBestBlockHash() {
		String blockHashJson = rpcClient.execute(Commands.GET_BEST_BLOCK_HASH.getName());
		String blockHash = rpcClient.getParser().parseString(blockHashJson);
		return blockHash;
	}
	
	@Override
	public Integer getBlockCount() {
		String blockHeightJson = rpcClient.execute(Commands.GET_BLOCK_COUNT.getName());
		Integer blockHeight = rpcClient.getParser().parseInteger(blockHeightJson);
		return blockHeight;
	}

	@Override
	public String getBlockHash(Integer blockHeight) {
		String blockHashJson = rpcClient.execute(Commands.GET_BLOCK_HASH.getName(), blockHeight);
		String blockHash = rpcClient.getParser().parseString(blockHashJson);
		return blockHash;
	}
	
	@Override
	public BigDecimal getDifficulty() {
		String difficultyJson = rpcClient.execute(Commands.GET_DIFFICULTY.getName());
		BigDecimal difficulty = rpcClient.getParser().parseBigDecimal(difficultyJson);
		return difficulty;
	}
	
	@Override
	public Boolean getGenerate() {
		String isGenerateJson = rpcClient.execute(Commands.GET_GENERATE.getName());
		Boolean isGenerate = rpcClient.getParser().parseBoolean(isGenerateJson);
		return isGenerate;
	}
	
	@Override
	public Long getHashesPerSec() {
		String hashesPerSecJson = rpcClient.execute(Commands.GET_HASHES_PER_SEC.getName());
		Long hashesPerSec = rpcClient.getParser().parseLong(hashesPerSecJson);
		return hashesPerSec;
	}
	
	@Override
	public Info getInfo() {
		String infoJson = rpcClient.execute(Commands.GET_INFO.getName());
		Info info = rpcClient.getMapper().mapToEntity(infoJson, Info.class);
		return info;
	}
	
	@Override
	public MemPoolInfo getMemPoolInfo() {
		String memPoolInfoJson = rpcClient.execute(Commands.GET_MEM_POOL_INFO.getName());
		MemPoolInfo memPoolInfo = rpcClient.getMapper().mapToEntity(memPoolInfoJson, 
				MemPoolInfo.class);
		return memPoolInfo;
	}

	@Override
	public MiningInfo getMiningInfo() {
		String miningInfoJson = rpcClient.execute(Commands.GET_MINING_INFO.getName());
		MiningInfo miningInfo = rpcClient.getMapper().mapToEntity(miningInfoJson, MiningInfo.class);
		return miningInfo;
	}
	
	@Override
	public String getNewAddress() {
		String addressJson = rpcClient.execute(Commands.GET_NEW_ADDRESS.getName());
		String address = rpcClient.getParser().parseString(addressJson);
		return address;
	}

	@Override
	public String getNewAddress(String account) {
		String addressJson = rpcClient.execute(Commands.GET_NEW_ADDRESS.getName(), account);
		String address = rpcClient.getParser().parseString(addressJson);
		return address;
	}
	
	@Override
	public List<PeerNode> getPeerInfo() {
		String peerInfoJson = rpcClient.execute(Commands.GET_PEER_INFO.getName());
		List<PeerNode> peerInfo = rpcClient.getMapper().mapToList(peerInfoJson, PeerNode.class);
		return peerInfo;
	}
	
	@Override
	public String getRawChangeAddress() {
		String addressJson = rpcClient.execute(Commands.GET_RAW_CHANGE_ADDRESS.getName());
		String address = rpcClient.getParser().parseString(addressJson);
		return address;
	}
	
	@Override
	public BigDecimal getReceivedByAccount(String account) {
		String totalReceivedJson = rpcClient.execute(Commands.GET_RECEIVED_BY_ACCOUNT.getName(),
				account);
		BigDecimal totalReceived = rpcClient.getParser().parseBigDecimal(totalReceivedJson);
		return totalReceived;
	}

	@Override
	public BigDecimal getReceivedByAccount(String account, Integer confirmations) {
		List<Object> params = CollectionUtils.asList(account, confirmations);
		String totalReceivedJson = rpcClient.execute(Commands.GET_RECEIVED_BY_ACCOUNT.getName(), 
				params);
		BigDecimal totalReceived = rpcClient.getParser().parseBigDecimal(totalReceivedJson);
		return totalReceived;
	}
	
	@Override
	public BigDecimal getReceivedByAddress(String address) {
		String totalReceivedJson = rpcClient.execute(Commands.GET_RECEIVED_BY_ADDRESS.getName(),
				address);
		BigDecimal totalReceived = rpcClient.getParser().parseBigDecimal(totalReceivedJson);
		return totalReceived;
	}

	@Override
	public BigDecimal getReceivedByAddress(String address, Integer confirmations) {
		List<Object> params = CollectionUtils.asList(address, confirmations);
		String totalReceivedJson = rpcClient.execute(Commands.GET_RECEIVED_BY_ADDRESS.getName(),
				params);
		BigDecimal totalReceived = rpcClient.getParser().parseBigDecimal(totalReceivedJson);
		return totalReceived;
	}
	
	@Override
	public BigDecimal getUnconfirmedBalance() {
		String unconfirmedBalanceJson = rpcClient.execute(Commands.GET_UNCONFIRMED_BALANCE.getName());
		BigDecimal unconfirmedBalance = rpcClient.getParser().parseBigDecimal(unconfirmedBalanceJson);
		return unconfirmedBalance;
	}	

	@Override
	public WalletInfo getWalletInfo() {
		String walletInfoJson = rpcClient.execute(Commands.GET_WALLET_INFO.getName());
		WalletInfo walletInfo = rpcClient.getMapper().mapToEntity(walletInfoJson, WalletInfo.class);
		return walletInfo;
	}
	
	@Override
	public Map<String, BigDecimal> listAccounts() {
		String accountsJson = rpcClient.execute(Commands.LIST_ACCOUNTS.getName());
		Map<String, BigDecimal> accounts = rpcClient.getMapper().mapToMap(accountsJson, 
				String.class, BigDecimal.class);
		accounts = NumberUtils.setValueScale(accounts, Defaults.DECIMAL_SCALE);
		return accounts;
	}
	
	@Override
	public Map<String, BigDecimal> listAccounts(Integer confirmations) {
		String accountsJson = rpcClient.execute(Commands.LIST_ACCOUNTS.getName(), confirmations);
		Map<String, BigDecimal> accounts = rpcClient.getMapper().mapToMap(accountsJson, 
				String.class, BigDecimal.class);
		accounts = NumberUtils.setValueScale(accounts, Defaults.DECIMAL_SCALE);
		return accounts;
	}

	@Override
	public Map<String, BigDecimal> listAccounts(Integer confirmations, Boolean withWatchOnly) {
		List<Object> params = CollectionUtils.asList(confirmations, withWatchOnly);
		String accountsJson = rpcClient.execute(Commands.LIST_ACCOUNTS.getName(), params);
		Map<String, BigDecimal> accounts = rpcClient.getMapper().mapToMap(accountsJson, 
				String.class, BigDecimal.class);
		accounts = NumberUtils.setValueScale(accounts, Defaults.DECIMAL_SCALE);
		return accounts;
	}
	
	@Override
	public String setAccount(String address, String account) {
		List<Object> params = CollectionUtils.asList(address, account);
		String nullMsgJson = rpcClient.execute(Commands.SET_ACCOUNT.getName(), params);
		String nullMsg = rpcClient.getParser().parseString(nullMsgJson);
		return nullMsg;
	}
	
	@Override
	public void setGenerate(Boolean isGenerate) {
		rpcClient.execute(Commands.SET_GENERATE.getName(), isGenerate);		
	}
	
	@Override
	public void setGenerate(Boolean isGenerate, Integer processors) {
		List<Object> params = CollectionUtils.asList(isGenerate, processors);
		rpcClient.execute(Commands.SET_GENERATE.getName(), params);
	}
	
	@Override
	public Boolean setTxFee(BigDecimal txFee) {
		String resultJson = rpcClient.execute(Commands.SET_TX_FEE.getName(), txFee);
		Boolean result = rpcClient.getParser().parseBoolean(resultJson);
		return result;
	}
	
	@Override
	public String stop() {
		String noticeMsgJson = rpcClient.execute(Commands.STOP.getName());
		String noticeMsg = rpcClient.getParser().parseString(noticeMsgJson);
		return noticeMsg;
	}
	
	@Override
	public void walletLock() {
		rpcClient.execute(Commands.WALLET_LOCK.getName());
	}
	
	@Override
	public void walletPassphrase(String passphrase, Integer authTimeout) {
		List<Object> params = CollectionUtils.asList(passphrase, authTimeout);
		rpcClient.execute(Commands.WALLET_PASSPHRASE.getName(), params);
	}

	@Override
	public void walletPassphraseChange(String curPassphrase, String newPassphrase) {
		List<Object> params = CollectionUtils.asList(curPassphrase, newPassphrase);
		rpcClient.execute(Commands.WALLET_PASSPHRASE_CHANGE.getName(), params);
	}
}