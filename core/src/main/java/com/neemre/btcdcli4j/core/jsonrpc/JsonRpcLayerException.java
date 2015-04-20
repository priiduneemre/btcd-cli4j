package com.neemre.btcdcli4j.core.jsonrpc;

import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**This exception is thrown to indicate a JSON-RPC-specific error in the underlying communication
 * infrastructure.*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class JsonRpcLayerException extends CommunicationException {

	private static final long serialVersionUID = 00000001L;

	
	public JsonRpcLayerException(Errors error) {
		super(error); 
	}
	
	public JsonRpcLayerException(Errors error, String additionalMsg) {
		super(error, additionalMsg);
	}

	public JsonRpcLayerException(Errors error, Exception cause) {
		super(error, cause);
	}
}