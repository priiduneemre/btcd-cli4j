package com.neemre.btcdcli4j;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.neemre.btcdcli4j.client.BtcdClient;
import com.neemre.btcdcli4j.client.BtcdClientImpl;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MiningInfo;

public class ExampleMain {
	
	public static void main(String[] args) throws IOException {
		HttpClient rawHttpClient = HttpClientBuilder.create().build();
		Properties nodeConfig = new Properties();
		InputStream inputStream = new BufferedInputStream(new FileInputStream(
				"src/main/resources/node_config.properties"));
		nodeConfig.load(inputStream);
		inputStream.close();
		
		BtcdClient btcdClient = new BtcdClientImpl(rawHttpClient, nodeConfig);
		
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
	}
}