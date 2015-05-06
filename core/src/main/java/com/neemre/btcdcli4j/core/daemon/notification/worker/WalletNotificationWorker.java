package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.Transaction;

public class WalletNotificationWorker extends NotificationWorker {
	
	private static final Logger LOG = LoggerFactory.getLogger(WalletNotificationWorker.class);
	
	
	public WalletNotificationWorker(Socket socket, BtcdClient client) {
		super(socket, client);
	}

	@Override
	protected Object getRelatedEntity(String txId) {
		Transaction transaction = new Transaction();
		try {
			transaction = getClient().getTransaction(txId);
		} catch (BitcoindException | CommunicationException e) {
			LOG.error("SODO");	//Unable to fetch the related entity from 'bitcoind'
			transaction.setTxId(txId);
		}
		return transaction;
	}
}