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
import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class NotificationWorker extends Observable implements Runnable {

	private NotificationMonitor monitor;
	private Socket socket;


	public NotificationWorker(NotificationMonitor monitor, Socket socket) {
		this.monitor = monitor;
		this.socket = socket;
	}

	public abstract Object getRelatedEntity(String notification);
	
	@Override
	public void run() {
		System.out.printf("[%s] %s >> %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "run(..)", "beginning to resolve the notification passed "
				+ "from 'NotificationMonitor'");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), Constants.UTF_8));
			StringBuilder notification = new StringBuilder();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				notification.append(line);
				System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
						getClass().getSimpleName(), "run(..)", "Appended 1 new line to notification "
						+ "body");
			}
			System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "run(..)", "Notification broadcasted by 'bitcoind' "
							+ "was: " + notification.toString().trim());
			Object relatedEntity = getRelatedEntity(notification.toString().trim());
			System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "run(..)", "Related entity was: " + relatedEntity);
			System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "run(..)", "signalling observers with 'worker finished' " 
					+ "(observer count: " + countObservers() + ")");
			setChanged();
			notifyObservers(relatedEntity);
			System.out.printf("[%s] %s << %s: %s\n", Thread.currentThread().getName(), 
					getClass().getSimpleName(), "run(..)", "Stoping notification worker instance");
		} catch (IOException e) {
			e.printStackTrace();	//TODO
		}
	}
	
	public BtcdClient getClient() {
		System.out.printf("[%s] %s >> %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "getClient(..)", "Getting 'bitcoind' core wrapper instance");
		return monitor.getClient();
	}
}