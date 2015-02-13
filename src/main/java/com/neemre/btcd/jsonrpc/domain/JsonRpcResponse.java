package com.neemre.btcd.jsonrpc.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neemre.btcd.jsonrpc.domain.misc.JsonRpcResponseDeserializer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = JsonRpcResponseDeserializer.class)
public class JsonRpcResponse extends JsonRpcMessage {
	
	private String result;
	private JsonRpcError error;
}