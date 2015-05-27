package com.neemre.btcdcli4j.examples.daemon;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.BtcdDaemon;
import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.event.AlertListener;
import com.neemre.btcdcli4j.core.daemon.event.BlockListener;
import com.neemre.btcdcli4j.core.daemon.event.WalletListener;
import com.neemre.btcdcli4j.core.domain.Block;
import com.neemre.btcdcli4j.core.domain.Transaction;
import com.neemre.btcdcli4j.examples.util.OutputUtils;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**An example demonstrating the use of <i>bitcoind</i>'s 'callback-via-shell-command' API via 
 * RPC-capable {@code BtcdDaemon} instances.*/
public class RpcCapableDaemon {

	public static void main(String[] args) throws Exception {
		BtcdClient btcdProvider = ResourceUtils.getBtcdProvider();
		BtcdDaemon daemon = new VerboseBtcdDaemonImpl(btcdProvider);

		daemon.getNodeConfig();
		OutputUtils.printSeparator();

		System.out.println("Verifying that the daemon started successfully:");
		daemon.isMonitoring(Notifications.ALERT);
		daemon.isMonitoring(Notifications.BLOCK);
		daemon.isMonitoring(Notifications.WALLET);
		daemon.isMonitoringAny();
		daemon.isMonitoringAll();
		OutputUtils.printSeparator();

		daemon.addAlertListener(new AlertListener() {
			@Override
			public void alertReceived(String alert) {
				System.out.printf("New alert received! (Event details: '%s')\n", alert);
			}
		});
		daemon.addBlockListener(new BlockListener() {
			@Override
			public void blockDetected(Block block) {
				System.out.printf("New block detected! (Event details: '%s')\n", block);
			}
		});
		daemon.addWalletListener(new WalletListener() {
			@Override
			public void walletChanged(Transaction transaction) {
				System.out.printf("New wallet transaction completed! (Event details: '%s')\n", 
						transaction);
			}
		});
		daemon.countAlertListeners();
		daemon.countBlockListeners();
		daemon.countWalletListeners();
		OutputUtils.printSeparator();

		System.out.println("Suspending main thread to capture notifications from 'bitcoind' "
				+ "(sleep time: 120 seconds)");
		OutputUtils.printSeparator();
		Thread.sleep(120000);
		OutputUtils.printSeparator();

		daemon.removeAlertListeners();
		daemon.removeBlockListeners();
		daemon.removeWalletListeners();
		daemon.countAlertListeners();
		daemon.countBlockListeners();
		daemon.countWalletListeners();
		OutputUtils.printSeparator();

		daemon.shutdown();
		System.out.println("Suspending main thread to guarantee complete shutdown of the daemon "
				+ "instance (sleep time: 10 seconds)");
		Thread.sleep(10000);
		OutputUtils.printSeparator();

		System.out.println("Verifying that the daemon stopped successfully:");
		daemon.isMonitoring(Notifications.ALERT);
		daemon.isMonitoring(Notifications.BLOCK);
		daemon.isMonitoring(Notifications.WALLET);
		daemon.isMonitoringAny();
		daemon.isMonitoringAll();
		OutputUtils.printSeparator();
	}
}