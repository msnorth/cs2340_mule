package edu.gatech.cs2340.data;


public class MountainTile extends Tile{
	public static String name = "Mountain";
	public MountainTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
		image = mountainImage; // get static image from superclass
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}


}
