package com.neemre.btcdcli4j.core;

import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**This is an abstract superclass for all exceptions thrown by the underlying communication 
 * infrastructure (<i>i.e.</i> the HTTP layer + the JSON-RPC layer).*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public abstract class CommunicationException extends Exception {

	private static final long serialVersionUID = 1L;

	private int code;
	
	
	public CommunicationException(Errors error) {
		this(error, Constants.STRING_EMPTY);
	}
	
	public CommunicationException(Errors error, String additionalMsg) {
		super(error.getDescription() + additionalMsg);
		code = error.getCode();
	}
	
	public CommunicationException(Errors error, Exception cause) {
		super(error.getDescription(), cause);
		code = error.getCode();
	}
}