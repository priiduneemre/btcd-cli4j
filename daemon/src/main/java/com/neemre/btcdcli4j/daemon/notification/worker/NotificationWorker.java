package com.neemre.btcdcli4j.daemon.notification.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Errors;
import com.neemre.btcdcli4j.core.util.StringUtils;
import com.neemre.btcdcli4j.daemon.NotificationHandlerException;
import com.neemre.btcdcli4j.daemon.Notifications;

public abstract class NotificationWorker extends Observable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationWorker.class);
	
	private Socket socket;
	@Getter
	private BtcdClient client;


	public NotificationWorker(Socket socket, BtcdClient client) {
		LOG.debug("** NotificationWorker(): launching new '{}' notification worker (RPC-capable: "
				+ "'{}')", getType().name(), ((client == null) ? "no" : "yes"));
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().setName(getUniqueName());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), Constants.UTF_8));
			StringBuilder notificationBuilder = new StringBuilder();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				notificationBuilder.append(line);
			}
			String notification = notificationBuilder.toString().trim();
			LOG.debug("-- run(..): received new '{}' notification as (raw): '{}'", getType().name(),
					notification);
			Object relatedEntity = getRelatedEntity(notification);
			setChanged();
			notifyObservers(relatedEntity);
		} catch (IOException e) {
			throw new NotificationHandlerException(Errors.IO_UNKNOWN, e);
		} finally {
			if (socket != null) {
				try {
					LOG.debug("-- run(..): attempting to recycle old '{}' notification worker (RPC-"
							+ "capable: '{}')", getType().name(), ((client == null) ? "no" : "yes"));
					socket.close();
				} catch (IOException e) {
					LOG.warn("<< run(..): failed to close socket (worker: '{}', port: '{}'), message "
							+ "was: '{}'", getType().name(), socket.getLocalPort(), e.getMessage());
				}
			}
		}
	}

	protected abstract Object getRelatedEntity(String notification);
	
	private String getUniqueName() {
		return getClass().getSimpleName() + "-" + StringUtils.random(4, Constants.DECIMAL_DIGITS);
	}
	
	private Notifications getType() {
		return Notifications.valueOf(getClass().getSimpleName().replaceAll("NotificationWorker", "")
				.toUpperCase());
	}
}