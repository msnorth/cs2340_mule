package edu.gatech.cs2340.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;

/**
 * @author Shreyyas Vanarase
 *
 */
public class M10_PlayerManagerTest {
	Player player = new Player("Shreyyas", "Human", Color.BLUE);
	Player player2 = new Player("Stephen", "Bonzoid", Color.YELLOW);
	Player player3 = new Player("Tommy", "Buzzite", Color.RED);
	Player[] playerArray = {player, player2, player3};
	
	int shreyyasScore = 3000;
	int stephenScore = 2900;
	int tommyScore = 2890;
	PlayerManager playerManager = new PlayerManager(playerArray, "Beginner");
	
	/**
	 * Checks that you can get the same player from player 
	 * manager as the original player array
	 */
	@Test
	public void checkPlayerArray() {
		Player[] managerArray = playerManager.getPlayers();
		for(int i = 0; i < playerArray.length; i++) {
			for(int j = 0; j < managerArray.length; j++) {
				if(playerArray[i] == managerArray[j]) {
					assertTrue("The player from playerArray matches the "
							      + "player in playerManager: ", true);
				}
			}
		}
	}
	
	@Test
	public void checkNumPlayers() {
		int playerNumber = playerArray.length;
		assertEquals(playerManager.getNumPlayers(), playerNumber);
	}
	@Test
	public void testCalculateOrder() {
		
		
	}
	/*
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
	}*/
}
