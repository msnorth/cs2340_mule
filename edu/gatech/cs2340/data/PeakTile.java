package edu.gatech.cs2340.data;


public class PeakTile extends Tile {
	public static String name = "Peak";
	public PeakTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}


}
