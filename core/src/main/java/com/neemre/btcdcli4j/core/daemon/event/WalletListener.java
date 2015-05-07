package com.neemre.btcdcli4j.core.daemon.event;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

import com.neemre.btcdcli4j.core.domain.Transaction;

public abstract class WalletListener {

	@Getter
	private Observer observer;


	public WalletListener() {
		observer = new Observer() {
			@Override
			public void update(Observable monitor, Object cause) {
				Transaction transaction = (Transaction)cause;
				walletChanged(transaction);
			}
		};
	}

	public abstract void walletChanged(Transaction transaction);
}