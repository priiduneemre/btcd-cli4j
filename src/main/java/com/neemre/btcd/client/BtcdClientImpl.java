package com.neemre.btcd.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.neemre.btcd.Commands;
import com.neemre.btcd.domain.Info;
import com.neemre.btcd.jsonrpc.client.JsonRpcClient;
import com.neemre.btcd.jsonrpc.client.JsonRpcClientImpl;

public class BtcdClientImpl implements BtcdClient {

	private JsonRpcClient rpcClient;
	
	
	public BtcdClientImpl(HttpClient rawHttpClient, Properties nodeConfig) {
		rpcClient = new JsonRpcClientImpl(rawHttpClient, nodeConfig);
	}
	
	@Override
	public Info getInfo() {
		try {
			String infoJson = rpcClient.execute(Commands.GET_INFO.getName());
			System.out.println(infoJson);
			Info info = rpcClient.getMapper().readValue(infoJson, Info.class);
			return info;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BigDecimal getDifficulty() {
		String difficultyStr = rpcClient.execute(Commands.GET_DIFFICULTY.getName());
		System.out.println(difficultyStr);
		return new BigDecimal(difficultyStr);
	}
}