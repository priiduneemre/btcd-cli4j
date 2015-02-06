package com.neemre.btcd.jsonrpc;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonRpcResponseDeserializer extends JsonDeserializer<JsonRpcResponse> {

	@Override
	public JsonRpcResponse deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		RawJsonRpcResponse rawRpcResponse = parser.readValueAs(RawJsonRpcResponse.class);
		
		return rawRpcResponse.toJsonRpcResponse();
	}
	
	private static class RawJsonRpcResponse {
		public int id;
		public JsonNode result;
		public JsonRpcError error;
	
		JsonRpcResponse toJsonRpcResponse() {
			JsonRpcResponse rpcResponse = new JsonRpcResponse();
			rpcResponse.setId(id);
			System.out.println("Result as string: " + result.toString());
			rpcResponse.setResult(result.toString());
			rpcResponse.setError(error);
			
			return rpcResponse;
		} 
	}

}
