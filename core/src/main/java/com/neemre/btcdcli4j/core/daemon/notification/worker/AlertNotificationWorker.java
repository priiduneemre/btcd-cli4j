package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;

public class AlertNotificationWorker extends NotificationWorker {

	public AlertNotificationWorker(NotificationMonitor monitor, Socket socket) {
		super(monitor, socket);
		System.out.printf("[%s] %s ** %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "AlertNotificationWorker(..)", "initiating a new "
				+ "worker instance");
	}

	@Override
	public Object getRelatedEntity(String alert) {
		System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
				getClass().getSimpleName(), "getRelatedEntity(..)", "returning alert message that "
				+ "triggered the 'alertReceived' event");
		return alert;
	}
}