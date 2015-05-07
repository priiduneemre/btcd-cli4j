package com.neemre.btcdcli4j.core.daemon;

import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Errors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class NotificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;
	
	
	public NotificationException(Errors error) {
		this(error, Constants.STRING_EMPTY);
	}
	
	public NotificationException(Errors error, String additionalMsg) {
		super(error.getDescription() + additionalMsg);
		code = error.getCode();
	}
	
	public NotificationException(Errors error, Exception cause) {
		super(error.getDescription(), cause);
		code = error.getCode();
	}	
}