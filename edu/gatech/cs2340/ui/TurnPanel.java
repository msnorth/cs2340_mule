package edu.gatech.cs2340.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * 
 * @author Stephen Conway 
 *
 *
 * UI class that handles switching between views of Turn phase
 * Needed in order to render the Sprite image
 */
public class TurnPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public static final int DIM_X = 72*9 + 40;
	public static final int DIM_Y = 550;
	Dimension panelSize = new Dimension(DIM_X, DIM_Y);

	private JPanel[] panels;
	private boolean[] spriteEnabled;
	private int currentPanel;
	private Sprite sprite;

	/**
	 * Creates a mutex using the input panels and a parallel array indicating which panels
	 * should also display the sprite.
	 * 
	 * @param panels
	 * @param sprite
	 */
	public TurnPanel(JPanel[] panels, boolean[] spriteEnabled, Sprite sprite) {
		this.panels = panels;
		this.spriteEnabled = spriteEnabled;
		this.sprite = sprite;
		//setLayout(new BorderLayout());
	//	add(panels[0]);
		currentPanel = 0;
	}
	
	/**
	 * Changes the panel displayed if needed.
	 * 
	 * @param ndx
	 */
	public void setCurrentPanel(int ndx) {
		if (ndx != currentPanel) {
			removeAll();
			setMinimumSize(panelSize);
			setMaximumSize(panelSize);
			setPreferredSize(panelSize);
			setBackground(MainGameWindow.BACKGROUND_COLOR);
			add(panels[ndx]);
			revalidate();
			currentPanel = ndx;
		}
	}
	
	/**
	 * Paints the Sprite image over the background if needed
	 */
	public void paint(Graphics g) {
		super.paint(g);
		if (spriteEnabled[currentPanel]) {
			Graphics2D g2d = (Graphics2D)g;
			Point loc = sprite.getScreenCoords();
	        g2d.drawImage(sprite.getImage(), loc.x, loc.y, this);
		}
        g.dispose();
	}
}
