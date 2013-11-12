package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.GameState;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.Waiter;
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
	private GameState state;
	
	
	public Round(PlayerManager pManager, Map usedMap, GameState state) {
		roundNumber = state.getRoundNumber();
		this.state = state;
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
		
		//Yeah, there are no breaks. Don't put them in.
		switch (state.getState()) {
		
			case GameState.PRODUCTION:
				ResourceProducer resourceProducer = new ResourceProducer(map, state);
				resourceProducer.runSynchronous();
				
			case GameState.RANDOM_EVENT:
				RandomEventGenerator randomEventGenerator = new RandomEventGenerator(playerManager, state);
				randomEventGenerator.runSynchronous();
				
			case GameState.LAND_GRANT:
				if (roundNumber < 3) { // 2 LandGrant phases (roundNumber starts at 1)
					LandGranter landGranter = new LandGranter(playerManager, map, state);
					landGranter.runSynchronous();
				}
				else {
					LandPurchaser purchaser = new LandPurchaser(playerManager, map, roundNumber, state);
					purchaser.runSynchronous();
				}
				
			case GameState.TURN:
				state.setState(GameState.TURN);
				int i = state.getPlayerNum();
				while (i<numPlayers) {
					state.setPlayerNum(i);
					state.savePoint();
					Player currentPlayer = playerManager.getNextPlayer();
					Turn turn = new Turn(currentPlayer, map, state);
					state.setSaveable(true);
					turn.runSynchronous();
					state.setSaveable(false);
					i++;
				}
				state.setPlayerNum(0);
				state.savePoint();
		}
			
		state.setState(GameState.PRODUCTION);
		state.savePoint();
		// Auction
		// Score screen
	}
}
