package com.neemre.btcdcli4j.core.daemon.notification.worker;

import java.net.Socket;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.neemre.btcdcli4j.core.daemon.Notifications;
import com.neemre.btcdcli4j.core.daemon.notification.NotificationMonitor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationWorkerFactory {

	public static NotificationWorker createWorker(Notifications type, NotificationMonitor monitor, 
			Socket socket) {
		if (type.equals(Notifications.ALERT)) {
			System.out.printf("[%s] %s << %s: %s\n", Thread.currentThread().getName(), 
					NotificationWorkerFactory.class.getSimpleName(), "createWorker(..)", 
					"Returning a new 'NotificationWorker' (type: " + type.name() + "T worker)");
			return new AlertNotificationWorker(monitor, socket);
		} else if (type.equals(Notifications.BLOCK)) {
			System.out.printf("[%s] %s << %s: %s\n", Thread.currentThread().getName(), 
					NotificationWorkerFactory.class.getSimpleName(), "createWorker(..)", 
					"Returning a new 'NotificationWorker' (type: " + type.name() + " worker)");
			return new BlockNotificationWorker(monitor, socket);
		} else if (type.equals(Notifications.WALLET)) {
			System.out.printf("[%s] %s << %s: %s\n", Thread.currentThread().getName(), 
					NotificationWorkerFactory.class.getSimpleName(), "createWorker(..)", 
					"Returning a new 'NotificationWorker' (type: " + type.name() + " worker)");
			return new WalletNotificationWorker(monitor, socket);
		} else {
			throw new IllegalArgumentException("I am broken.");		//TODO
		}
	}
}