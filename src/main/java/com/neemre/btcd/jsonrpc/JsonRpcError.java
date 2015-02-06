package com.neemre.btcd.jsonrpc;

import lombok.Data;

@Data
public class JsonRpcError {
	
	private int code;
	private String message;
}