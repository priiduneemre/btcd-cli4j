package com.neemre.btcdcli4j.http.client;

import com.neemre.btcdcli4j.http.HttpLayerException;

public interface SimpleHttpClient {
	
	String execute(String reqMethod, String reqPayload) throws HttpLayerException;
}