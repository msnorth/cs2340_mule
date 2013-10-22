package edu.gatech.cs2340.ui;

import java.awt.Color;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class TownTile extends Tile {
	public static String name = "Town";
	public TownTile(String id, Player owner) {
		super(name);
		Player player = new Player("Municipatily","Human",new Color(255,255,255));
		this.setOwner(player);
		this.setId(id);
		image = townImage; // get static image from superclass
	}
	@Override
	public ResourceAmount calculateProduction() {
		// This doesn't apply
		return null;
	}

}
