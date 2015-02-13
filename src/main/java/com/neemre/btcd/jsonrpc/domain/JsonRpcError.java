package com.neemre.btcd.jsonrpc.domain;

import lombok.Data;

@Data
public class JsonRpcError {
	
	private int code;
	private String message;
}