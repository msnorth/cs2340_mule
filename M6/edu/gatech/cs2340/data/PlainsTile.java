package edu.gatech.cs2340.data;

import edu.gatech.cs2340.ui.TileRenderer;

public class PlainsTile extends Tile {
	public PlainsTile(String id, Player owner, TileRenderer renderer) {
		name = "Plains";
		this.setRenderer(renderer);
		this.setOwner(owner);
		this.setId(id);
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}

}
