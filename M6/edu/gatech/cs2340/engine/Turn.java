package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Player;
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
 * 		Purpose: Execute a single player's turn
 */
public class Turn implements WaitedOn {
	private boolean finished;
	private Player player;
	
	/**
	 * #M6
	 * Main constructor. Takes in player to use for the turn.
	 * 
	 * @param player
	 */
	public Turn(Player player) {
		this.player = player;
	}
	
	/**
	 * #M6
	 * Method to execute a turn
	 * 		free range phase
	 * 		handle feedback of MULE purchase, MULE loading, MULE deploying, Pubbing
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
	
	

}
