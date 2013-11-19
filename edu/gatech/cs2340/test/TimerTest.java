/**
 * 
 */
package edu.gatech.cs2340.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.sequencing.Waiter;

/**
 * @author Madeleine North
 * 		   M10
 * 
 *         Testing the timer class
 * 
 */
public class TimerTest {

	protected GameData data;
	private Player[] players = { new Player("Player1", "Bonzoid", Color.RED),
			new Player("Player2", "Human", Color.BLUE) };

	@Before
	public void setUp() throws Exception {

		String difficulty = "Beginner";
		PlayerManager pManager = new PlayerManager(players, difficulty);
		data = new GameData(pManager, null, null, 8);
	}

	/**
	 * Does timer end when waited on?
	 */
	@Test
	public void test0() {
		GameClock.startClock();
		MULETimer timer = new MULETimer(100);
		WaitedOn[] waitees = { timer };

		timer.start();
		int killa = Waiter.waitForAny(waitees);
		assertEquals(killa, 0);

	}
	
	/**
	 * For each round, does the timer's time remaining never go negative?
	 * 
	 */

	@Test
	public void test1() {
		GameClock.startClock();

		for (int i = 0; i < data.getNumRounds(); i++) {
			MULETimer timer = new MULETimer(players[0].calculateTurnTime(i));
			timer.start();
			while (!timer.isFinished()) {
				System.out.println(timer.getTimeRemaining());
				assertTrue(timer.getTimeRemaining() > 0);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
