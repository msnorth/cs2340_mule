package edu.gatech.cs2340.data;
/**
 *@author Thomas Mark
 * 		Function group:		Model: Data holder
 * 		Created for:		M8		10/27/13
 * 		Assigned to:		Thomas Mark
 * 		Modifications: 									
 * 			
 * 		Purpose: To hold data about a specific mule type.				
 */
public class Mule {
	private String type;
	
	/**
	 * #M8
	 * Mule constructor
	 * @param muleType
	 */
	public Mule(String muleType) {
		this.type = muleType;
	}
	
	/**
	 * #M8
	 * Getter method for mule type.
	 * @return type
	 */
	public String getType() {
		return type;
	}
}
