package com.neemre.btcdcli4j.jsonrpc.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.http.client.SimpleHttpClient;
import com.neemre.btcdcli4j.http.client.SimpleHttpClientImpl;
import com.neemre.btcdcli4j.jsonrpc.JsonMapper;
import com.neemre.btcdcli4j.jsonrpc.JsonPrimitiveParser;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcError;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcRequest;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcResponse;

public class JsonRpcClientImpl implements JsonRpcClient {

	private SimpleHttpClient httpClient;
	private JsonPrimitiveParser parser;
	private JsonMapper mapper;


	public JsonRpcClientImpl(HttpClient httpProvider, Properties nodeConfig) {
		httpClient = new SimpleHttpClientImpl(httpProvider, nodeConfig);
		parser = new JsonPrimitiveParser();
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
		String requestUuid = getNewUuid();
		JsonRpcRequest<T> request = getNewRequest(method, params, requestUuid);
		String responseJson = httpClient.execute(mapper.mapToJson(request));
		JsonRpcResponse response = mapper.mapToEntity(responseJson, JsonRpcResponse.class);
		response = verifyResponse(request, response);
		response = checkResponse(response);
		return response.getResult();
	}

	@Override
	public JsonPrimitiveParser getParser() {
		return parser;
	}

	@Override
	public JsonMapper getMapper() {
		return mapper;
	}

	private <T> JsonRpcRequest<T> getNewRequest(String method, List<T> params, String id) {
		JsonRpcRequest<T> rpcRequest = new JsonRpcRequest<T>();
		rpcRequest.setMethod(method);
		rpcRequest.setParams(params);
		rpcRequest.setId(id);
		return rpcRequest;
	}

	private JsonRpcResponse getNewResponse(String result, JsonRpcError error, String id) {
		JsonRpcResponse rpcResponse = new JsonRpcResponse();
		rpcResponse.setResult(result);
		rpcResponse.setError(error);
		rpcResponse.setId(id);
		return rpcResponse;
	}

	private String getNewUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	private <T> JsonRpcResponse verifyResponse(JsonRpcRequest<T> request, JsonRpcResponse response) {
		if(response == null) {
			throw new IllegalArgumentException("I am broken."); //TODO
		}
		if(response.getId() == null) {
			throw new IllegalArgumentException("I am broken."); //TODO
		}
		if(!response.getId().equals(request.getId())) {
			throw new IllegalArgumentException("I am broken.");	//TODO
		}
		return response;
	}

	private <T> JsonRpcResponse checkResponse(JsonRpcResponse response) {
		if(!(response.getError() == null)) {
			//throw new IllegalArgumentException("I am broken.");	//TODO
			System.out.println("A non-null error object was returned: " + response.getError());
		}
		return response;
	}
}