package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.common.Constants;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class NotificationWorker extends Observable implements Runnable {

	private Socket socket;
	private BtcdClient client;


	public NotificationWorker(Socket socket, BtcdClient client) {
		this.socket = socket;
		this.client = client;
	}

	public abstract Object getRelatedEntity(String notification);

	@Override
	public void run() {
		try {
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
			throw new RuntimeException(e);	//TODO
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					throw new RuntimeException(e);	//TODO
				}
			}
		}
	}
}