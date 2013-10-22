package edu.gatech.cs2340.ui;

import java.awt.Color;
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
public class TownRenderer extends GUIComponent implements WaitedOn {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ImageIcon pathImage;
	private static ImageIcon landOfficeImage;
//	private static ImageIcon landOfficeImage;
//	private static ImageIcon landOfficeImage;
//	private static ImageIcon landOfficeImage;

	public static enum Side {
		NORTH, EAST, SOUTH, WEST
	}

	private TownSprite sprite;

	/**
	 * #M6 Main constructor. Takes in a GUIManager for callback, takes in a side
	 * to determine TownSprite position
	 * 
	 * @param manager
	 * @param side
	 */
	public TownRenderer(Side side) {
		this.sprite = new TownSprite(0, 0, this);
		this.drawTown();
	}
	
	public void initialize(){
		pathImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.town/path.png"));
		landOfficeImage = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.town/path.png"));
		//path = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.town/path.png"));
		//path = new ImageIcon(Tile.class.getResource("../../../../edu.gatech.cs2340.res/edu.gatech.cs2340.res.town/path.png"));
	}
	
	private void drawTown() {
		GridLayout grid = new GridLayout();
		this.setLayout(grid);
		grid.setColumns(3);
		grid.setRows(3);
		
		this.add(this.drawLandOffice());
		this.add(this.drawPathPanel());
		this.add(this.drawLO());
		
		this.add(this.drawPathPanel());
		this.add(this.drawPathPanel());
		this.add(this.drawPathPanel());
		
		this.add(this.drawMule());
		this.add(this.drawPathPanel());
		this.add(this.drawPub());
		
		grid.layoutContainer(this);
	}

	@Override
	public void refresh() {
		this.drawTown();

	}

	private JPanel drawLandOffice() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Land Office"));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;

	}

	private JPanel drawPub() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Pub"));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;

	}

	private JPanel drawMule() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("M.U.L.E."));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;

	}

	private JPanel drawLO() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Load Out"));
		Border line = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(line);
		return panel;
	}

	private JPanel drawPathPanel() {
		JPanel panel = new JPanel();
		// panel.add(new JLabel(""));
		return panel;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
