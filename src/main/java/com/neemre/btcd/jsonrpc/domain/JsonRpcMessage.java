package com.neemre.btcd.jsonrpc.domain;

import lombok.Data;

@Data
public abstract class JsonRpcMessage {
	
	private int id;
}