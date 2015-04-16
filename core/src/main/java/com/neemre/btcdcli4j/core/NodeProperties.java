package com.neemre.btcdcli4j.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**An enumeration specifying the <i>bitcoind</i> node properties required for 
 * constructing a {@code BtcdClient} instance.*/
@Getter
@ToString
@AllArgsConstructor
public enum NodeProperties {
	
    RPC_PROTOCOL("node.bitcoind.rpc.protocol", "http"),
    RPC_HOST("node.bitcoind.rpc.host", "127.0.0.1"),
    RPC_PORT("node.bitcoind.rpc.port", "8332"),
    RPC_USER("node.bitcoind.rpc.user", "user"),
    RPC_PASSWORD("node.bitcoind.rpc.password", ""),
    HTTP_AUTH_SCHEME("node.bitcoind.http.auth_scheme", "Basic");
    
    private final String key;
    private final String defaultValue;
}