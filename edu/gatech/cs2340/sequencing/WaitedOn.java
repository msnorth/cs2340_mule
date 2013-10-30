package edu.gatech.cs2340.sequencing;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Control
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M7		10/21/13	Stephen
 * 									Removed extension of Runnable
 * 
 * 
 * 
 * 		Purpose: Interface implemented by classes that pause another thread.
 * 				 The paused thread should use the static Waiter.waitFor(WaitedOn item)
 * 				 to block until execution finishes.
 * 				 
 */
public interface WaitedOn {
	public boolean isFinished();
}
