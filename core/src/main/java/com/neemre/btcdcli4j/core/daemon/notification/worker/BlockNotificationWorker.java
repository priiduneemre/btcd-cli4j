package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.Block;

public class BlockNotificationWorker extends NotificationWorker {

	private static final Logger LOG = LoggerFactory.getLogger(BlockNotificationWorker.class);
	
	
	public BlockNotificationWorker(Socket socket, BtcdClient client) {
		super(socket, client);
	}

	@Override
	protected Object getRelatedEntity(String headerHash) {
		Block block = new Block();
		try {
			block = getClient().getBlock(headerHash);
		} catch (BitcoindException | CommunicationException e) {
			LOG.error("SODO");	//Unable to fetch the relevant block from 'bitcoind'
			block.setHash(headerHash);
		}
		return block;
	}
}