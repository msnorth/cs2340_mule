package edu.gatech.cs2340.ui;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.ResourceAmount;
import edu.gatech.cs2340.data.Tile;

public class HillTile extends Tile{
	public static String name = "Hill";
	public HillTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
		image = hillImage; // get static image from superclass
	}

	@Override
	public ResourceAmount calculateProduction() {
		// TODO Auto-generated method stub
		return null;
	}


}
