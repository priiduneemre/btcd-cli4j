package com.neemre.btcdcli4j.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**This exception is thrown when a non-null error object (<i>i.e.</i> originating from 
 * <i>bitcoind</i>) is found to be present in a returning JSON-RPC response.*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BitcoindException extends Exception {

	private static final long serialVersionUID = 00000001L;

	private int code;
	
	
	public BitcoindException(Exception cause) {
		super(cause);
	}
	
	public BitcoindException(int code, String message) {
		super(message); 
		this.code = code;
	}

	public BitcoindException(int code, String message, Exception cause) {
		super(message, cause);
		this.code = code;
	}
}