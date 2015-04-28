package com.neemre.btcdcli4j.core.daemon.notification;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.notification.worker.NotificationWorker;
import com.neemre.btcdcli4j.core.daemon.notification.worker.NotificationWorkerFactory;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotificationMonitor extends Observable implements Observer, Runnable {

	private Notifications type;
	private BtcdClient client;
	
	private int serverPort;
	private ServerSocket serverSocket;
	private ExecutorService workerPool;
	
	private volatile boolean isRunning;


	public NotificationMonitor(Notifications type, int serverPort, BtcdClient client) {
		this.type = type;
		this.client = client;
		this.serverPort = serverPort;
		workerPool = Executors.newFixedThreadPool(10);
	}
	
	@Override
	public void run() {
		System.out.printf("[%s] %s >> %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "run(..)", "Starting a NotificationMonitor instance (type: " 
				+ type.name() + " monitor)");
		startListening();
		while(isRunning) {
			try {
				System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
						getClass().getSimpleName(), "run(..)", "Waiting to construct a new "
								+ "'NotificationWorker' (type: " + type.name() + " worker) to deal "
								+ "with the notification");
				NotificationWorker worker = NotificationWorkerFactory.createWorker(type, this, 
						serverSocket.accept());
				worker.addObserver(this);
				workerPool.submit(worker);
				System.out.printf("[%s] %s << %s: %s\n", Thread.currentThread().getName(), 
						getClass().getSimpleName(), "run(..)", "New 'NotificationWorker' (type: "
						+ type.name() + " worker) created, returning to listen on server socket "
								+ "('accept()')");;
			} catch (IOException e) {
				if(!isListening()) {
					return;
				}
				System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
						getClass().getSimpleName(), "run(..)", "Unable to process received 'bitcoind'"
								+ "notification (type: " + type.name() + " notification)");
				throw new RuntimeException(e); //TODO
			}
			if(Thread.interrupted()) {
				stopListening();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.printf("[%s] %s >> %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "update(..)", "received 'worker finished', signalling"
						+ " observers with 'notification received' (observer count: " 
						+ countObservers() + ")");
		setChanged();
		notifyObservers(arg);
	}
	
	private boolean isListening() {
		return isRunning;
	}
	
	private void startListening() {
		isRunning = true;
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			throw new RuntimeException(e); //TODO
		}
	}
	
	private void stopListening() {
		isRunning = false;
		if(serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);	//TODO
			}
		}
	}
}