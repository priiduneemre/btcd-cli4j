package com.neemre.btcdcli4j.core.daemon;

import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.NodeProperties;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.common.AgentConfigurator;
import com.neemre.btcdcli4j.core.domain.Block;

public class DaemonConfigurator extends AgentConfigurator {

	private static final Logger LOG = LoggerFactory.getLogger(DaemonConfigurator.class);
	
	
	@Override
	public Set<NodeProperties> getRequiredProperties() {
		return EnumSet.of(NodeProperties.ALERT_PORT, NodeProperties.BLOCK_PORT, 
				NodeProperties.WALLET_PORT);
	}

	public Properties checkNodeConfig(Integer alertPort, Integer blockPort, Integer walletPort) {
		Properties nodeConfig = toNodeConfig(alertPort, blockPort, walletPort);
		return checkNodeConfig(nodeConfig);
	}
	
	public BtcdClient checkBtcdProvider(BtcdClient btcdProvider) {
		try {
			String headerHash = btcdProvider.getBestBlockHash();
			if (headerHash != null) {
				Block bestBlock = (Block)btcdProvider.getBlock(headerHash, true);
				if (bestBlock != null) {
					long currentTime = System.currentTimeMillis() / 1000;
					if ((currentTime - bestBlock.getTime()) > TimeUnit.HOURS.toSeconds(6)) {
						LOG.warn("Best block reported by the node was mined >6 hours ago; "
								+ "please check your network connection");
					}
				}
			}
		} catch (BitcoindException e) {
			e.printStackTrace();	//TODO
		} catch (CommunicationException e) {
			e.printStackTrace();	//TODO
		}
		return btcdProvider;
	}

	private Properties toNodeConfig(Integer alertPort, Integer blockPort, Integer walletPort) {
		Properties nodeConfig = new Properties();
		if (alertPort != null) {
			nodeConfig.setProperty(NodeProperties.ALERT_PORT.getKey(), String.valueOf(alertPort));
		}
		if (blockPort != null) {
			nodeConfig.setProperty(NodeProperties.BLOCK_PORT.getKey(), String.valueOf(blockPort));
		}
		if (walletPort != null) {
			nodeConfig.setProperty(NodeProperties.WALLET_PORT.getKey(), String.valueOf(walletPort));
		}
		return nodeConfig;
	}
}