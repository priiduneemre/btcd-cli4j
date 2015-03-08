package com.neemre.btcdcli4j.jsonrpc.deserialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neemre.btcdcli4j.domain.AddressOverview;
import com.neemre.btcdcli4j.jsonrpc.JsonPrimitiveParser;

public class AddressOverviewDeserializer extends JsonDeserializer<AddressOverview> {
	
	private static final int ADDRESS_INDEX = 0;
	private static final int BALANCE_INDEX = 1;
	private static final int ACCOUNT_INDEX = 2;

	private JsonPrimitiveParser parser;
	
	
	public AddressOverviewDeserializer() {
		parser = new JsonPrimitiveParser();
	}
	
	@Override
	public AddressOverview deserialize(JsonParser parser, DeserializationContext context)	
			throws IOException, JsonProcessingException {
		List<Object> propertyList = context.readValue(parser, context.getTypeFactory()
				.constructCollectionType(ArrayList.class, Object.class));
		return toAddressOverview(propertyList);
	}
	
	private AddressOverview toAddressOverview(List<Object> propertyList) {
		AddressOverview addressOverview = new AddressOverview();
		addressOverview.setAddress(parser.parseString(propertyList.get(ADDRESS_INDEX).toString()));
		addressOverview.setBalance(parser.parseBigDecimal(propertyList.get(BALANCE_INDEX).toString()));
		addressOverview.setAccount(parser.parseString(propertyList.get(ACCOUNT_INDEX).toString()));
		return addressOverview;
	}
}