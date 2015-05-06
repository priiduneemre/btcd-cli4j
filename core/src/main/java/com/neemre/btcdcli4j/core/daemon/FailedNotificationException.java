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
public class FailedNotificationException extends RuntimeException {

	private static final long serialVersionUID = 00000001L;

	private int code;
	
	
	public FailedNotificationException(Errors error) {
		this(error, Constants.STRING_EMPTY);
	}
	
	public FailedNotificationException(Errors error, String additionalMsg) {
		super(error.getDescription() + additionalMsg);
		code = error.getCode();
	}
	
	public FailedNotificationException(Errors error, Exception cause) {
		super(error.getDescription(), cause);
		code = error.getCode();
	}	
}