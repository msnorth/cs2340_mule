package edu.gatech.cs2340.ui;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.gatech.cs2340.data.TownImageLoader;


/**
 * 
 * @author Stephen Conway Function group: View: Background Created for: M6
 *         10/8/13 Assigned to: Maddy Modifications:
 * 
 * 
 * 
 *         Purpose: Graphic of the inside of the town.
 */
public class TownRenderer extends JPanel{
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new panel with the town image on it.
	 */
	public TownRenderer() {
		TownImageLoader loader = new TownImageLoader();
		ImageIcon image = loader.getImage();
		add(new JLabel(image));
		revalidate();
	}
}
