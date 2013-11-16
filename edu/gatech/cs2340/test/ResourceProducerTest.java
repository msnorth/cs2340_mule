/**
 * 
 */
package edu.gatech.cs2340.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.engine.ResourceProducer;

/**
 * @author Dan
 *
 */
public class ResourceProducerTest {

	static Player[] players;
	private static Color[] colors = { 
		Color.BLUE, 
		Color.YELLOW, 
		Color.GREEN, 
		Color.RED};
	private static ResourceType[] resourceTypes = {
			ResourceAmount.ResourceType.FOOD,
			ResourceAmount.ResourceType.CRYSTITE,
			ResourceAmount.ResourceType.ENERGY,
			ResourceAmount.ResourceType.SMITHORE };	
	ResourceProducer resProd;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void runSynchronousTest() {
		fail("Not yet implemented");
		assertEquals("",0,0);
	}
	
	private static void printPlayerResources() {
		for (int i = 0; i < players.length; i++) {
			Player curPlayer = players[i];
			System.out.println("Player " + i + ": ");
			System.out.println("Food " + i + ": "
					+ curPlayer.getResourceAmount(ResourceType.FOOD));
			System.out.println("Ore " + i + ": "
					+ curPlayer.getResourceAmount(ResourceType.SMITHORE));
			System.out.println("Energy " + i + ": "
					+ curPlayer.getResourceAmount(ResourceType.ENERGY));
			System.out.println("Crystite " + i + ": "
					+ curPlayer.getResourceAmount(ResourceType.CRYSTITE));
			System.out.println("-------------");
		}
	}

}
