package com.neemre.btcdcli4j.core.daemon.event;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

import com.neemre.btcdcli4j.core.domain.Transaction;

@Getter
public abstract class WalletListener {

	private Observer observer;


	public WalletListener() {
		observer = new Observer() {
			public void update(Observable o, Object arg) {
				Transaction transaction = (Transaction)arg;
				System.out.printf("[%s] %s << %s: %s\n", Thread.currentThread().getName(), 
						getClass().getSimpleName(), "update(..)", "received 'notification received'"
						+ ", invoking 'walletChanged(..)'");
				walletChanged(transaction);
			}
		};
	}

	public abstract void walletChanged(Transaction transaction);
}