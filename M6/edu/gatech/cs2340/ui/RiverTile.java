package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class RiverTile extends Tile{
	public static String name = "River";
	public RiverTile(String id, Player owner, TileRenderer renderer) {
		super(name);
		this.setRenderer(renderer);
		this.setOwner(owner);
		this.setId(id);
		image = riverImage; // get static image from superclass
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}

}
