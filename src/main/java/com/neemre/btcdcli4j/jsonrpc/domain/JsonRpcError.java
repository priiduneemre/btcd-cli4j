package com.neemre.btcdcli4j.jsonrpc.domain;

import lombok.Data;

@Data
public class JsonRpcError {
	
	private int code;
	private String message;
}