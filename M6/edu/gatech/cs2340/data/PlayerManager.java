package edu.gatech.cs2340.data;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data manager
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications:		M6		10/14/2013 Thomas Mark
 * 									Added bubble sort check to order players for land grant/auction.
 * 									Filled out getNextPlayer() method.
 * 
 * 
 * 
 * 		Purpose: Holder class for all Player objects
 * 				 
 */
public class PlayerManager implements PlayerManagerResponsibilities{
	private Player[] players;
	private int nextPlayer;
	
	/**
	 * #M6
	 * Main constructor. Takes in players and then calculates their initial ordering.
	 * 
	 * @param players
	 */
	public PlayerManager(Player[] players) {
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
				if (players[i].getGameScore() > players[i+1].getGameScore()) {
					temp = players[i];
					players[i] = players[i+1];
					players[i+1] = temp;
					sorted = false;
				}
			}
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
		
		if (players[nextPlayer+1] != null) {
			nextPlayer++;
		} else {
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
	public int getTotalPlayers() {
		return players.length;
	}

}
