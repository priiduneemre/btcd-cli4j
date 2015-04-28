package com.neemre.btcdcli4j.core.daemon.event;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

@Getter
public abstract class AlertListener {

	private Observer observer;
	
	
	public AlertListener() {
		observer = new Observer() {
			public void update(Observable o, Object arg) {
				String alert = (String)arg;
				System.out.printf("[%s] %s -- %s: %s\n", Thread.currentThread().getName(), 
						getClass().getSimpleName(), "update(..)", "received 'notification received'"
						+ ", invoking 'alertReceived(..)'");
				alertReceived(alert);
			}
		};
	}
	
	public abstract void alertReceived(String alert);
}