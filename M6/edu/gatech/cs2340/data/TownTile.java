package edu.gatech.cs2340.data;

import java.awt.Color;

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
		return new ResourceAmount();
	}

}
