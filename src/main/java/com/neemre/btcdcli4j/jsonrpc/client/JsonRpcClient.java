package com.neemre.btcdcli4j.jsonrpc.client;

import java.util.List;

import com.neemre.btcdcli4j.BitcoindException;
import com.neemre.btcdcli4j.CommunicationException;
import com.neemre.btcdcli4j.jsonrpc.JsonMapper;
import com.neemre.btcdcli4j.jsonrpc.JsonPrimitiveParser;

public interface JsonRpcClient {

	String execute(String method) throws BitcoindException, CommunicationException;
	
	<T> String execute(String method, T param) throws BitcoindException, CommunicationException;
	
	<T> String execute(String method, List<T> params) throws BitcoindException, 
			CommunicationException;
	
	JsonPrimitiveParser getParser();

	JsonMapper getMapper();
}