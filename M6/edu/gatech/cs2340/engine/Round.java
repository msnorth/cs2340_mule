package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.ui.MapRenderer;



/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Engine
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy, Stephen
 * 		Modifications:		M6		10/15/13 Thomas Mark
 * 									Fleshed out round methods.						
 * 
 * 
 * 
 * 		Purpose: Execute a single round of the game
 */
public class Round implements WaitedOn{
	private static int roundNumber;
	private boolean finished;
	private static PlayerManager playerManager;
	private static Map map;
	private static MapRenderer mapRenderer;
	
	public Round(PlayerManager pManager, Map usedMap, MapRenderer mRenderer, int roundNum) {
		this.finished = false;
		roundNumber = roundNum;
		playerManager = pManager;
		map = usedMap;
		mapRenderer = mRenderer;
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
		LandGranter granter;
		int numPlayers = playerManager.getTotalPlayers();
		// TODO: random events
		
		// Land Grant/Auction phases
		for (int i=0; i < roundNumber; i++) {
			if (i == 0 || i == 1) {
				for (int j=0; j < numPlayers; j++) {
					granter = new LandGranter(playerManager.getNextPlayer(), map, mapRenderer);
					granter.run();
					Waiter.waitOn(granter);
				}
			}
		}
		
		// Production
		// Auction
		// Score screen
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}	
}
