package edu.gatech.cs2340.data;

import edu.gatech.cs2340.ui.TileRenderer;

public class TownTile extends Tile{
	public TownTile(String id, Player owner, TileRenderer renderer) {
		this.renderer = renderer;
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}



}
