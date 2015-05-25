package com.neemre.btcdcli4j.examples.daemon;

import com.neemre.btcdcli4j.core.daemon.BtcdDaemon;

/**
 * 
 */
public class AutonomousDaemon {
	
	public static void main(String[] args) throws Exception {
		DaemonCalls daemonCalls = new DaemonCalls();
		
		BtcdDaemon daemon = daemonCalls.createAutonomousDaemon(5158, 5159, 5160);
		daemonCalls.reportNodeConfig(daemon);
		daemonCalls.reportMonitorStates(daemon);
		daemonCalls.registerEventListeners(daemon);
		daemonCalls.reportListenerCounts(daemon);
		daemonCalls.waitForNotifications(60000);
		daemonCalls.deregisterEventListeners(daemon);
		daemonCalls.reportListenerCounts(daemon);
		daemonCalls.shutdownDaemon(daemon, 10000);
		daemonCalls.reportMonitorStates(daemon);
	}
}