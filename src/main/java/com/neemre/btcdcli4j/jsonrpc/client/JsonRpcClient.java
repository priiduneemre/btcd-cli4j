package com.neemre.btcdcli4j.jsonrpc.client;

import java.util.List;

public interface JsonRpcClient {

	String execute(String method);
	
	<T> String execute(String method, T param);
	
	<T> String execute(String method, List<T> params);
	
	<T> String mapToJson(T entity);
	
	<T> T mapToEntity(String entityJson, Class<T> clazz);
}