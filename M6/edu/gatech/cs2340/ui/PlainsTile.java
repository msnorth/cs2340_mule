package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class PlainsTile extends Tile {
	public PlainsTile(String id, Player owner, TileRenderer renderer) {
		super("Plains");
		this.renderer = renderer;
		this.owner = owner;
		this.id = id;
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}

}
