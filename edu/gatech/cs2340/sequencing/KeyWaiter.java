package edu.gatech.cs2340.sequencing;

import edu.gatech.cs2340.io.InputReceiver;
import edu.gatech.cs2340.io.KeyboardAdapter;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Control: Sequencing
 * 		Created for:		M6		10/11/13
 * 		Modifications:						
 * 
 * 
 * 
 * 		Purpose: Wait for a specified key to be pressed. Runs in GUI thread.
 */
public class KeyWaiter implements InputReceiver, WaitedOn{
	private boolean keyReceived;
	private String target;
	
	/**
	 * Constructor. Takes in key that's waited for
	 * @param targetKey
	 */
	public KeyWaiter(KeyboardAdapter.KEY_NAME targetKey) {
		target = KeyboardAdapter.KEY_CONFIG[targetKey.ordinal()];
	}

	/**
	 * Finished when the target key is pressed
	 */
	@Override
	public boolean isFinished() {
		return keyReceived;
	}

	/**
	 * Receive input from the KeyboardAdapter
	 */
	@Override
	public void receiveInput(String input) {
		if (input.equals(target)) {
			keyReceived = true;
		}
	}
}
