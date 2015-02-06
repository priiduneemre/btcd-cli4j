package com.neemre.btcd.jsonrpc;

import lombok.Data;

@Data
public abstract class JsonRpcMessage {
	
	private int id;
}