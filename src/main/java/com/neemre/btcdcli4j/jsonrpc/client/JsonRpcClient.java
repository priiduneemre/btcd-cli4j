package com.neemre.btcdcli4j.jsonrpc.client;

import java.util.List;

import com.neemre.btcdcli4j.jsonrpc.JsonMapper;

public interface JsonRpcClient {

	String execute(String method);
	
	<T> String execute(String method, T param);
	
	<T> String execute(String method, List<T> params);
	
	JsonMapper getMapper();
}