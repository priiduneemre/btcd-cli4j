package com.neemre.btcdcli4j.core.daemon;

import java.util.Properties;

import com.neemre.btcdcli4j.core.daemon.event.AlertListener;
import com.neemre.btcdcli4j.core.daemon.event.BlockListener;
import com.neemre.btcdcli4j.core.daemon.event.WalletListener;

public interface BtcdDaemon {
	
	void addAlertListener(AlertListener listener);
	
	int countAlertListeners();
	
	void removeAlertListener(AlertListener listener);
	
	void removeAlertListeners();
	
	void addBlockListener(BlockListener listener);
	
	int countBlockListeners();
	
	void removeBlockListener(BlockListener listener);
	
	void removeBlockListeners();
	
	void addWalletListener(WalletListener listener);
	
	int countWalletListeners();
	
	void removeWalletListener(WalletListener listener);
	
	void removeWalletListeners();
	
	boolean isMonitoring(Notifications notificationType);
	
	boolean isMonitoringAny();
	
	boolean isMonitoringAll();
	
	Properties getNodeConfig();
	
	void shutdown();
}