package com.neemre.btcdcli4j.examples.daemon;

import java.util.Properties;

import com.neemre.btcdcli4j.core.daemon.BtcdDaemon;
import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.event.AlertListener;
import com.neemre.btcdcli4j.core.daemon.event.BlockListener;
import com.neemre.btcdcli4j.core.daemon.event.WalletListener;

/**An explanatory decorator for btcd-cli4j daemon ({@code BtcdDaemon}) implementations. Calling any
 * of the methods below will cause a short overview (<i>i.e.</i> of the results of the operation) to 
 * be written to {@code stdout}.*/
public class VerboseBtcdDaemon implements BtcdDaemon {

	private BtcdDaemon daemon;


	public VerboseBtcdDaemon(BtcdDaemon daemon) {
		if(daemon.getNodeConfig().size() == 3) {
			System.out.println("Initiating the 'bitcoind' notification daemon (type: autonomous)");
		} else {
			System.out.println("Initiating the 'bitcoind' notification daemon (type: RPC-capable)");
		}
		this.daemon = daemon;
	}

	public void addAlertListener(AlertListener listener) {
		System.out.printf("Registering an event listener for 'ALERT' notifications (%s)\n", listener);
		daemon.addAlertListener(listener);
	}

	public int countAlertListeners() {
		int listenerCount = daemon.countAlertListeners();
		System.out.printf("The daemon reported an alert listener count of: %s\n", listenerCount);
		return listenerCount;
	}

	public void removeAlertListener(AlertListener listener) {
		System.out.printf("Deregistering the specified alert listener (%s)\n", listener);
		daemon.removeAlertListener(listener);
	}

	public void removeAlertListeners() {
		System.out.println("Deregistering all alert listener(s)");
		daemon.removeAlertListeners();
	}
	public void addBlockListener(BlockListener listener) {
		System.out.printf("Registering an event listener for 'BLOCK' notifications (%s)\n", listener);
		daemon.addBlockListener(listener);
	}
		public int countBlockListeners() {
		int listenerCount = daemon.countBlockListeners();
		System.out.printf("The daemon reported a block listener count of: %s\n", listenerCount);
		return listenerCount;
	}
		public void removeBlockListener(BlockListener listener) {
		System.out.printf("Deregistering the specified block listener (%s)\n", listener);
		daemon.removeBlockListener(listener);
	}
		public void removeBlockListeners() {
		System.out.println("Deregistering all block listener(s)");
		daemon.removeBlockListeners();
	}
		public void addWalletListener(WalletListener listener) {
		System.out.printf("Registering an event listener for 'WALLET' notifications (%s)\n", listener);
		daemon.addWalletListener(listener);
	}
		public int countWalletListeners() {
		int listenerCount = daemon.countWalletListeners();
		System.out.printf("The daemon reported a wallet listener count of: %s\n", listenerCount);
		return listenerCount;
	}
		public void removeWalletListener(WalletListener listener) {
		System.out.printf("Deregistering the specified wallet listener (%s)\n", listener);
		daemon.removeWalletListener(listener);
	}
		public void removeWalletListeners() {
		System.out.println("Deregistering all wallet listener(s)");
		daemon.removeWalletListeners();
	}
		public boolean isMonitoring(Notifications notificationType) {
		boolean isMonitoringX = daemon.isMonitoring(notificationType);
		System.out.printf("'%s' notification monitor status was: '%s'\n", notificationType.name(),
				((isMonitoringX == true) ? "ACTIVE" : "INACTIVE"));
		return isMonitoringX;
	}
		public boolean isMonitoringAny() {
		boolean isMonitoringAny = daemon.isMonitoringAny();
		System.out.printf("Is listening for ANY supported notification types: %s\n", 
				((isMonitoringAny == true) ? "YES" : "NO"));
		return isMonitoringAny;
	}
		public boolean isMonitoringAll() {
		boolean isMonitoringAll = daemon.isMonitoringAll();
		System.out.printf("Is listening for ALL supported notification types: %s\n", 
				((isMonitoringAll == true) ? "YES" : "NO"));
		return isMonitoringAll;
	}
		public Properties getNodeConfig() {
		Properties nodeConfig = daemon.getNodeConfig();
		System.out.printf("Node configuration passed to & used by the daemon: '%s'\n", nodeConfig);
		return nodeConfig;
	}
		public void shutdown() {
		System.out.println("Shutting down the 'bitcoind' notification daemon");
		daemon.shutdown();
	}
}