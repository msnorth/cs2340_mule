package edu.gatech.cs2340.data;


public class RiverTile extends Tile{
	public static String name = "River";
	public RiverTile(String id, Player owner) {
		super(name);
		this.setOwner(owner);
		this.setId(id);
	}
	@Override
	protected int getOreIncrease() {
		return 0;
	}
	@Override
	protected int getFoodIncrease() {
		return 4;
	}
	@Override
	protected int getEnergyIncrease() {
		return 2;
	}
	@Override
	protected int getCrystiteIncrease() {
		return 0;
	}

}
