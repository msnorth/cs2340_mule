package edu.gatech.cs2340.ui;
import javax.swing.JPanel;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		View
 * 		Created for:		M6		10/7/13
 * 		Assigned to:		
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Common parent class for any GUI components			 
 */
public abstract class GUIComponent extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * #M6
	 * Method to update display based on most recent information in 
	 * 	corresponding Model object. (TileRenderer using info from Tile)
	 */
	public abstract void refresh();
}
