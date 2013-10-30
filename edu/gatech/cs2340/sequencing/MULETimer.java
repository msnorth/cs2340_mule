package edu.gatech.cs2340.sequencing;

import edu.gatech.cs2340.test.DebugPrinter;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Sequencing
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M6		10/11/13
 * 									Added method to end thread.	
 * 							M7		10/21/13
 * 									Changed to run asynchronously (and optionally synchronously)
 * 
 * 		Purpose: Blocks for a set amount of time.
 */
public class MULETimer implements Runnable, WaitedOn {
	private static final int DEFAULT_FREQUENCY = 50;
	
	private boolean timeout;
	private long timeout_ms;
	private Thread thread;
	
	private long totalTime;

	/**
	 * #M6
	 * Main constructor. Creates countdown timer with given time.
	 * 
	 * @param timeout_ms
	 */
	public MULETimer(long timeout_ms) {
		this.timeout_ms = timeout_ms;
		this.totalTime = timeout_ms;
		timeout = false;
	}
	
	/**
	 * Method to start timer (asynchronously).
	 */
	public void start() {
		DebugPrinter.println("Timer for " + timeout_ms + " started asynchronously.");
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Method to start timer (synchronously).
	 */
	public void runSynchronous() {
		DebugPrinter.println("Timer for " + timeout_ms + " started synchronously.");
		run();
	}
	
	@Override
	public void run() {
		while (timeout_ms > 0 && !timeout) {
			timeout_ms -= 1000/DEFAULT_FREQUENCY;
			try {
				Thread.sleep(1000/DEFAULT_FREQUENCY);
			} catch (InterruptedException e) {
				timeout = true;
			}
		}
		timeout = true;
	}
	
	/**
	 * #M6
	 * Method to get time left on the clock.
	 * Used to determine gamlin' profits.
	 * 
	 * @return
	 */
	public long getTimeRemaining() {
		return timeout_ms;
	}
	
	/**
	 * Method to terminate thread before Timer runs out.
	 */
	public void stop() {
		if (thread == null) {
			timeout = true;
		}
		else {
			thread.interrupt();
		}
	}
	
	@Override
	public boolean isFinished() {
		return timeout;
	}

	/**
	 * @return the totalTime
	 */
	public long getTotalTime() {
		return totalTime;
	}

}
