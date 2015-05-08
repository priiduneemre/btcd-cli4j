package com.neemre.btcdcli4j.core.daemon.notification;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.Errors;
import com.neemre.btcdcli4j.core.daemon.NotificationHandlerException;
import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.notification.worker.NotificationWorker;
import com.neemre.btcdcli4j.core.daemon.notification.worker.NotificationWorkerFactory;
import com.neemre.btcdcli4j.core.util.StringUtils;

public class NotificationMonitor extends Observable implements Observer, Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationMonitor.class);
	private static final int WORKER_MIN_COUNT = 1;
	private static final int WORKER_MAX_COUNT = 5;
	private static final int IDLE_WORKER_TIMEOUT = 60000;
	
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
				Thread.currentThread().interrupt();
				throw new NotificationHandlerException(Errors.IO_SOCKET_UNINITIALIZED, e);
			} finally {
				if(Thread.interrupted()) {
					deactivate();
				}
			}
		}
	}

	@Override
	public synchronized void update(Observable worker, Object result) {
		worker.deleteObserver(this);
		setChanged();
		notifyObservers(result);
	}

	private boolean isActive() {
		return isActive;
	}

	private void activate() {
		Thread.currentThread().setName(getUniqueName());
		isActive = true;
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			try {
				serverSocket = new ServerSocket(0);
				LOG.warn("SODO");
			} catch (IOException e1) {
				throw new NotificationHandlerException(Errors.IO_SERVERSOCKET_UNINITIALIZED, e1);
			}
		}
		workerPool = new ThreadPoolExecutor(WORKER_MIN_COUNT, WORKER_MAX_COUNT, IDLE_WORKER_TIMEOUT,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	private void deactivate() {
		isActive = false;
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				LOG.warn("SODO");
			}
		}
		workerPool.shutdown();
	}

	private String getUniqueName() {
		return "NotificationMonitor[" + StringUtils.capitalize(type.name().toLowerCase()) + "]-" 
				+ StringUtils.random(4, Constants.DECIMAL_DIGITS);
	}
}