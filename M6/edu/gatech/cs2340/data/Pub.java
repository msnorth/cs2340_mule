package edu.gatech.cs2340.data;

import java.util.Random;

import edu.gatech.cs2340.engine.Round;
import edu.gatech.cs2340.ui.TownSprite;

/**
 * 
 * @author Tommy and Shreyyas
 * 		Function group:		Model: Service provider
 * 		Created for:		M7		10/20/13
 * 		Assigned to:		Thomas Mark and Shreyyas Vanarase
 * 		Modifications:		
 * 							
 * 		Purpose: To allow player to gamble in pub.
 * 				 
 */
public class Pub {
	private Player player;
	private Random random;

	// needs to be instantiated in townRenderer
	public Pub() { 
		random = new Random();
	}
	
	/**
	 * Accepts the player into the pub.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Checks that the pub has a player inside of it
	 * @return
	 */
	public boolean hasAccepted() {
		if (player != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gambles based on the amount of money the player has
	 */
	public void gamble() {
		int round      = Round.getRoundNumber();
		long time	   = player.calculateTurnTime(round);
		
		int winnings   = 0;
		int roundBonus = 200;
		int timeBonus  = 200; 
		
		int randNum    = random.nextInt(timeBonus+1);

		if(hasAccepted()) {
			if(round < 12) {
				roundBonus = 150;	
			}
			if(round < 8) {
				roundBonus = 100;
			}
			if(round < 4) {
				roundBonus = 50;
			}
			if(time < 38) {
				timeBonus = 150;
			}
			if(time < 25) {
				timeBonus = 100;
			}
			if(time < 11) {
				timeBonus = 50;
			}
			winnings = roundBonus*randNum;
			if(winnings > 250) {
				winnings = 250;
			}
			player.addMoney(winnings);
			// end player's turn
			// turn.endTurn(player);
		}
	}
}
