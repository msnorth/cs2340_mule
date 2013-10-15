package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.sequencing.WaitedOn;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Engine
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy, Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Execute a single round of the game
 */
public class Round implements WaitedOn{
	private int roundNumber;
	private boolean finished;

	/**
	 * #M6
	 * Method to run a single round.
	 * Performs the following actions:
	 * 		Random events
	 * 		Call for Players to be ordered
	 * 		Land distribution phase
	 * 			LandGranter in early rounds for each Player
	 * 			LandAuction in later rounds
	 * 		Free range phase
	 * 			each Player takes a Turn
	 * 		Production phase
	 * 		Auction Phase
	 * 		Score screen
	 */
	@Override
	public void run() {
		// random events
		// ordering of players
		// land distribution phase
			// LandGranter implementation
			// LandAuction implementation
		// Player turns
		// Production
		// Auction
		// Score screen
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}	
}
