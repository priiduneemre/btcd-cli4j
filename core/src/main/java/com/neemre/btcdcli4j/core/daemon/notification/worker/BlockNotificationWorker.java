package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.Block;

public class BlockNotificationWorker extends NotificationWorker {

	public BlockNotificationWorker(Socket socket, BtcdClient client) {
		super(socket, client);
	}

	@Override
	protected Object getRelatedEntity(String headerHash) {
		Block block = null;
		try {
			block = getClient().getBlock(headerHash);
		} catch (BitcoindException e) {
			e.printStackTrace();	//TODO
		} catch (CommunicationException e) {
			e.printStackTrace();	//TODO
		}
		return block;
	}
}