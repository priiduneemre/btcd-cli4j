package com.neemre.btcdcli4j.examples.daemon;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.BtcdDaemon;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**
 * 
 */
public class RpcCapableDaemon {
	
	public static void main(String[] args) throws Exception {
		BtcdClient btcdProvider = ResourceUtils.getBtcdProvider();
		DaemonCalls daemonCalls = new DaemonCalls();
		
		BtcdDaemon daemon = daemonCalls.createRpcCapableDaemon(btcdProvider);
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