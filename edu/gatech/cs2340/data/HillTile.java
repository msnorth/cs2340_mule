package edu.gatech.cs2340.data;



public class HillTile extends Tile{
	public static String name = "Hill";
	public HillTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
	}
	@Override
	protected int getOreIncrease() {
		return 2;
	}
	@Override
	protected int getFoodIncrease() {
		return 1;
	}
	@Override
	protected int getEnergyIncrease() {
		return 1;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 1;
	}

}
