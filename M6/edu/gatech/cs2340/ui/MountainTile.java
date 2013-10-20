package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class MountainTile extends Tile{
	public MountainTile(String id, Player owner, TileRenderer renderer) {
		super("Mountain");
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
