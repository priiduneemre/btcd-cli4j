package com.neemre.btcdcli4j.daemon.notification.worker;

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
		block.setHash(headerHash);
		if(getClient() != null) {
			try {
				LOG.debug("-- getRelatedEntity(..): fetching related block data from 'bitcoind' "
						+ "(via JSON-RPC API)");
				block = getClient().getBlock(headerHash);
			} catch (BitcoindException | CommunicationException e) {
				LOG.error("<< getRelatedEntity(..): failed to receive block data from 'bitcoind' "
						+ "(hash: '{}'), message was: '{}'", headerHash, e.getMessage());
			}
		}
		return block;
	}
}