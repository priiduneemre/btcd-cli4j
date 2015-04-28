package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;
import com.neemre.btcdcli4j.core.domain.Block;

public class BlockNotificationWorker extends NotificationWorker {

	public BlockNotificationWorker(NotificationMonitor monitor, Socket socket) {
		super(monitor, socket);
		System.out.printf("[%s] %s ** %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "BlockNotificationWorker(..)", "initiating a new "
						+ "worker instance");
	}

	@Override
	public Object getRelatedEntity(String headerHash) {
		Block block = null;
		try {
			System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "getRelatedEntity(..)", "requesting best block " +
					"that triggered the 'blockDetected' event");
			block = getClient().getBlock(headerHash);
		} catch (BitcoindException e) {
			e.printStackTrace();	//TODO
		} catch (CommunicationException e) {
			e.printStackTrace();	//TODO
		}
		return block;
	}
}