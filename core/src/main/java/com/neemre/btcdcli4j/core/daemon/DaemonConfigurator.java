package com.neemre.btcdcli4j.core.daemon;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.NodeProperties;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.common.AgentConfigurator;
import com.neemre.btcdcli4j.core.common.Errors;
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
			throw new IllegalArgumentException(Errors.ARGS_BTCD_PROVIDER_NULL.getDescription());
		}
		return btcdProvider;
	}

	public boolean checkNodeLiveness(Info info) {
		if (info == null) {
			LOG.error("-- checkNodeLiveness(..): node did not respond to JSON-RPC API probe request; "
					+ "please check your 'bitcoind' configuration");
			return false;
		}
		return true;
	}

	public boolean checkMonitorStates(Map<Notifications, Future<Void>> futures) {
		boolean isAllActive = true;
		for(Notifications notificationType : Notifications.values()) {
			Future<Void> monitorHandle = futures.get(notificationType);
			if((monitorHandle == null) || (monitorHandle.isDone())) {
				LOG.warn("-- checkMonitorStates(..): no active '{}' notification monitor detected "
						+ "(reason: {})", notificationType.name(), ((monitorHandle == null) ? 
								"failure to launch" : "task terminated prematurely"));
				isAllActive = false;
			}
		}
		return isAllActive;
	}
}