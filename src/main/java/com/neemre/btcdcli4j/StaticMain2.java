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

public class StaticMain2 {
	
	public static void main(String[] args) throws IOException {
		HttpClient rawHttpClient = HttpClientBuilder.create().build();
		Properties nodeConfig = new Properties();
		InputStream inputStream = new BufferedInputStream(new FileInputStream("src/main/resources/node_config.properties"));
		nodeConfig.load(inputStream);
		inputStream.close();
		
		BtcdClient btcdClient = new BtcdClientImpl(rawHttpClient, nodeConfig);
		Info info = btcdClient.getInfo();
		System.out.println(info);
		
		BigDecimal difficulty = btcdClient.getDifficulty();
		System.out.println(difficulty);
		
		
	}
}