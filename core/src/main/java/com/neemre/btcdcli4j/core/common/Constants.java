package com.neemre.btcdcli4j.core.common;

import java.nio.charset.Charset;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**A set of universally accepted, component-agnostic constants.*/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
	
	public static final Charset US_ASCII = Charset.forName("US-ASCII");
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	
	public static final String STRING_EMPTY = "";
	public static final String STRING_NULL = "null";
	public static final String WHITESPACE = " ";
	public static final String QUOTE_SINGLE = "'";
	public static final String QUOTE_DOUBLE = "\"";

	public static final String UNIX_NEWLINE = "\n";
	public static final String WINDOWS_NEWLINE ="\r\n";
}