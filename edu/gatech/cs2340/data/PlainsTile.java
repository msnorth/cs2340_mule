package edu.gatech.cs2340.data;



public class PlainsTile extends Tile {
	public static String name = "Plain";
	public PlainsTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
	}
	@Override
	protected int getOreIncrease() {
		return 1;
	}
	@Override
	protected int getFoodIncrease() {
		return 2;
	}
	@Override
	protected int getEnergyIncrease() {
		return 3;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 1;
	}

}
