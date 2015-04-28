package com.neemre.btcdcli4j.core.daemon;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.event.BlockListener;
import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;

public class BtcdDaemon {
	
	private static final Logger LOG = LoggerFactory.getLogger(BtcdDaemon.class);
			
	private BtcdClient client;
	private NotificationMonitor alertMonitor;
	private Thread alertThread;
	private NotificationMonitor blockMonitor;
	private Thread blockThread;
	private NotificationMonitor walletMonitor;
	private Thread walletThread;

	
	private BtcdDaemon(BtcdClient client) {
		LOG.info("** BtcdDaemon(): initiating the 'bitcoind' notification daemon");
	}

	public BtcdDaemon(Integer alertPort) throws IOException {
		this(null, alertPort, null, null);
	}
	
	public BtcdDaemon(BtcdClient client, Integer alertPort, Integer blockPort) throws IOException {
		this(client, alertPort, blockPort, null);
	}
	
	public BtcdDaemon(BtcdClient client, Integer alertPort, Integer blockPort, Integer walletPort) 
			throws IOException {
		this(client);
		if (alertPort != null) {
			alertMonitor = new NotificationMonitor(Notifications.ALERT, client, alertPort);
			alertThread = new Thread(alertMonitor);
			alertThread.start();
		}
		if(blockPort != null) {
			System.out.printf("[%s] %s ** %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "BtcdDaemon(..)", "Constructing a new "
							+ "NotificationMonitor (type: BLOCK monitor)");
			blockMonitor = new NotificationMonitor(Notifications.BLOCK, client, blockPort);
			blockThread = new Thread(blockMonitor);
			blockThread.start();
		}
		if(walletPort != null) {
			walletMonitor = new NotificationMonitor(Notifications.WALLET, client, walletPort);
			walletThread = new Thread(walletMonitor);
			walletThread.start();
		}
	}
	
	public void addBlockListener(BlockListener listener) {
		blockMonitor.addObserver(listener.getObserver());
		System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "addBlockListener(..)", "Adding a new block listener "
				+ "to NotificationMonitor (type: BLOCK monitor)");
	}
}