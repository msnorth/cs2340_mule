package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class TownTile extends Tile{
	public TownTile(String id, Player owner, TileRenderer renderer) {
		super("Town");
		this.id = id;
		this.owner = owner;
		this.renderer = renderer;
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}



}
