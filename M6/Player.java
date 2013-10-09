import java.awt.Color;
import java.util.ArrayList;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data holder
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		Tommy
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Holds information for a player in the game.
 * 				 
 */
public class Player {
	private String name;
	private String race;
	private Color color;
	private ResourceAmount resources;
	private int money;
	private ArrayList<Tile> ownedTiles;
	
	/**
	 * #M6
	 * Main constructor
	 * 
	 * @param name
	 * @param race
	 * @param color
	 */
	public Player(String name, String race, Color color) {
		
	}
	
	/**
	 * #M6
	 * Method to calculate the player's current game score.
	 * Used to determine player order by PlayerManager
	 * @return
	 */
	public int calculateScore() {
		return 0;
	}
}
