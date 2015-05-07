package com.neemre.btcdcli4j.core.daemon.event;

import java.util.Observable;
import java.util.Observer;

import lombok.Getter;

import com.neemre.btcdcli4j.core.domain.Block;

public abstract class BlockListener {

	@Getter
	private Observer observer;
	
	
	public BlockListener() {
		observer = new Observer() {
			@Override
			public void update(Observable monitor, Object cause) {
				Block block = (Block)cause;
				blockDetected(block);
			}
		};
	}
	
	public abstract void blockDetected(Block block);
}