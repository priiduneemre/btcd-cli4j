package com.neemre.btcdcli4j.core.http.client;

import com.neemre.btcdcli4j.core.http.HttpLayerException;

public interface SimpleHttpClient {
	
	String execute(String reqMethod, String reqPayload) throws HttpLayerException;
	
	void close();
}