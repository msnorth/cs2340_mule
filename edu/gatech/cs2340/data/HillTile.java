package edu.gatech.cs2340.data;


public class HillTile extends Tile{
	public static String name = "Hill";
	public HillTile(String id, Player owner) {
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
