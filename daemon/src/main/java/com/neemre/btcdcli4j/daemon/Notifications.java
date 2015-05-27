package com.neemre.btcdcli4j.daemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**An enumeration specifying the <i>bitcoind</i> notification types currently supported by 
 * btcd-cli4j.**/
@Getter
@ToString
@AllArgsConstructor
public enum Notifications {

	ALERT,
	BLOCK,
	WALLET;
}