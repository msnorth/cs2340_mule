package edu.gatech.cs2340.data;

import edu.gatech.cs2340.ui.TileRenderer;

public class PeakTile extends Tile {
	public PeakTile(String id, Player owner, TileRenderer renderer) {
		name = "Peak";
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
