import edu.gatech.mule.utils.WaitedOn;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy, Stephen
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Execute a single round of the game
 */
public class Round implements WaitedOn{
	private int roundNumber;
	private boolean finished;
	
	/**
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
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}	
}
