
/**
 * 
 * @author Stephen Conway
 * 		Function group:		Model: Data manager
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy
 * 		Modifications:								
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
	
	@Override
	public void calculatePlayerOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getNextPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
