package com.neemre.btcdcli4j;

import com.neemre.btcdcli4j.jsonrpc.JsonMapper;

public class StaticMain3 {
	
	public static void main(String[] args) {
		JsonMapper mapper = new JsonMapper();
		String testString = "\"This is a \\\"typical\\\" JSON string containing escaped double-quotes.\"";
		System.out.printf("%s: %s\n", "testString", testString);
		String decodedString = mapper.parseString(testString);
		System.out.printf("%s: %s\n", "decodedString", decodedString);
	}
}
