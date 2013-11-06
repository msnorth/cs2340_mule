package edu.gatech.cs2340.ui;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
	private static ImageIcon singleImage;

	/**
	 * Creates a new panel with the town image on it.
	 */
	public TownRenderer() {
		if (singleImage == null) {
			singleImage = new ImageIcon(TownRenderer.class.getResource("TownRender.png"));
		}
		add(new JLabel(singleImage));
		revalidate();
	}
}
