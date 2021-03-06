package edu.gatech.cs2340.data;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Logger;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;

/**
 * 
 * @author Stephen Conway Function group: Model: Data holder Created for: M6
 *         10/7/13 Assigned to: Maddy Modifications:
 * 
 * 
 * 
 *         Purpose: Parent class for all Tile objects that make up a grid on the
 *         game's map.
 */
public abstract class Tile implements Serializable {
	//Static
	public static Logger logger;
	
	//Instance
	private Player owner;
	private String id;
	private boolean isActive;
	private String name;
	private int price;
	private Mule mule;
	
	protected boolean dirty;

	/**
	 * Create a tile of type name
	 * @param name
	 */
	public Tile(String name) {
		this.name = name;
		dirty = true;
		// TODO throw error if not of correct type?
	}

	/**
	 * Get the production of a Tile. Based upon Tile type,
	 * MULE present, and random events.
	 * 
	 * @return
	 */
	public ResourceAmount calculateProduction() {
		ResourceType muleType = mule.getType();
		ResourceAmount producedAmount;
		switch (muleType){
			case SMITHORE:
				producedAmount = new ResourceAmount(getOreIncrease(),0,0,0);
				break;
			case FOOD:
				producedAmount = new ResourceAmount(0, getFoodIncrease(), 0, 0);
				break;
			case ENERGY:
				producedAmount = new ResourceAmount(0, 0, getEnergyIncrease(), 0);
				break;
			case CRYSTITE:
				// Randomized crystite production
				int crystiteIncrease = getCrystiteIncrease();
				Random random = new Random();
				producedAmount = new ResourceAmount(0, 0, 0, 
						crystiteIncrease*random.nextInt(crystiteIncrease+1));
				break;
			default:
				// produce nothing if we can't identify a MULE type
				producedAmount = new ResourceAmount(0,0,0,0);
				break;
		}
		return producedAmount;
	};

	/**
	 * Get increase in ore for a particular kind of tile's production phase.
	 * @return Increase in resource
	 */
	protected abstract int getOreIncrease();
	
	/**
	 * Get increase in food for a particular kind of tile's production phase.
	 * @return Increase in resource
	 */
	protected abstract int getFoodIncrease();
	
	/**
	 * Get increase in energy for a particular kind of tile's production phase.
	 * @return Increase in resource
	 */
	protected abstract int getEnergyIncrease();
	
	/**
	 * Get increase in crystite for a particular kind of tile's production phase.
	 * @return Increase in resource
	 */
	protected abstract int getCrystiteIncrease();

	/**
	 * #M6 Method to set owner of tile. Should handle "null" as returning Tile
	 * to neutral (through sale) Should call something to update its
	 * TileRenderer to reflect the change
	 * 
	 * @param newOwner
	 */
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
		dirty = true;
	}

	/**
	 * #M6 Method to get owner of tile. Should return null if tile is unowned
	 * 
	 * @return
	 */
	public Player getOwner() {
		return this.owner;
	}
	
	/**
	 * Dirtiness indicates a need to be repainted
	 * @return
	 */
	public boolean isDirty() {
		return dirty;
	}
	
	/**
	 * When a tile is repainted, let it know it's been cleaned
	 */
	public void cleaned() {
		dirty = false;
	}
	
	/**
	 * Returns if tile has an owner
	 * @return
	 */
	public boolean isOwned() {
		return owner != null;
	}
	
	/**
	 * Method to compare two tiles
	 */
	public boolean equals(Object t) {
		boolean result = false;
		if (t instanceof Tile) {
			result = (id == ((Tile)t).id);
		}
		return result;
	}

	/**
	 * Method to determine if tile is active
	 * @return
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Method to activate tile
	 * @param isActive
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
		dirty = true;
	}

	/**
	 * Method to get name of tile
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method to get reference to mule on tile, if any
	 * @return
	 */
	public Mule getMule() {
		return mule;
	}
	
	/**
	 * Method to place a mule on the tile
	 * @param mule
	 */
	public void setMule(Mule mule) {
		if (this.mule != null) {
			throw new RuntimeException("Cannot place mule where there already is one.");
		}
		this.mule = mule;
		dirty = true;
	}
	
	/**
	 * Method to determine if a mule has been deplyed on the tile
	 * @return
	 */
	public boolean hasMule() {
		return (mule != null);
	}


	/**
	 * Set the price of the tile and draw a string with that price
	 * @param price Price of the tile
	 * @return Whether the setting was successful (because the tile is unowned)
	 */
	public void setPrice(int price) {
		this.price = price; 
		dirty = true;
	}
	
	/**
	 * Get the price of the tile
	 * @return Price of the tile
	 */
	public int getPrice(){
		return price;
	}

}
