package com.neemre.btcdcli4j.core.daemon.notice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.neemre.btcdcli4j.core.common.Constants;

public class Notifier implements Runnable {

	private ServerSocket serverSocket;
	private boolean isListening;


	public Notifier(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		isListening = true;
	}

	@Override
	public void run() {
		System.out.println("Waiting for 'bitcoind' to connect");
		while(isListening) {
			try {
				new NotifierTask(serverSocket.accept()).run();
			} catch (IOException e) {
				System.out.println("Unable to process received 'bitcoind' notification");
			}
		}
	}

	private class NotifierTask implements Runnable {

		private Socket socket;


		private NotifierTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("Incoming notification from 'bitcoind' detected");
			BufferedReader reader;
			try {
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), Constants.UTF_8));
				StringBuilder notification = new StringBuilder();
				for (String line = reader.readLine(); line != null; line = reader.readLine()) {
					notification.append(line);
					System.out.println("Appended 1 line to the notification body");
				}
				System.out.printf("Notification broadcasted by 'bitcoind' was: '%s'\n",
						notification.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}