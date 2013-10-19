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
 * 
 * 		Purpose: Execute a single round of the game
 */
public class Round implements WaitedOn{
	private static int roundNumber;
	private boolean finished;
	private PlayerManager playerManager;
	private Map map;
	
	public Round(PlayerManager pManager, Map usedMap, int roundNum) {
		roundNumber = roundNum;
		playerManager = pManager;
		map = usedMap;
		finished = false;
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
	@Override
	public void run() {
		int numPlayers = playerManager.getTotalPlayers();
		// TODO: random events
		
		// Land Grant/Purchase phases
			if (roundNumber < 3) {
				for (int i=0; i < numPlayers; i++) {
					Player currentPlayer = playerManager.getNextPlayer();
					LandGranter granter = new LandGranter(currentPlayer, map);
					granter.run();
					Waiter.waitOn(granter);
				}
			}
			else {
				for (int i=0; i < numPlayers; i++) {
					Player currentPlayer = playerManager.getNextPlayer();
					LandPurchaser purchaser = new LandPurchaser(currentPlayer, map, roundNumber);
					purchaser.run();
					Waiter.waitOn(purchaser);
				}
			}
			
		// Land Auction phase
		// Turn
			for (int i=0; i<numPlayers; i++) {
				Player currentPlayer = playerManager.getNextPlayer();
				Turn turn = new Turn(currentPlayer, map);
				turn.run();
				Waiter.waitOn(turn);
			}
		// Production
		// Auction
		// Score screen
	}
	
	/**
	 * Finished once all phases complete
	 * Random events, land distribution, production, auctions, score screen
	 */
	@Override
	public boolean isFinished() {
		return finished;
	}	
}
