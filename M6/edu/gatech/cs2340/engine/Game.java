
package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.ui.MainMenuManager;
import edu.gatech.cs2340.ui.MapRenderer;

/**
 * 
 * @author Thomas Mark
 * 		Function group:		Control: Engine
 * 		Created for:		M6		10/14/2013
 * 
 * 		Purpose: Controls game processes. Initializes rounds. Determines end of game.
 *
 */
public class Game implements WaitedOn {
	private static PlayerManager playerManager;
	private static boolean randomMap;
	private static int numberRounds;
	private static Map map;
	private static MapRenderer renderer;
	private LandGranter granter;
	
	public Game(PlayerManager pManager, boolean randMap, int numRounds) {
		playerManager = pManager;
		randomMap = randMap;
		numberRounds = numRounds;
		
		if (!randomMap) {
			map = MapGenerator.generateStandardMap();
		} else {
			map = MapGenerator.generateRandomMap();
		}
		
		renderer = new MapRenderer(map);
	}
	
	public void run() {
		int numPlayers = playerManager.getTotalPlayers();
		
		for (int i=0; i < numberRounds; i++) {
			if (i == 0) {
				granter = new LandGranter(playerManager.getNextPlayer(), map, renderer);
			}
		}
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
