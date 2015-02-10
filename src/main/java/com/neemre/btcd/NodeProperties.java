package com.neemre.btcd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum NodeProperties {
	
	RPC_PROTOCOL("node.full.bitcoind.rpc.protocol"),
	RPC_USER("node.full.bitcoind.rpc.user"),
	RPC_PASSWORD("node.full.bitcoind.rpc.password"),
	RPC_HOST("node.full.bitcoind.rpc.host"),
	RPC_PORT("node.full.bitcoind.rpc.port"),
	HTTP_AUTH_SCHEME("node.full.bitcoind.http.auth_scheme");
	
	@Getter
	@Setter
	private String key;
}