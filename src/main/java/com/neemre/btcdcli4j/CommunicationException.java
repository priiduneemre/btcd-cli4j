package com.neemre.btcdcli4j;

import com.neemre.btcdcli4j.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class CommunicationException extends Exception {

	private static final long serialVersionUID = 00000001L;

	private int code;
	
	
	public CommunicationException(Exception cause) {
		super(cause);
	}
	
	public CommunicationException(Errors error) {
		super(error.getDescription());
		code = error.getCode();
	}
	
	public CommunicationException(Errors error, Exception cause) {
		super(error.getDescription(), cause);
		code = error.getCode();
	}
}