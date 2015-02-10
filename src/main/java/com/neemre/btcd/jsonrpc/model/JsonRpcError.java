package com.neemre.btcd.jsonrpc.model;

import lombok.Data;

@Data
public class JsonRpcError {
	
	private int code;
	private String message;
}