package com.neemre.btcdcli4j.daemon.notification;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
import com.neemre.btcdcli4j.core.util.StringUtils;
import com.neemre.btcdcli4j.daemon.NotificationHandlerException;
import com.neemre.btcdcli4j.daemon.Notifications;
import com.neemre.btcdcli4j.daemon.notification.worker.NotificationWorker;
import com.neemre.btcdcli4j.daemon.notification.worker.NotificationWorkerFactory;

public class NotificationMonitor extends Observable implements Observer, Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationMonitor.class);
	private static final int WORKER_MIN_COUNT = 1;
	private static final int WORKER_MAX_COUNT = 5;
	private static final int IDLE_WORKER_TIMEOUT = 60000;
	private static final int IDLE_SOCKET_TIMEOUT = 5000;
	
	private Notifications type;
	private int serverPort;
	private ServerSocket serverSocket;
	private ExecutorService workerPool;
	private volatile boolean isActive;

	private BtcdClient client;


	public NotificationMonitor(Notifications type, int serverPort, BtcdClient client) {
		LOG.info("** NotificationMonitor(): initiating new '{}' notification monitor (port: '{}', "
				+ "RPC-capable: '{}')", type.name(), serverPort, ((client == null) ? "no" : "yes"));
		this.type = type;
		this.serverPort = serverPort;
		this.client = client;
	}

	@Override
	public void run() {
		activate();
		LOG.info("-- run(..): started listening for '{}' notifications on port '{}'", type.name(), 
				serverSocket.getLocalPort());
		while(isActive) {
			try {
				Socket socket = serverSocket.accept();
				NotificationWorker worker = NotificationWorkerFactory.createWorker(type, socket, 
						client);
				worker.addObserver(this);
				workerPool.submit(worker);
			} catch (SocketTimeoutException e) {
				LOG.trace("-- run(..): polling '{}' notification monitor for interrupts (socket idle "
						+ "for {}ms)", type.name(), IDLE_SOCKET_TIMEOUT);
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
		LOG.info(">> update(..): worker finished, informing listener(s) of new '{}' notification: "
				+ "'{}'", type.name(), result);
		worker.deleteObserver(this);
		setChanged();
		notifyObservers(result);
	}

	public boolean isActive() {
		return isActive;
	}

	private void activate() {
		Thread.currentThread().setName(getUniqueName());
		isActive = true;
		try {
			serverSocket = new ServerSocket(serverPort);
			serverSocket.setSoTimeout(IDLE_SOCKET_TIMEOUT);
		} catch (IOException e) {
			try {
				serverSocket = new ServerSocket(0);
				serverSocket.setSoTimeout(IDLE_SOCKET_TIMEOUT);
				LOG.warn("-- activate(..): failed to create server socket (monitor: '{}', port: "
						+ "'{}'), reverting to unused port '{}'", type.name(), serverPort, 
						serverSocket.getLocalPort());
			} catch (IOException e1) {
				throw new NotificationHandlerException(Errors.IO_SERVERSOCKET_UNINITIALIZED, e);
			}
		}
		workerPool = new ThreadPoolExecutor(WORKER_MIN_COUNT, WORKER_MAX_COUNT, IDLE_WORKER_TIMEOUT,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	private void deactivate() {
		LOG.info(">> deactivate(..): attempting to shut down '{}' notification monitor (port: '{}', "
				+ "RPC-capable: '{}')", type.name(), serverSocket.getLocalPort(), ((client == null)
						? "no" : "yes"));
		isActive = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			LOG.warn("-- deactivate(..): failed to close server socket (monitor: '{}', port: '{}'), "
					+ "message was: '{}'", type.name(), serverSocket.getLocalPort(), e.getMessage());
		}
		workerPool.shutdown();
	}

	private String getUniqueName() {
		return "NotificationMonitor[" + StringUtils.capitalize(type.name().toLowerCase()) + "]-" 
				+ StringUtils.random(4, Constants.DECIMAL_DIGITS);
	}
}