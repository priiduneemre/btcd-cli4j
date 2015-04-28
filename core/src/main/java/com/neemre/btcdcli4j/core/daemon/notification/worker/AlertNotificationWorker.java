package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

public class AlertNotificationWorker extends NotificationWorker {

	public AlertNotificationWorker(Socket socket) {
		super(socket, null);
	}

	@Override
	public Object getRelatedEntity(String alert) {
		return alert;
	}
}