package edu.gatech.cs2340.test;

import java.awt.Color;

import edu.gatech.cs2340.data.HillTile;
import edu.gatech.cs2340.data.Map;
import edu.gatech.cs2340.data.MapGenerator;
import edu.gatech.cs2340.data.MountainTile;
import edu.gatech.cs2340.data.Mule;
import edu.gatech.cs2340.data.PeakTile;
import edu.gatech.cs2340.data.PlainsTile;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.PlayerManager;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Store;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.data.ResourceAmount.ResourceType;
import edu.gatech.cs2340.data.RiverTile;
import edu.gatech.cs2340.engine.ResourceProducer;

public class ProductionTester {

	static Player[] players;
	private static Color[] colors = { Color.BLUE, Color.YELLOW, Color.GREEN,
			Color.RED, Color.BLUE };
	private static ResourceType[] resourceTypes = {
			ResourceAmount.ResourceType.FOOD,
			ResourceAmount.ResourceType.CRYSTITE,
			ResourceAmount.ResourceType.ENERGY,
			ResourceAmount.ResourceType.SMITHORE };

	// ======================
	// Branch 058f redesigned the way resources production is calculated
	// this tester is obsolete
	// ======================

//	public static void main(String[] args) {
//		int numPlayers = 5;
//		players = new Player[numPlayers];
//		Store store = Store.getStore(); // initialize store
//		// initialize players
//		for (int i = 0; i < numPlayers; i++) {
//			 Player curPlayer = new Player("name" + i, "race" + i, colors[i]);
//			 //curPlayer;
//			 players[i] = curPlayer;
//		}
//		PlayerManager playerManager = new PlayerManager(players, "Beginner");
//		Map map = MapGenerator.generateStandardMap();
//		// add some tiles
//		for (int i = 0; i < numPlayers; i++) {
//			Tile curTile = map.getNextTile();
//			curTile.setMule(new Mule(resourceTypes[i]));
//			curTile.setOwner(players[i]);
//		}
////
////		players[i].addTile(new RiverTile("River", players[i])); // should
////																// increase by 4
////		i++;
////		players[i].addTile(new PlainsTile("Plain", players[i]));
////		i++;
////		players[i].addTile(new HillTile("Hill", players[i]));
////		i++;
////		players[i].addTile(new MountainTile("Mountain", players[i]));
////		i++;
////		players[i].addTile(new PeakTile("Peak", players[i]));
//		// check resources
//		printPlayerResources();
//		// calculate production
//		System.out.println("//////////////////////");
//		ResourceProducer resourceProducer = new ResourceProducer(map);
//		resourceProducer.runSynchronous();
//		// check resources again
//		printPlayerResources();
//
//	}

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
