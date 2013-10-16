package edu.gatech.cs2340.data;

import edu.gatech.cs2340.ui.TileRenderer;

public class MountainTile extends Tile{
	public MountainTile(String id, Player owner, TileRenderer renderer) {
		name = "Mountain";
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
