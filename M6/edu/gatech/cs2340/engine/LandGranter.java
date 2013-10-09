package edu.gatech.cs2340.engine;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.io.InputReceiver;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.ui.MapRenderer;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Game Process
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Shreyyas
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Grant unowned plots of land to players in the first few rounds of the game.
 */
public class LandGranter implements InputReceiver, WaitedOn{
	private Player currentPlayer;
	private MapRenderer mapRenderer;
	private boolean grantFinished;
	
	/**
	 * #M6
	 * Main constructor. Takes in references to current grantee and to the MapRenderer to refresh the screen.
	 * 
	 * @param player
	 * @param mapRenderer
	 */
	public LandGranter(Player player, MapRenderer mapRenderer) {
		currentPlayer = player;
		this.mapRenderer = mapRenderer;
		grantFinished = false;
	}
	
	/**
	 * #M6
	 * Cycles through unowned map tiles, waiting for the user to press the select key.
	 * Once the key is pressed, the Tile is assigned to the Player, and the grant is finished.
	 * If the cycle reaches the end of the map before the user selects a Tile, a random Tile
	 * should be assigned to the Player.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void receiveInput(String input) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isFinished() {
		return grantFinished;
	}


}
