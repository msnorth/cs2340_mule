package edu.gatech.mule.utils;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Control
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Stephen
 * 		Modifications:								
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
