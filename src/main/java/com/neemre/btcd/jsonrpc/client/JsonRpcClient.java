package com.neemre.btcd.jsonrpc.client;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonRpcClient {

	String execute(String method);
	
	<T> String execute(String method, List<T> params);
	
	ObjectMapper getMapper();
}