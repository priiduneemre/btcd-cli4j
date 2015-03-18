package com.neemre.btcdcli4j.core.jsonrpc.deserialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.neemre.btcdcli4j.core.jsonrpc.domain.JsonRpcError;
import com.neemre.btcdcli4j.core.jsonrpc.domain.JsonRpcResponse;

public class JsonRpcResponseDeserializer extends JsonDeserializer<JsonRpcResponse> {

	@Override
	public JsonRpcResponse deserialize(JsonParser parser, DeserializationContext context)	
			throws IOException, JsonProcessingException {
		RawJsonRpcResponse rawRpcResponse = parser.readValueAs(RawJsonRpcResponse.class);

		return rawRpcResponse.toJsonRpcResponse();
	}

	private static class RawJsonRpcResponse {

		public JsonNode result;
		public JsonRpcError error;
		public String id;


		private JsonRpcResponse toJsonRpcResponse() {
			JsonRpcResponse rpcResponse = new JsonRpcResponse();
			rpcResponse.setResult(result.toString());
			rpcResponse.setError(error);
			rpcResponse.setId(id);
			return rpcResponse;
		} 
	}
}