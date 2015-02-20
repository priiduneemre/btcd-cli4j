package com.neemre.btcdcli4j;

import java.util.UUID;

import com.neemre.btcdcli4j.jsonrpc.JsonParser;

public class IncubatorMain {
	
	public static void main(String[] args) {
		JsonParser parser = new JsonParser();

		System.out.println(parser.parseString("\"nul\"\""));
		
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.printf("Unique ID: '%s'\n", id);
	}
}
