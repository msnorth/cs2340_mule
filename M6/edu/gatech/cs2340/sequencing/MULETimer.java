package edu.gatech.cs2340.sequencing;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Sequencing
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Blocks for a set amount of time.
 */
public class MULETimer implements Runnable, WaitedOn {
	private static final int DEFAULT_FREQUENCY = 50;
	
	private boolean timeout;
	private long timeout_ms;

	/**
	 * #M6
	 * Main constructor. Creates countdown timer with given time.
	 * 
	 * @param timeout_ms
	 */
	public MULETimer(long timeout_ms) {
		this.timeout_ms = timeout_ms;
		timeout = false;
	}
	
	@Override
	public void run() {
		while (timeout_ms > 0) {
			timeout_ms -= 1000/DEFAULT_FREQUENCY;
			try {
				Thread.sleep(1000/DEFAULT_FREQUENCY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		timeout = true;
	}
	
	@Override
	public boolean isFinished() {
		return timeout;
	}
}
