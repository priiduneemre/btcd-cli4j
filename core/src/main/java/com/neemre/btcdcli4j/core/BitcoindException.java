package com.neemre.btcdcli4j.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**This exception is thrown when a non-null error object (originating from <i>bitcoind</i>) is 
 *detected in a returning JSON-RPC response.*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BitcoindException extends Exception {

	private static final long serialVersionUID = 1L;

	private int code;

	
	public BitcoindException(int code, String message) {
		super(message); 
		this.code = code;
	}
}