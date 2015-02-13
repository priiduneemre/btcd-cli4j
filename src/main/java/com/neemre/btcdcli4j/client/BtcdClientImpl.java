package com.neemre.btcdcli4j.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.common.Constants;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClient;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClientImpl;

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
		String difficultyString = rpcClient.execute(Commands.GET_DIFFICULTY.getName());
		BigDecimal difficulty = new BigDecimal(difficultyString).setScale(Defaults.DECIMAL_SCALE, 
				Defaults.DECIMAL_ROUNDING_MODE);
		return difficulty;
	}
	
	@Override
	public Boolean getGenerate() {
		String isGeneratingString = rpcClient.execute(Commands.GET_GENERATE.getName());
		
	}
}