import edu.gatech.mule.utils.WaitedOn;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Sequencing
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Wrapper class for static waitFor method.
 * 				 Used to block execution until another thread is done.
 * 				 
 */
public abstract class Waiter {
	public static final int DEFAULT_POLL_RATE = 20; //Hz
	
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
	 * Check if WaitedOn item has finished at the specified rate.
	 * 
	 * @param item
	 */
	public static void waitOn(WaitedOn item, int pollRate) {
		while (true) {
			if (item.isFinished()) {
				return;
			}
			try {
				Thread.sleep(1000/pollRate);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}