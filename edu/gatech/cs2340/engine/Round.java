package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.test.DebugPrinter;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.StatusBar;

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
 * 							M7		10/29/13 Thomas Mark
 * 									Round now calculates player order at the beginning of run.
 * 							M9		11/3/13 Thomas Mark
 * 									Added random event backend implementation.
 * 									11/10/2013 Madeleine North
 * 								    Refactoring (removing unused variables)
 * 
 * 		Purpose: Execute a single round of the game
 */
public class Round {
	private static int roundNumber;
	private final PlayerManager playerManager;
	private final Map map;
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
		DebugPrinter.println("Running round " + roundNumber +" synchronously");
		int numPlayers = playerManager.getTotalPlayers();
		playerManager.calculatePlayerOrder();
		Player[] players = playerManager.getPlayers();
				
		StatusBar statBar = new StatusBar(players);
		MainGameWindow.getInstance().setLowerPanel(statBar);
		
		// Production
		ResourceProducer resourceProducer = new ResourceProducer(map);
		resourceProducer.runSynchronous();
		
		// Random event simulator with returned message for the beginning of the round
		for (int i=0; i<numPlayers; i++) {
			playerManager.randomEventSimulator(players[i]);
		}
		
		// Land Grant/Purchase phases
		if (roundNumber < 3) { // 2 LandGrant phases (roundNumber starts at 1)
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
			
		// Turn
		for (int i=0; i<numPlayers; i++) {
			Player currentPlayer = playerManager.getNextPlayer();
			Turn turn = new Turn(currentPlayer, map);
			turn.runSynchronous();
		}
		// Auction
		// Score screen
	}
}
