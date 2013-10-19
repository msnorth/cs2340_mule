package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.ui.MapManager;
import edu.gatech.cs2340.ui.MapRenderer;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Engine
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy, Stephen
 * 		Modifications:		M6 		10/16/13 Stephen Conway, Shreyyas Vanarase
 * 									Added intitial functionality to run method	
 * 							M7      10/19/13 Shreyyas Vanarase
 * 									Added local roundNumber and changed muleTimer parameter to get the turntime based off a roundNumber			
 * 
 * 
 * 
 * 		Purpose: Execute a single player's turn
 */
public class Turn implements WaitedOn {
	private boolean finished;
	private Player player;
	private Map map;
	
	/**
	 * #M6
	 * Main constructor. Takes in player to use for the turn.
	 * 
	 * @param player
	 */
	public Turn(Player player, Map map) {
		this.player = player;
		finished = false;
		this.map = map;
	}
	
	/**
	 * #M6
	 * Method to execute a turn
	 * 		free range phase
	 * 		handle feedback of MULE purchase, MULE loading, MULE deploying, Pubbing
	 */
	@Override
	public void run() {
		int roundNumber = Round.getRoundNumber();
		MULETimer timer = new MULETimer(player.calculateTurnTime(roundNumber));
		MapManager mapManager = new MapManager(player);
		WaitedOn[] waitees = {timer, mapManager};
		int killa = Waiter.waitForAny(waitees);
		if (killa == 1) { //turn ended by gambling
			
		}		
		finished = true;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
	
	

}
