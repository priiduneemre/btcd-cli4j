package com.neemre.btcdcli4j.core.daemon;

import java.util.EnumSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.NodeProperties;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.common.AgentConfigurator;
import com.neemre.btcdcli4j.core.domain.Info;

public class DaemonConfigurator extends AgentConfigurator {

	private static final Logger LOG = LoggerFactory.getLogger(DaemonConfigurator.class);
	
	
	@Override
	public Set<NodeProperties> getRequiredProperties() {
		return EnumSet.of(NodeProperties.ALERT_PORT, NodeProperties.BLOCK_PORT, 
				NodeProperties.WALLET_PORT);
	}

	public BtcdClient checkBtcdProvider(BtcdClient btcdProvider) {
		if (btcdProvider == null) {
			LOG.error("TODO");
		}
		try {
			Info info = btcdProvider.getInfo();
			if (info == null) {
				LOG.error("TODO");
			}
		} catch (BitcoindException e) {
			e.printStackTrace();	//TODO
		} catch (CommunicationException e) {
			e.printStackTrace();	//TODO
		}
		return btcdProvider;
	}
}