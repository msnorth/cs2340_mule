package edu.gatech.cs2340.data;


public class PlainsTile extends Tile {
	public static String name = "Plains";
	public PlainsTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
		image = plainImage; // get static image from superclass
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}
}
