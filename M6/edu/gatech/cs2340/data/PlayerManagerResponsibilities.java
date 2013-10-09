package edu.gatech.cs2340.data;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Development Contract
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Outline design responsibilities of PlayerManager
 * 				 
 */
public interface PlayerManagerResponsibilities {
	
	/**
	 * #M6
	 * Method to reorder Players based on score.
	 * Resets the getNextPlayer method's counter.
	 */
	public abstract void calculatePlayerOrder();
	
	/**
	 * #M6
	 * Method to get next player in the sequence
	 * @return
	 */
	public abstract Player getNextPlayer();
	
}
