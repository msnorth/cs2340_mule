package edu.gatech.cs2340.data;

import java.io.Serializable;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data manager
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications:		M6		10/14/2013 Thomas Mark
 * 									Added bubble sort check to order players for land grant/auction.
 * 									Filled out getNextPlayer() method.
 * 							M9		11/3/13 Thomas Mark
 * 									Added random events.
 * 
 * 
 * 
 * 		Purpose: Holder class for all Player objects
 * 				 
 */
public class PlayerManager implements PlayerManagerResponsibilities, Serializable {
	private static final long serialVersionUID = 1L;
	private final Player[] players;
	private int nextPlayer;
	
	/**
	 * #M6
	 * Main constructor. Takes in players and then calculates their initial ordering.
	 * 
	 * @param players
	 */
	public PlayerManager(Player[] players, String difficulty) {
		this.players = players;
		calculatePlayerOrder();
		nextPlayer = 0;
	}
	
	/**
	 * #M6
	 * Calculates player order using bubble sort method.
	 * Rearranges players array in ascending order of score.
	 */
	@Override
	public void calculatePlayerOrder() {
		boolean sorted = false;
		Player temp;
		
		while (!sorted) {
			sorted = true;
			for (int i=0; i < players.length-1; i++) {
					players[i].setlowestScore(false);
				if (players[i].calculateScore() > players[i+1].calculateScore()) {
					temp = players[i];
					players[i] = players[i+1];
					players[i+1] = temp;
					sorted = false;
				}
			}
			players[0].setlowestScore(true);
		}
		
	}
	
	/**
	 * #M6
	 * Returns next player in order of score.
	 * 
	 * @return next
	 */
	@Override
	public Player getNextPlayer() {
		Player next = players[nextPlayer];
		nextPlayer++;
		if (nextPlayer >= players.length) {
			nextPlayer = 0;
		}

		return next;
	}

	
	/**
	 * #M6
	 * Returns number of player in game.
	 * 
	 * @return the number of players
	 */
	public int getNumPlayers() {
		return players.length;
	}
	
	/**
	 * Get specific player out of the manager
	 * @param ndx
	 * @return
	 */
	public Player getPlayerNumber(int ndx) {
		return players[ndx];
	}
	
	/**
	 * Method that should not exist.
	 * PlayerManager is an encapsulation that controls access to the Players. Why circumvent it?
	 * @return
	 */
	public Player[] getPlayers() {
		return players;
	}
}
