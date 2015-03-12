package com.neemre.btcdcli4j.http;

import com.neemre.btcdcli4j.CommunicationException;
import com.neemre.btcdcli4j.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class HttpLayerException extends CommunicationException {

	private static final long serialVersionUID = 00000001L;

	
	public HttpLayerException(Exception cause) {
		super(cause);
	}
	
	public HttpLayerException(Errors error) {
		super(error); 
	}

	public HttpLayerException(Errors error, Exception cause) {
		super(error, cause);
	}
}