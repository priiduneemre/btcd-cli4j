package com.neemre.btcdcli4j.http;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class HttpLayerException extends Exception {

	private static final long serialVersionUID = 00000001L;

	
	public HttpLayerException(String message) {
		super(message); 
	}

	public HttpLayerException(String message, Exception cause) {
		super(message, cause);
	}
}