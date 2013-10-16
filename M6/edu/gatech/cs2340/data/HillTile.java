package edu.gatech.cs2340.data;

import edu.gatech.cs2340.ui.TileRenderer;

public class HillTile extends Tile{
	public HillTile(String id, Player owner, TileRenderer renderer) {
		name = "Hill";
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
