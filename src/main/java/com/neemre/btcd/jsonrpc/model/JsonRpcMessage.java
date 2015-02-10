package com.neemre.btcd.jsonrpc.model;

import lombok.Data;

@Data
public abstract class JsonRpcMessage {
	
	private int id;
}