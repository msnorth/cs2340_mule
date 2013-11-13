package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.GameData;
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
	private GameData data;
	
	public Round(GameData data) {
		this.data = data;
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
		StatusBar statBar = new StatusBar(data.getPlayerManager().getPlayers());
		MainGameWindow.getInstance().setLowerPanel(statBar);
		
		// Production
		ResourceProducer resourceProducer = new ResourceProducer(data);
		resourceProducer.runSynchronous();
		
		// Random event simulator with returned message for the beginning of the round
		RandomEventGenerator randomEventGenerator = new RandomEventGenerator(data);
		randomEventGenerator.runSynchronous();
		
		// Land Grant/Purchase phases
		if (data.getRoundNum() < 2) { // 2 LandGrant phases (roundNumber starts at 1)				
			LandGranter granter = new LandGranter(data);
			granter.runSynchronous();
		}
		else {
			LandPurchaser purchaser = new LandPurchaser(data);
			purchaser.runSynchronous();
		}
			
		// Turn
		TurnLauncher turnLauncher = new TurnLauncher(data);
		turnLauncher.runSynchronous();
	}
}
