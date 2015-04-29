package com.neemre.btcdcli4j.core.daemon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.event.AlertListener;
import com.neemre.btcdcli4j.core.daemon.event.BlockListener;
import com.neemre.btcdcli4j.core.daemon.event.WalletListener;
import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;

public class BtcdDaemon {
	
	private static final Logger LOG = LoggerFactory.getLogger(BtcdDaemon.class);
	
	private Map<Notifications, NotificationMonitor> monitors;
	private Map<Notifications, Future<?>> futures;
	private ExecutorService monitorPool;
	
	private BtcdClient client;
	
	
	public BtcdDaemon(BtcdClient client) {
		LOG.info("** BtcdDaemon(): initiating the 'bitcoind' notification daemon");
		this.client = client;
		monitors = new HashMap<Notifications, NotificationMonitor>();
		futures = new HashMap<Notifications, Future<?>>();
	}
	
	public BtcdDaemon(BtcdClient client, Properties nodeConfig) {
		this(client);
		buildMonitors(0, 0, 0);
		startMonitors();
	}

	public BtcdDaemon(BtcdClient client, Integer alertPort, Integer blockPort, Integer walletPort) 
			throws IOException {
		this(client);
		buildMonitors(alertPort, blockPort, walletPort);
		startMonitors();
	}

	public void addAlertListener(AlertListener listener) {
		monitors.get(Notifications.ALERT).addObserver(listener.getObserver());
	}
	
	public int countAlertListeners() {
		return monitors.get(Notifications.ALERT).countObservers();
	}
	
	public void removeAlertListener(AlertListener listener) {
		monitors.get(Notifications.ALERT).deleteObserver(listener.getObserver());
	}
	
	public void removeAlertListeners() {
		monitors.get(Notifications.ALERT).deleteObservers();
	}

	public void addBlockListener(BlockListener listener) {
		monitors.get(Notifications.BLOCK).addObserver(listener.getObserver());
	}
	
	public int countBlockListeners() {
		return monitors.get(Notifications.BLOCK).countObservers();
	}
	
	public void removeBlockListener(BlockListener listener) {
		monitors.get(Notifications.BLOCK).deleteObserver(listener.getObserver());
	}
	
	public void removeBlockListeners() {
		monitors.get(Notifications.BLOCK).deleteObservers();
	}
	
	public void addWalletListener(WalletListener listener) {
		monitors.get(Notifications.WALLET).addObserver(listener.getObserver());
	}
	
	public int countWalletListeners() {
		return monitors.get(Notifications.WALLET).countObservers();
	}
	
	public void removeWalletListener(WalletListener listener) {
		monitors.get(Notifications.WALLET).deleteObserver(listener.getObserver());
	}
	
	public void removeWalletListeners() {
		monitors.get(Notifications.WALLET).deleteObservers();
	}
	
	public void shutdown() {
		for (Future<?> future : futures.values()) {
			future.cancel(true);
		}
	}
	
	private void buildMonitors(int alertPort, int blockPort, int walletPort) {
		monitors.put(Notifications.ALERT, new NotificationMonitor(Notifications.ALERT, alertPort, 
				null));
		monitors.put(Notifications.BLOCK, new NotificationMonitor(Notifications.BLOCK, blockPort, 
				client));
		monitors.put(Notifications.WALLET, new NotificationMonitor(Notifications.WALLET, walletPort,
				client));
		monitorPool = Executors.newFixedThreadPool(monitors.size());
	}
	
	private void startMonitors() {
		futures.put(Notifications.ALERT, monitorPool.submit(monitors.get(Notifications.ALERT)));
		futures.put(Notifications.BLOCK, monitorPool.submit(monitors.get(Notifications.BLOCK)));
		futures.put(Notifications.WALLET, monitorPool.submit(monitors.get(Notifications.WALLET)));
	}
}