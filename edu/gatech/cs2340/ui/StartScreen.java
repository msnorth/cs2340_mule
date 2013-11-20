package edu.gatech.cs2340.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.gatech.cs2340.io.StartScreenImageLoader;
/**
 * 
 * @author Thomas Mark
 * 
 *         Created for: 	N/A		11/17/2013
 * 		   Modifications:
 * 
 *         Purpose: Start screen for MULE game.
 * 
 */
public class StartScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean finished;
	/**
	 * Main constructor for Start Screen
	 */
	public StartScreen() {
		StartScreenImageLoader loader = new StartScreenImageLoader();
		ImageIcon image = loader.getImage();
		add(new JLabel(image));
		revalidate();
	}
}
