package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;
import com.neemre.btcdcli4j.core.domain.Transaction;

public class WalletNotificationWorker extends NotificationWorker {
	
	public WalletNotificationWorker(NotificationMonitor monitor, Socket socket) {
		super(monitor, socket);
		System.out.printf("[%s] %s ** %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "WalletNotificationWorker(..)", "initiating a new "
						+ "worker instance");
	}

	@Override
	public Object getRelatedEntity(String txId) {
		Transaction transaction = null;
		try {
			System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "getRelatedEntity(..)", "requesting transaction " +
					"that triggered the 'walletChanged' event");
			transaction = getClient().getTransaction(txId);
		} catch (BitcoindException e) {
			e.printStackTrace();	//TODO
		} catch (CommunicationException e) {
			e.printStackTrace();	//TODO
		}
		return transaction;
	}
}