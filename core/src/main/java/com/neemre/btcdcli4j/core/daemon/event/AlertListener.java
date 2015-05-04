package com.neemre.btcdcli4j.core.daemon.event;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

public abstract class AlertListener {

	@Getter
	private Observer observer;
	
	
	public AlertListener() {
		observer = new Observer() {
			public void update(Observable o, Object arg) {
				String alert = (String)arg;
				alertReceived(alert);
			}
		};
	}
	
	public abstract void alertReceived(String alert);
}