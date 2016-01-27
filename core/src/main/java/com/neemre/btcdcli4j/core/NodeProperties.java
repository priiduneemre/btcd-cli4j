package com.neemre.btcdcli4j.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**An enumeration specifying the <i>bitcoind</i> node properties currently recognized by 
 * btcd-cli4j.**/
@Getter
@ToString
@AllArgsConstructor
public enum NodeProperties {
	
    RPC_PROTOCOL("node.bitcoind.rpc.protocol", "http"),
    RPC_HOST("node.bitcoind.rpc.host", "127.0.0.1"),
    RPC_PORT("node.bitcoind.rpc.port", "8332"),
    RPC_USER("node.bitcoind.rpc.user", "user"),
    RPC_PASSWORD("node.bitcoind.rpc.password", "password"),
    HTTP_AUTH_SCHEME("node.bitcoind.http.auth_scheme", "Basic"),
    ALERT_PORT("node.bitcoind.notification.alert.port", "5158"),
    BLOCK_PORT("node.bitcoind.notification.block.port", "5159"),
    WALLET_PORT("node.bitcoind.notification.wallet.port", "5160");
	
    private final String key;
    private final String defaultValue;
}