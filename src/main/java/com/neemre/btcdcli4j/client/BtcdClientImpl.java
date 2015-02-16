package com.neemre.btcdcli4j.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MiningInfo;
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
	public MiningInfo getMiningInfo() {
		try {
			String miningInfoJson = rpcClient.execute(Commands.GET_MINING_INFO.getName());
			MiningInfo miningInfo = rpcClient.getMapper().readValue(miningInfoJson, MiningInfo.class);
			return miningInfo;
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
	public Integer getHashesPerSec() {
		String hashesPerSecStr = rpcClient.execute(Commands.GET_HASHES_PER_SEC.getName());
		Integer hashesPerSec = Integer.valueOf(hashesPerSecStr);
		return hashesPerSec;
	}
}