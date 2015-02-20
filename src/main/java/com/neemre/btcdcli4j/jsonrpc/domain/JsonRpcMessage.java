package com.neemre.btcdcli4j.jsonrpc.domain;

import lombok.Data;

@Data
public abstract class JsonRpcMessage {
	
	private String id;
}