package com.neemre.btcdcli4j.core.daemon.notification.worker;

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
import com.neemre.btcdcli4j.core.daemon.NotificationHandlerException;
import com.neemre.btcdcli4j.core.util.StringUtils;

public abstract class NotificationWorker extends Observable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationWorker.class);
	
	private Socket socket;
	@Getter
	private BtcdClient client;


	public NotificationWorker(Socket socket, BtcdClient client) {
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().setName(getUniqueName());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), Constants.UTF_8));
			StringBuilder notification = new StringBuilder();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				notification.append(line);
			}
			Object relatedEntity = getRelatedEntity(notification.toString().trim());
			setChanged();
			notifyObservers(relatedEntity);
		} catch (IOException e) {
			throw new NotificationHandlerException(Errors.IO_UNKNOWN, e);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					LOG.warn("SODO");
				}
			}
		}
	}

	protected abstract Object getRelatedEntity(String notification);
	
	private String getUniqueName() {
		return getClass().getSimpleName() + "-" + StringUtils.random(4, Constants.DECIMAL_DIGITS);
	}
}