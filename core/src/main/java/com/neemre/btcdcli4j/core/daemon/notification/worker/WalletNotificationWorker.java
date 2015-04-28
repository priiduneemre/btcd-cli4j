package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.Transaction;

public class WalletNotificationWorker extends NotificationWorker {
	
	public WalletNotificationWorker(Socket socket, BtcdClient client) {
		super(socket, client);
	}

	@Override
	public Object getRelatedEntity(String txId) {
		Transaction transaction = null;
		try {
			transaction = getClient().getTransaction(txId);
		} catch (BitcoindException e) {
			e.printStackTrace();	//TODO
		} catch (CommunicationException e) {
			e.printStackTrace();	//TODO
		}
		return transaction;
	}
}