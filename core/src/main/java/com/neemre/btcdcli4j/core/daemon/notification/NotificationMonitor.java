package com.neemre.btcdcli4j.core.daemon.notification;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.notification.worker.NotificationWorker;
import com.neemre.btcdcli4j.core.daemon.notification.worker.NotificationWorkerFactory;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotificationMonitor extends Observable implements Observer, Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationMonitor.class);
	private static final int NOTIFICATION_WORKER_COUNT = 10;

	private Notifications type;
	private int serverPort;
	private ServerSocket serverSocket;
	private ExecutorService workerPool;
	private volatile boolean isActive;

	private BtcdClient client;


	public NotificationMonitor(Notifications type, int serverPort, BtcdClient client) {
		this.type = type;
		this.serverPort = serverPort;
		this.client = client;
	}

	@Override
	public void run() {
		activate();
		while(isActive) {
			try {
				Socket socket = serverSocket.accept();
				NotificationWorker worker = NotificationWorkerFactory.createWorker(type, socket, 
						client);
				worker.addObserver(this);
				workerPool.submit(worker);
			} catch (IOException e) {
				throw new RuntimeException(e); //TODO
			} finally {
				if(Thread.interrupted()) {
					deactivate();
				}
			}
		}
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	private boolean isActive() {
		return isActive;
	}

	private void activate() {
		isActive = true;
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			throw new RuntimeException(e); //TODO
		}
		workerPool = Executors.newFixedThreadPool(NOTIFICATION_WORKER_COUNT);

	}

	private void deactivate() {
		isActive = false;
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);	//TODO
			}
		}
		workerPool.shutdown();
	}
}