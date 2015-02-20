package com.neemre.btcdcli4j.jsonrpc.domain;

import lombok.Data;

@Data
public class JsonRpcError {
	
	private Integer code;
	private String message;
}