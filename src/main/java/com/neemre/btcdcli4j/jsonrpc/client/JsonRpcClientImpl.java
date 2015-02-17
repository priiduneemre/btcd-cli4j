package com.neemre.btcdcli4j.jsonrpc.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
		JsonRpcRequest request = getNewRequest(method, params, 80085);
		String responseJson = httpClient.execute(mapToJson(request));
		JsonRpcResponse response = mapToEntity(responseJson, JsonRpcResponse.class);
		return response.getResult();
	}
	
	@Override
	public <T> String mapToJson(T entity) {
		try {
			String entityJson = jsonWriter.writeValueAsString(entity);
			return entityJson;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T mapToEntity(String entityJson, Class<T> clazz) {
		try {
			T entity = jsonMapper.readValue(entityJson, clazz);
			return entity;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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