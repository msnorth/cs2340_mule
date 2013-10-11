package edu.gatech.cs2340.sequencing;

import edu.gatech.cs2340.io.InputReceiver;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Control: Sequencing
 * 		Created for:		M6		10/11/13
 * 		Modifications:						
 * 
 * 
 * 
 * 		Purpose: Wait for a specified key to be pressed
 */
public class KeyWaiter implements InputReceiver, WaitedOn{
	private boolean keyReceived;
	private String target;
	
	public KeyWaiter(String targetKey) {
		target = targetKey;
	}
	
	@Override
	public void run() {}

	@Override
	public boolean isFinished() {
		return keyReceived;
	}

	@Override
	public void receiveInput(String input) {
		if (input.equals(target)) {
			keyReceived = true;
		}
	}
}
