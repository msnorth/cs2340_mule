package edu.gatech.cs2340.test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.gatech.cs2340.data.Mule;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.ResourceAmount;

/**
 * @author Shreyyas Vanarase
 *
 */
public class M10_PlayerManagerTest {
	Player player = new Player("Shreyyas", "Human", Color.BLUE);
	Player player2 = new Player("Stephen", "Bonzoid", Color.YELLOW);
	Player player3 = new Player("Tommy", "Buzzite", Color.RED);
	Player[] playerArray = {player, player2, player3};
	
	PlayerManager playerManager = new PlayerManager(playerArray, "Beginner");

	Player[] managerArray = playerManager.getPlayers();

	/**
	 * Checks that you can get the same player from player 
	 * manager as the original player array
	 */
	@Test
	public void checkPlayerArray() {
		for(int i = 0; i < playerArray.length; i++) {
			for(int j = 0; j < managerArray.length; j++) {
				if(playerArray[i] == managerArray[j]) {
					assertTrue("The player from playerArray matches the "
							      + "player in playerManager: ", true);
				}
			}
		}
	}
	
	/**
	 * Tests the calculate order method so we can get the correct player
	 * based on their game score
	 */
	@Test
	public void testCalculateOrder() {
		//Adds in resources, money, mule for Shreyyas
		player.addResources(ResourceAmount.ResourceType.ENERGY, 40);
		player.addMoney(300);
		player.addMule(new Mule(ResourceAmount.ResourceType.ENERGY));
		int playerScore = player.calculateScore(); 
		
		//Adds in resources, money, mule for Stephen
		player2.addResources(ResourceAmount.ResourceType.FOOD, 10);
		player2.addMoney(200);
		player2.addMule(new Mule(ResourceAmount.ResourceType.FOOD));
		int player2Score = player2.calculateScore();
				
		//Adds in resources, money, mule for Tommy
		player3.addResources(ResourceAmount.ResourceType.SMITHORE, 5);
		player3.addMoney(100);
		player3.addMule(new Mule(ResourceAmount.ResourceType.SMITHORE));
		int player3Score = player3.calculateScore();
		
		//Here are players' scores
		System.out.println("Player 1 Score: " +playerScore 
						  +"\nPlayer 2 Score: " +player2Score
						  +"\nPlayer 3 Score: " +player3Score);
		
		//OrderArray is ordered manually based on these scores
		Player[] orderArray = {player2, player3, player};
		
		//Has a random assortment of players
		Player[] playerArray2 = {player3, player, player2};
		
		//Prints out the order of this random assortment of players 
		System.out.println("\nPlayer Test Array");
		System.out.println("-----------------------------------------------");
		for(int i = 0; i < playerArray2.length; i++) {
			System.out.println(playerArray2[i]);
		}
		System.out.println();
		
		//Makes a new player manager, calculates the player order based on random array,
		//and assigns it to an array
		PlayerManager newPlayerManager = new PlayerManager(playerArray2, "Beginner");
		newPlayerManager.calculatePlayerOrder();
		Player[] newManagerArray = newPlayerManager.getPlayers();
		
		System.out.println("Player Manager's Array");
		System.out.println("-----------------------------------------------");
		for(int i = 0; i < orderArray.length; i++) {
			for(int j = 0; j < newManagerArray.length; j++) {
				if(orderArray[i] == newManagerArray[j]) {
					System.out.println(newManagerArray[j]);
					assertTrue("The ordering player from orderArray matches the "
						      + "ordering player in playerManager: ", true);
				}
			}
		}
	}
	
	/**
	 * Checks the number of players are proper
	 */
	@Test
	public void checkNumPlayers() {
		int playerNumber = playerArray.length;
		assertEquals(playerManager.getNumPlayers(), playerNumber);
	}
}

