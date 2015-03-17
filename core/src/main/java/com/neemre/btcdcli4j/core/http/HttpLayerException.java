package com.neemre.btcdcli4j.core.http;

import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**This exception is thrown to indicate a HTTP-specific error in the underlying communication
 * infrastructure.*/
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