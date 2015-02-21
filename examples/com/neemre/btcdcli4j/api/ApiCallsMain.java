package com.neemre.btcdcli4j.api;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.neemre.btcdcli4j.client.BtcdClient;
import com.neemre.btcdcli4j.client.BtcdClientImpl;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.util.CollectionUtils;

public class ApiCallsMain {
	
	public static void main(String[] args) throws IOException {
		BtcdClient btcdClient = new BtcdClientImpl(getHttpProvider(), getNodeConfig());
		
		Info info = btcdClient.getInfo();
		System.out.printf("bitcoind.exe response for 'getinfo()': '%s'\n", info);
		
		BigDecimal difficulty = btcdClient.getDifficulty();
		System.out.printf("bitcoind.exe response for 'getdifficulty()': '%s'\n", difficulty);

		Boolean isGenerate1 = false;
		btcdClient.setGenerate(isGenerate1);
		System.out.printf("bitcoind.exe response for 'setgenerate(%s=%s)': n/a\n", "isGenerate1", 
				isGenerate1);
		
		Boolean isGenerate2 = false;
		Integer processorCount = 7;
		btcdClient.setGenerate(isGenerate2, processorCount);
		System.out.printf("bitcoind.exe response for 'setgenerate(%s=%s, %s=%s)': n/a\n",
				"isGenerate2", isGenerate2, "processorCount", processorCount);
		
		Boolean isGenerate3 = btcdClient.getGenerate();
		System.out.printf("bitcoind.exe response for 'getgenerate()': '%s'\n", isGenerate3);
		
		Integer hashesPerSec = btcdClient.getHashesPerSec();
		System.out.printf("bitcoind.exe response for 'gethashespersec()': '%s'\n", hashesPerSec);
		
		MiningInfo miningInfo = btcdClient.getMiningInfo();
		System.out.printf("bitcoind.exe response for 'getmininginfo()': '%s'\n", miningInfo);
		
		//[START]To be supported in Bicoin Core 0.10.0
		MemPoolInfo memPoolInfo = btcdClient.getMemPoolInfo();
		System.out.printf("bitcoind.exe response for 'getmempoolinfo()': '%s'\n", memPoolInfo);
		//[END]To be supported in Bicoin Core 0.10.0
		
		String passphrase1 = "maasikas";
		btcdClient.walletPassphrase(passphrase1, Defaults.WALLET_AUTH_TIMEOUT);
		System.out.printf("bitcoind.exe response for 'walletpassphrase(%s=%s, %s=%s)': n/a\n", 
				"passphrase1", passphrase1, "authTimeout", Defaults.WALLET_AUTH_TIMEOUT);
		
		String curPassphrase = "maasikas";
		String newPassphrase = "vaarikas";
		btcdClient.walletPassphraseChange(curPassphrase, newPassphrase);
		System.out.printf("bitcoind.exe response for 'walletpassphrasechange(%s=%s, %s=%s)': n/a\n",
				"curPassphrase", curPassphrase, "newPassphrase", newPassphrase);
		
		btcdClient.walletLock();
		System.out.printf("bitcoind.exe response for 'walletlock()': n/a\n");
		
		String passphrase2 = "kapsas";
		String noticeMsg1 = btcdClient.encryptWallet(passphrase2);
		System.out.printf("bitcoind.exe response for 'encryptwallet(%s=%s)': '%s'\n", "passphrase2",
				passphrase2, noticeMsg1);
		
//		String noticeMsg2 = btcdClient.stop();
//		System.out.printf("bitcoind.exe response for 'stop()': '%s'\n", noticeMsg2);
		
		BigDecimal balance1 = btcdClient.getBalance();
		System.out.printf("bitcoind.exe response for 'getbalance()': '%s'\n", balance1);
	
		String account1 = "";
		BigDecimal balance2 = btcdClient.getBalance(account1);
		System.out.printf("bitcoind.exe response for 'getbalance(%s=%s)': '%s'\n", "account1", 
				account1, balance2);
		
		String account2 = "";
		Integer confirmations1 = 6;
		BigDecimal balance3 = btcdClient.getBalance(account2, confirmations1);
		System.out.printf("bitcoind.exe response for 'getbalance(%s=%s, %s=%s)': '%s'\n", 
				"account2", account2, "confirmations1", confirmations1, balance3);
		
		String account3 = "";
		Integer confirmations2 = 7;
		Boolean hasWatchOnly = true;
		BigDecimal balance4 = btcdClient.getBalance(account3, confirmations2, hasWatchOnly);
		System.out.printf("bitcoind.exe response for 'getbalance(%s=%s, %s=%s, %s=%s)': '%s'\n",
				"account3", account3, "confirmations2", confirmations2, "hasWatchOnly", 
				hasWatchOnly, balance4);
		
	}
	
	private static HttpClient getHttpProvider() {
		HttpClient rawHttpClient = HttpClientBuilder.create().build();
		return rawHttpClient;
	}
	
	private static Properties getNodeConfig() throws IOException {
		Properties nodeConfig = new Properties();
		InputStream inputStream = new BufferedInputStream(new FileInputStream(
				"src/main/resources/node_config.properties"));
		nodeConfig.load(inputStream);
		inputStream.close();
		return nodeConfig;
	}
	
	private static void printResult(String methodName, List<Object> paramNames, 
			List<Object> paramValues, Object result) {
		List<Object> printables = new ArrayList<Object>();
		printables.add(methodName);
		printables.add(CollectionUtils.mergeInterlaced(paramNames, paramValues));
		printables.add(result);
		String resultString = String.format("'bitcoind' response for method '%s()' was: '%s'\n");
		for(int i = 0; i < paramNames.size();)
		
	}
}