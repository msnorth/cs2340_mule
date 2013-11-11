package edu.gatech.cs2340.test;

import java.awt.Color;

import edu.gatech.cs2340.data.HillTile;
import edu.gatech.cs2340.data.MountainTile;
import edu.gatech.cs2340.data.PeakTile;
import edu.gatech.cs2340.data.PlainsTile;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.RiverTile;

public class ProductionTester {

	static Player[] players;
	private static Color[] colors = { Color.BLUE,
		Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE };
	public static void main(String[] args) {
		int numPlayers = 5;
		players = new Player[numPlayers];
		// initialize players
		for (int i = 0; i < numPlayers; i++){
			players[i] = new Player("name"+i, "race"+i, colors[i]);
		}
		PlayerManager playerManager = new PlayerManager(players, "Beginner");
		// add some tiles
		int i = 0;
		players[i].addTile(new RiverTile("River", players[i]));
		i++;
		players[i].addTile(new PlainsTile("Plain", players[i]));
		i++;
		players[i].addTile(new HillTile("Hill", players[i]));
		i++;
		players[i].addTile(new MountainTile("Mountain", players[i]));
		i++;
		players[i].addTile(new PeakTile("Peak", players[i]));
		// check resources
		printPlayerResources();
		// calculate production
		System.out.println("//////////////////////");
		playerManager.produceResources();
		// check resources again
		printPlayerResources();
		
	}
	private static void printPlayerResources(){
		for (int i = 0; i < players.length; i++){
			Player curPlayer = players[i];
			System.out.println("Player "+i+": ");
			System.out.println("Food "+i+": "+curPlayer.getResourceAmount(ResourceType.FOOD));
			System.out.println("Ore "+i+": "+curPlayer.getResourceAmount(ResourceType.SMITHORE));
			System.out.println("Energy "+i+": "+curPlayer.getResourceAmount(ResourceType.ENERGY));
			System.out.println("Crystite "+i+": "+curPlayer.getResourceAmount(ResourceType.CRYSTITE));
			System.out.println("-------------");
		}
	}
}
