package com.neemre.btcdcli4j.jsonrpc.client;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.neemre.btcdcli4j.http.client.SimpleHttpClient;
import com.neemre.btcdcli4j.http.client.SimpleHttpClientImpl;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcError;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcRequest;
import com.neemre.btcdcli4j.jsonrpc.domain.JsonRpcResponse;

public class JsonRpcClientImpl implements JsonRpcClient {
	
	private SimpleHttpClient httpClient;
	private ObjectMapper jsonMapper;
	private ObjectWriter jsonWriter;
	
	
	public JsonRpcClientImpl(HttpClient rawHttpClient, Properties nodeConfig) {
		httpClient = new SimpleHttpClientImpl(rawHttpClient, nodeConfig);
		jsonMapper = new ObjectMapper();
		jsonWriter = jsonMapper.writer().withDefaultPrettyPrinter();
	}
	
	public String execute(String method) {
		return execute(method, null);
	}
	
	public <T> String execute(String method, List<T> params) {
		try {
			JsonRpcRequest request = getNewRequest(method, params, 80085);
			String responseJson = httpClient.execute(jsonWriter.writeValueAsString(request));
			JsonRpcResponse response = jsonMapper.readValue(responseJson, JsonRpcResponse.class);
			return response.getResult();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ObjectMapper getMapper() {
		return jsonMapper;
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