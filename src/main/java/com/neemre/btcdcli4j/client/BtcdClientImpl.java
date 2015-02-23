package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClient;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClientImpl;
import com.neemre.btcdcli4j.util.CollectionUtils;

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
	public BigDecimal getBalance(String account, Integer confirmations, Boolean hasWatchOnly) {
		List<Object> params = CollectionUtils.asList(account, confirmations, hasWatchOnly);
		String balanceJson = rpcClient.execute(Commands.GET_BALANCE.getName(), params);
		BigDecimal balance = rpcClient.getParser().parseBigDecimal(balanceJson);
		return balance;
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
	public void setGenerate(Boolean isGenerate) {
		rpcClient.execute(Commands.SET_GENERATE.getName(), isGenerate);		
	}
	
	@Override
	public void setGenerate(Boolean isGenerate, Integer processors) {
		List<Object> params = CollectionUtils.asList(isGenerate, processors);
		rpcClient.execute(Commands.SET_GENERATE.getName(), params);
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
	public void walletPassphrase(String passphrase, int authTimeout) {
		List<Object> params = CollectionUtils.asList(passphrase, authTimeout);
		rpcClient.execute(Commands.WALLET_PASSPHRASE.getName(), params);
	}

	@Override
	public void walletPassphraseChange(String curPassphrase, String newPassphrase) {
		List<Object> params = CollectionUtils.asList(curPassphrase, newPassphrase);
		rpcClient.execute(Commands.WALLET_PASSPHRASE_CHANGE.getName(), params);
	}
}