package com.neemre.btcd.jsonrpc.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class JsonRpcRequest<T> extends JsonRpcMessage {

	private String method;
	private List<T> params;
}