package edu.gatech.cs2340.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.sequencing.WaitedOn;

/**
 * 
 * @author Stephen Conway Function group: View: Background Created for: M6
 *         10/8/13 Assigned to: Maddy Modifications:
 * 
 * 
 * 
 *         Purpose: Graphic of the inside of the town.
 */
public class TownRendererUpdated extends GUIComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ImageIcon singleImage;
	private static boolean initialized = false;

	public static enum Side {
		NORTH, EAST, SOUTH, WEST
	}

	private TownSprite sprite;
	
	public static void initialize() {
		if (!initialized) {
			singleImage = new ImageIcon(TownRendererUpdated.class.getResource("TownRender.png"));
			initialized = true;
		}
	}

	/**
	 * #M6 Main constructor. Takes in a GUIManager for callback, takes in a side
	 * to determine TownSprite position
	 * 
	 * @param manager
	 * @param side
	 */
	public TownRendererUpdated(TownSprite sprite) {
		this.sprite = sprite;
		this.drawTown();
	}
	
	public TownRendererUpdated() {
		this.drawTown();
	}
	
	private void drawTown() {
		removeAll();
		GridLayout grid = new GridLayout(3,3,0,0);
		this.setLayout(grid);
		
		/*this.add(this.drawLandOffice());
		this.add(this.drawPathPanel());
		this.add(this.drawLoadOut());
		
		this.add(this.drawPathPanel());
		this.add(this.drawPathPanel());
		this.add(this.drawPathPanel());
		
		this.add(this.drawMule());
		this.add(this.drawPathPanel());
		this.add(this.drawPub());*/
		
		revalidate();
		this.add(this.drawSingleImage());

		grid.layoutContainer(this);
	}

	@Override
	public void refresh() {
		drawTown();
		sprite.update();
		repaint();
	}
	
	public void refreshSprite() {
		if (sprite == null) {
			throw new RuntimeException("Cannot refresh sprite on spriteless town renderer!");
		}
		sprite.update();
		repaint();
	}

	/*private JPanel drawLandOffice() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(landOfficeImage));
		//panel.add(new JLabel("Land Office"));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;

	}

	private JPanel drawPub() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(pubImage));
		//panel.add(new JLabel("Pub"));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;

	}

	private JPanel drawMule() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(muleImage));
		//panel.add(new JLabel("M.U.L.E."));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;

	}

	private JPanel drawLoadOut() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(loadOutImage));
		//panel.add(new JLabel("Load Out"));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;
	}

	private JPanel drawPathPanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(pathImage));
		// panel.add(new JLabel(""));
		return panel;
	}*/
	
	private JPanel drawSingleImage() {
		JPanel panel = new JPanel();
		panel.add(new JLabel(singleImage));
		// panel.add(new JLabel(""));
		return panel;
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        //drawTown();
        if (sprite != null) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(sprite.getImage(), sprite.getScreenX(), sprite.getScreenY(), this);
        }
        g.dispose();
	}

}
