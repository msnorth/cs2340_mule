package edu.gatech.cs2340.data;

import edu.gatech.cs2340.data.ResourceAmount.ResourceType;

/**
 *@author Thomas Mark
 * 		Function group:		Model: Data holder
 * 		Created for:		M8		10/27/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications: 		M7		10/29/13 Thomas Mark
 * 									Changed type reference to ResourceType (originally String)							
 * 			
 * 		Purpose: To hold data about a specific mule type.				
 */
public class Mule {
	private final ResourceType type;
	
	/**
	 * #M8
	 * Mule constructor
	 * @param muleType
	 */
	public Mule(ResourceType muleType) {
		this.type = muleType;
	}
	
	/**
	 * #M8
	 * Getter method for mule type.
	 * @return type
	 */
	public ResourceType getType() {
		return type;
	}
	
	public String toString() {
		return (type.name() + "");
		
	}
}
