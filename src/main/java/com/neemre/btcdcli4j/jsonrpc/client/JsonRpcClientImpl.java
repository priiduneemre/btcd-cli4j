package com.neemre.btcdcli4j.jsonrpc.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.http.client.SimpleHttpClient;
import com.neemre.btcdcli4j.http.client.SimpleHttpClientImpl;
import com.neemre.btcdcli4j.jsonrpc.JsonMapper;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcError;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcRequest;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcResponse;

public class JsonRpcClientImpl implements JsonRpcClient {
	
	private SimpleHttpClient httpClient;
	private JsonMapper mapper;
	
	
	public JsonRpcClientImpl(HttpClient rawHttpClient, Properties nodeConfig) {
		httpClient = new SimpleHttpClientImpl(rawHttpClient, nodeConfig);
		mapper = new JsonMapper();
	}
	
	@Override
	public String execute(String method) {
		return execute(method, null);
	}
	
	@Override
	public <T> String execute(String method, T param) {
		List<T> params = new ArrayList<T>();
		params.add(param);
		return execute(method, params);
	}

	@Override
	public <T> String execute(String method, List<T> params) {
		JsonRpcRequest<T> request = getNewRequest(method, params, 80085);
		String responseJson = httpClient.execute(mapper.mapToJson(request));
		JsonRpcResponse response = mapper.mapToEntity(responseJson, JsonRpcResponse.class);
		return response.getResult();
	}
	
	@Override
	public JsonMapper getMapper() {
		return mapper;
	}
	
	private <T> JsonRpcRequest<T> getNewRequest(String method, List<T> params, int id) {
		JsonRpcRequest<T> rpcRequest = new JsonRpcRequest<T>();
		
		rpcRequest.setMethod(method);
		rpcRequest.setParams(params);
		rpcRequest.setId(id);
		return rpcRequest;
	}
	
	private JsonRpcResponse getNewResponse(String result, JsonRpcError error, int id) {
		JsonRpcResponse rpcResponse = new JsonRpcResponse();
		
		rpcResponse.setResult(result);
		rpcResponse.setError(error);
		rpcResponse.setId(id);
		return rpcResponse;
	}
}