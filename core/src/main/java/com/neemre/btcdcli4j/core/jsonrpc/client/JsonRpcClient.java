package com.neemre.btcdcli4j.core.jsonrpc.client;

import java.util.List;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.jsonrpc.JsonMapper;
import com.neemre.btcdcli4j.core.jsonrpc.JsonPrimitiveParser;

public interface JsonRpcClient {

	String execute(String method) throws BitcoindException, CommunicationException;
	
	<T> String execute(String method, T param) throws BitcoindException, CommunicationException;
	
	<T> String execute(String method, List<T> params) throws BitcoindException, 
			CommunicationException;
	
	JsonPrimitiveParser getParser();

	JsonMapper getMapper();
	
	void close();
}