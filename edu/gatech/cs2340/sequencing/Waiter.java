 package edu.gatech.cs2340.sequencing;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Sequencing
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M6		10/11/13 Stepehn		
 * 									Added waitForAny to wait on multiple processes
 * 
 * 
 * 		Purpose: Wrapper class for static waitFor method.
 * 				 Used to block execution until another thread is done.
 * 				 
 */
public abstract class Waiter {
	public static final int DEFAULT_POLL_RATE = 20; //Hz
	public int customPullRate;
	
	/**
	 * #M6
	 * Check if WaitedOn item has finished at the set rate.
	 * 
	 * @param item, customPollRate
	 */	
	public static void waitOn(WaitedOn item, int customPollRate) {
		WaitedOn[] thread = new WaitedOn[1];
		thread[0] = item;
		waitForAny(thread, customPollRate);
	}
	
	/**
	 * #M6
	 * Check if WaitedOn item has finished at the default rate.
	 * 
	 * @param item
	 */	
	public static void waitOn(WaitedOn item) {
		waitOn(item, DEFAULT_POLL_RATE);
	}
	
	/**
	 * #M6
	 * Returns when any of the WaitedOn objects complete. 
	 * The int returned signals which WaitedOn finished first.
	 * 
	 * @param threads
	 * @return
	 */
	public static int waitForAny(WaitedOn[] threads) {
		return waitForAny(threads,DEFAULT_POLL_RATE);
	}
	
	
	/**
	 * #M6
	 * Returns when any of the WaitedOn objects complete. 
	 * The int returned signals which WaitedOn finished first.
	 * Allows user to specify poll rate.
	 * 
	 * @param threads
	 * @return
	 */
	public static int waitForAny(WaitedOn[] threads, int pollRate) {
		int result = -1;
		while (result == -1) {
			for (int i=0; i<threads.length; i++) {
				if (threads[i].isFinished()) {
 					result = i;
					break;
				}
			}
			try {
				Thread.sleep(1000/pollRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
