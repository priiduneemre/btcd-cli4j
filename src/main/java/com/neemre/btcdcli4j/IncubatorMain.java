package com.neemre.btcdcli4j;

import java.math.BigDecimal;

import com.neemre.btcdcli4j.jsonrpc.JsonParser;

public class IncubatorMain {
	
	public static void main(String[] args) {
		JsonParser parser = new JsonParser();

		System.out.println(parser.parseString("\"nul\"\""));
	}
}
