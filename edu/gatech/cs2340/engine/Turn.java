package edu.gatech.cs2340.engine;

import edu.gatech.cs2340.data.Gambler;
import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.SavePointTimer;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;
import edu.gatech.cs2340.test.DebugPrinter;
import edu.gatech.cs2340.ui.MainGameWindow;
import edu.gatech.cs2340.ui.StatusBar;
import edu.gatech.cs2340.ui.MapManager;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Engine
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Tommy, Stephen
 * 		Modifications:		M6 		10/16/13 Stephen Conway, Shreyyas Vanarase
 * 									Added intitial functionality to run method	
 * 							M7      10/19/13 Shreyyas Vanarase
 * 									Added local roundNumber and changed muleTimer parameter to get the turntime based off a roundNumber			
 * 							M7		10/21/13 Stephen Conway
 * 									Removed WaitedOn interface. Runs synchronously.
 * 
 * 
 * 		Purpose: Execute a single player's turn
 */
public class Turn {
	private GameData data;
	
	/**
	 * #M6
	 * Main constructor. Takes in player to use for the turn.
	 * 
	 * @param player
	 */
	public Turn(GameData data) {
		this.data = data;
	}
	
	/**
	 * #M6
	 * Method to execute a turn
	 * 		free range phase
	 * 		handle feedback of MULE purchase, MULE loading, MULE deploying, Pubbing
	 */
	public void runSynchronous() {
		MainGameWindow.setMessage(String.format("%s it's your turn!", data.getCurrentPlayer().getName()));
		
		DebugPrinter.println("Running Turn synchronously.");
		Player player = data.getCurrentPlayer();
		
		int roundNumber = data.getRoundNum();
		SavePointTimer timer = new SavePointTimer(player.calculateTurnTime(roundNumber), data);
		MULETimer timer2 = new MULETimer(player.calculateTurnTime(roundNumber));
		System.out.println(player.getName() + " " + player.calculateTurnTime(roundNumber));
		StatusBar statBar = MainGameWindow.getLowerPanel();
		statBar.setTimer(timer2);
		MapManager mapManager = new MapManager(data);
		timer.start();
		statBar.startTurn(player);
		mapManager.runAsynchronous();
		WaitedOn[] waitees = {timer, mapManager};
		int killa = Waiter.waitForAny(waitees);
		if (killa == 1) { //turn ended by gambling
			timer.stop();
			Gambler gambler = new Gambler(roundNumber);
			gambler.setPlayer(player);
			gambler.gamble(timer.getTimeRemaining());
		}	
		else {
			MainGameWindow.setMessage(String.format("%s ran out of time on their turn!", player.getName()));
		}
	}
}
