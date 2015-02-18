package com.neemre.btcdcli4j.client;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.common.Constants;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClient;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClientImpl;

public class BtcdClientImpl implements BtcdClient {

	private JsonRpcClient rpcClient;
	
	
	public BtcdClientImpl(HttpClient rawHttpClient, Properties nodeConfig) {
		rpcClient = new JsonRpcClientImpl(rawHttpClient, nodeConfig);
	}
	
	@Override
	public String encryptWallet(String passphrase) {
		String noticeMsgJson = rpcClient.execute(Commands.ENCRYPT_WALLET.getName(), passphrase);
		String noticeMsg = rpcClient.getMapper().decode(noticeMsgJson);
		return noticeMsg;
	}

	@Override
	public BigDecimal getBalance() {
		String balanceStr = rpcClient.execute(Commands.GET_BALANCE.getName());
		BigDecimal balance = new BigDecimal(balanceStr).setScale(Defaults.DECIMAL_SCALE, 
				Defaults.DECIMAL_ROUNDING_MODE);
		return balance;
	}
	
	@Override
	public BigDecimal getBalance(String account) {
		String balanceStr = rpcClient.execute(Commands.GET_BALANCE.getName(), account);
		BigDecimal balance = new BigDecimal(balanceStr).setScale(Defaults.DECIMAL_SCALE,
				Defaults.DECIMAL_ROUNDING_MODE);
		return balance;
	}
	
	@Override
	public BigDecimal getDifficulty() {
		String difficultyStr = rpcClient.execute(Commands.GET_DIFFICULTY.getName());
		BigDecimal difficulty = new BigDecimal(difficultyStr).setScale(Defaults.DECIMAL_SCALE, 
				Defaults.DECIMAL_ROUNDING_MODE);
		return difficulty;
	}
	
	@Override
	public Boolean getGenerate() {
		String isGenerateStr = rpcClient.execute(Commands.GET_GENERATE.getName());
		Boolean isGenerate = Boolean.valueOf(isGenerateStr);
		return isGenerate;
	}
	
	@Override
	public Integer getHashesPerSec() {
		String hashesPerSecStr = rpcClient.execute(Commands.GET_HASHES_PER_SEC.getName());
		Integer hashesPerSec = Integer.valueOf(hashesPerSecStr);
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
	public void setGenerate(Boolean isGenerate, Integer processorCount) {
		List<Object> params = new ArrayList<Object>();
		params.add(isGenerate);
		params.add(processorCount);
		rpcClient.execute(Commands.SET_GENERATE.getName(), params);
	}
	
	@Override
	public String stop() {
		String noticeMsgJson = rpcClient.execute(Commands.STOP.getName());
		String noticeMsg = rpcClient.getMapper().decode(noticeMsgJson);
		return noticeMsg;
	}
	
	@Override
	public void walletLock() {
		rpcClient.execute(Commands.WALLET_LOCK.getName());
	}
	
	@Override
	public void walletPassphrase(String passphrase, int authTimeout) {
		List<Object> params = new ArrayList<Object>();
		params.add(passphrase);
		params.add(authTimeout);
		rpcClient.execute(Commands.WALLET_PASSPHRASE.getName(), params);
	}

	@Override
	public void walletPassphraseChange(String curPassphrase, String newPassphrase) {
		List<Object> params = new ArrayList<Object>();
		params.add(curPassphrase);
		params.add(newPassphrase);
		rpcClient.execute(Commands.WALLET_PASSPHRASE_CHANGE.getName(), params);
	}
}