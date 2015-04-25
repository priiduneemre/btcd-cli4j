package com.neemre.btcdcli4j.core.daemon.event;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

import com.neemre.btcdcli4j.core.domain.Block;

@Getter
public abstract class BlockListener {

	private Observer observer;
	
	
	public BlockListener() {
		observer = new Observer() {
			public void update(Observable o, Object arg) {
				Block block = (Block)arg;
				blockDetected(block);
			}
		};
	}
	
	public abstract void blockDetected(Block block);
}