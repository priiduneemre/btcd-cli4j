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
	public static final String STRING_SPACE = " ";
	public static final String STRING_SINGLE_QUOTE = "'";
	public static final String STRING_DOUBLE_QUOTE = "\"";
	
	public static final char CHAR_NULL = '\u0000';
	
	public static final String UNIX_NEWLINE = "\n";
	public static final String WINDOWS_NEWLINE ="\r\n";
	
	public static final String BINARY_DIGITS = "01";
	public static final String DECIMAL_DIGITS = "0123456789";
	public static final String HEXADECIMAL_DIGITS = "0123456789ABCDEF";
	public static final String BASE32_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
	public static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvw"
			+ "xyz0123456789+/";
}