package edu.gatech.cs2340.data;


public class RiverTile extends Tile{
	public static String name = "River";
	public RiverTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
		image = riverImage; // get static image from superclass
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}

}
