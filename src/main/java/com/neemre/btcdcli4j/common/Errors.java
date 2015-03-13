package com.neemre.btcdcli4j.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum Errors {

	ARGS_COUNT_UNEVEN(1001001, "Expected the argument count to be 'even', but was 'uneven' instead."),
	ARGS_COUNT_UNEQUAL(1001002, "Expected the argument count to be 'equal', but was 'unequal' instead."),
	ARGS_NULL(1001003, "Expected a non-null argument, but got 'null' instead."),
	ARGS_CONTAIN_NULL(100104, "Expected only non-null arguments, but got >0 'null' instead."),
	ARGS_HTTP_METHOD_UNSUPPORTED(100105, "Expected the argument to be a valid HTTP method, but was "
			+ "invalid/unsupported instead."),
	ARGS_HTTP_AUTHSCHEME_UNSUPPORTED(100106, "Expected the argument to be a valid HTTP auth scheme, but was "
			+ "invalid/unsupported instead."),
	REQUEST_HTTP_FAULT(1002001, "Request execution failed due to a HTTP-specific error."),
	RESPONSE_JSONRPC_NULL(1003001, "Expected a non-null JSON-RPC response object, but got 'null' instead."),
	RESPONSE_JSONRPC_NULL_ID(1003002, "Expected a non-null JSON-RPC response id, but got 'null' instead."),
	RESPONSE_JSONRPC_UNEQUAL_IDS(1003003, "Expected the JSON-RPC request and response ids to be 'equal', but"
			+ " were 'unequal' instead."),
	IO_STREAM_UNCLOSED(1004001, "Unable to close the specified stream."),
	IO_UNKNOWN(1004002, "The operation failed due to an unknown IO exception."),
	PARSE_URI_FAILED(1005001, "Unable to parse the specified URI."),
	PARSE_JSON_UNKNOWN(1005002, "An unknown exception occurred while parsing/generating JSON content."),
	PARSE_JSON_MALFORMED(1005003, "Unable to parse the specified JSON content (malformed syntax detected)."),
	MAP_JSON_UNKNOWN(1006001, "An unknown exception ocurred while mapping the JSON content.");
	
	@Getter
	@Setter
	private int code;
	@Getter
	@Setter
	private String message;
	
	
	public String getDescription() {
		return String.format("Error #%s: %s", code, message);
	}
}