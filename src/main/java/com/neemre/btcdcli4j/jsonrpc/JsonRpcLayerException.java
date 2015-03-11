package com.neemre.btcdcli4j.jsonrpc;

public class JsonRpcLayerException extends RuntimeException {

	private static final long serialVersionUID = 00000001L;

	
	public JsonRpcLayerException(String message) {
		super(message);
	}
}