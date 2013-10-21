package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Engine
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy, Stephen
 * 		Modifications:		M6		10/15/13 Thomas Mark
 * 									Fleshed out round methods.	
 * 							M6		10/15/13 Stephen					
 * 									Fixed some typos. Added LandPurchaser instances for later rounds.
 * 							M7      10/19/13 Shreyyas Vanarase
 * 									Changed roundNumber to static value and added a getter to get the roundNumber.
 * 							M7		10/21/13 Stephen Conway
 * 									Removed WaitedOn interface. Runs synchronously.
 * 
 * 		Purpose: Execute a single round of the game
 */
public class Round {
	private static int roundNumber;
	private PlayerManager playerManager;
	private Map map;
	
	public Round(PlayerManager pManager, Map usedMap, int roundNum) {
		roundNumber = roundNum;
		playerManager = pManager;
		map = usedMap;
	}
	/**
	 * #M7
	 * Method to get the round number
	 * @return
	 */
	public static int getRoundNumber(){
		return roundNumber;
	}
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
	public void runSynchronous() {
		int numPlayers = playerManager.getTotalPlayers();
		// TODO: random events
		
		// Land Grant/Purchase phases
			if (roundNumber < 3) {
				for (int i=0; i < numPlayers; i++) {
					Player currentPlayer = playerManager.getNextPlayer();
					LandGranter granter = new LandGranter(currentPlayer, map);
					granter.runSynchronous();
				}
			}
			else {
				for (int i=0; i < numPlayers; i++) {
					Player currentPlayer = playerManager.getNextPlayer();
					LandPurchaser purchaser = new LandPurchaser(currentPlayer, map, roundNumber);
					purchaser.runSynchronous();
				}
			}
			
		// Land Auction phase
		// Turn
			for (int i=0; i<numPlayers; i++) {
				Player currentPlayer = playerManager.getNextPlayer();
				Turn turn = new Turn(currentPlayer, map);
				turn.runSynchronous();
			}
		// Production
		// Auction
		// Score screen
	}
}
