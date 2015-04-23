package com.neemre.btcdcli4j.core.daemon.notice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletNotifier extends Notifier {

	public WalletNotifier(int port) throws IOException {
		super(port);
	}


	private static final Logger LOG = LoggerFactory.getLogger(WalletNotifier.class);
	
	
	public static void main(String[] args){
		String s = "Hello";
		String s2 = "Hello";
		System.out.println(s == s2);
	}
}