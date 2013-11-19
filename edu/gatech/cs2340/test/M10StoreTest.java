
package edu.gatech.cs2340.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.Store;

/**
 * @author Thomas Mark
 *
 */
public class M10StoreTest {
	// Player starts with 1000 money
	Player player = new Player("TestPlayer", "buzzite", Color.BLUE);
	// Store starts with 150 of each resource
	Store store = new Store();

	/**
	 * Sets up the classes and information required to test the selling of resources to a player.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		store.setPlayer(player);
	}

	@Test
	public void notEnoughMoney() {
		assertFalse("The player could buy all 150 units of food.", 
				   store.sellResources(ResourceType.FOOD, 150));
	}
	
	@Test
	public void notEnoughResources() {
		player.addMoney(1000000);
		assertFalse("The store could sell 200 units of Food",
				    store.sellResources(ResourceType.FOOD, 200));
	}
	
	@Test
	public void enoughMoneyAndResources() {
		assertTrue("The could not buy 10 units of food with 1000 money", 
				   store.sellResources(ResourceType.FOOD, 10));
	}
}
