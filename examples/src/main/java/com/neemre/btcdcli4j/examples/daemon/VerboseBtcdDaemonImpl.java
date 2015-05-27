package com.neemre.btcdcli4j.examples.daemon;

import java.util.Properties;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.BtcdDaemonImpl;
import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.event.AlertListener;
import com.neemre.btcdcli4j.core.daemon.event.BlockListener;
import com.neemre.btcdcli4j.core.daemon.event.WalletListener;

/**A subclass of {@code BtcdDaemonImpl} that writes detailed operational data (<i>e.g.</i> the 
 * result of each method call) to {@code stdout}. This implementation is provided for informational 
 * purposes only; it is not fit for use in a production environment.*/
public class VerboseBtcdDaemonImpl extends BtcdDaemonImpl {
	
	public VerboseBtcdDaemonImpl(BtcdClient btcdProvider) throws BitcoindException,
			CommunicationException {
		super(btcdProvider);
		System.out.println("Initiating the 'bitcoind' notification daemon (type: RPC-capable)");
	}
	
	public VerboseBtcdDaemonImpl(Integer alertPort, Integer blockPort, Integer walletPort) {
		super(alertPort, blockPort, walletPort);
		System.out.println("Initiating the 'bitcoind' notification daemon (type: autonomous)");
	}

	@Override
	public void addAlertListener(AlertListener listener) {
		System.out.printf("Registering an event listener for 'ALERT' notifications (%s)\n", listener);
		super.addAlertListener(listener);
	}

	@Override
	public int countAlertListeners() {
		int listenerCount = super.countAlertListeners();
		System.out.printf("The daemon reported an alert listener count of: %s\n", listenerCount);
		return listenerCount;
	}

	@Override
	public void removeAlertListener(AlertListener listener) {
		System.out.printf("Deregistering the specified alert listener (%s)\n", listener);
		super.removeAlertListener(listener);
	}

	@Override
	public void removeAlertListeners() {
		System.out.println("Deregistering all alert listener(s)");
		super.removeAlertListeners();
	}

	@Override	public void addBlockListener(BlockListener listener) {
		System.out.printf("Registering an event listener for 'BLOCK' notifications (%s)\n", listener);
		super.addBlockListener(listener);
	}

	@Override	public int countBlockListeners() {
		int listenerCount = super.countBlockListeners();
		System.out.printf("The daemon reported a block listener count of: %s\n", listenerCount);
		return listenerCount;
	}

	@Override	public void removeBlockListener(BlockListener listener) {
		System.out.printf("Deregistering the specified block listener (%s)\n", listener);
		super.removeBlockListener(listener);
	}

	@Override	public void removeBlockListeners() {
		System.out.println("Deregistering all block listener(s)");
		super.removeBlockListeners();
	}

	@Override	public void addWalletListener(WalletListener listener) {
		System.out.printf("Registering an event listener for 'WALLET' notifications (%s)\n", listener);
		super.addWalletListener(listener);
	}

	@Override	public int countWalletListeners() {
		int listenerCount = super.countWalletListeners();
		System.out.printf("The daemon reported a wallet listener count of: %s\n", listenerCount);
		return listenerCount;
	}

	@Override	public void removeWalletListener(WalletListener listener) {
		System.out.printf("Deregistering the specified wallet listener (%s)\n", listener);
		super.removeWalletListener(listener);
	}

	@Override	public void removeWalletListeners() {
		System.out.println("Deregistering all wallet listener(s)");
		super.removeWalletListeners();
	}

	@Override	public boolean isMonitoring(Notifications notificationType) {
		boolean isMonitoringX = super.isMonitoring(notificationType);
		System.out.printf("'%s' notification monitor status was: '%s'\n", notificationType.name(),
				((isMonitoringX == true) ? "ACTIVE" : "INACTIVE"));
		return isMonitoringX;
	}

	@Override	public boolean isMonitoringAny() {
		boolean isMonitoringAny = super.isMonitoringAny();
		System.out.printf("Is listening for ANY supported notification types: %s\n", 
				((isMonitoringAny == true) ? "YES" : "NO"));
		return isMonitoringAny;
	}

	@Override	public boolean isMonitoringAll() {
		boolean isMonitoringAll = super.isMonitoringAll();
		System.out.printf("Is listening for ALL supported notification types: %s\n", 
				((isMonitoringAll == true) ? "YES" : "NO"));
		return isMonitoringAll;
	}

	@Override	public Properties getNodeConfig() {
		Properties nodeConfig = super.getNodeConfig();
		System.out.printf("Node configuration passed to & used by the daemon: '%s'\n", nodeConfig);
		return nodeConfig;
	}

	@Override	public void shutdown() {
		System.out.println("Shutting down the 'bitcoind' notification daemon");
		super.shutdown();
	}
}