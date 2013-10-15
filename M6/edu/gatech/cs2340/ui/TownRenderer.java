package edu.gatech.cs2340.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

	private void drawTown() {
		GridLayout grid = new GridLayout();
		grid.setColumns(3);
		grid.setRows(3);
		this.setLayout(grid);
		this.add(this.drawLandOffice());
		this.add(this.drawPathPanel());
		this.add(this.drawLO());
		this.add(this.drawMule());
		this.add(this.drawPathPanel());
		this.add(this.drawPub());
		grid.layoutContainer(this);
	}

	@Override
	public void refresh() {
		this.drawTown();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	private JPanel drawLandOffice() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Land Office"));
		return panel;

	}

	private JPanel drawPub() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Pub"));
		return panel;

	}

	private JPanel drawMule() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("M.U.L.E."));
		return panel;

	}

	private JPanel drawLO() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Menu"));
		return panel;
	}

	private JPanel drawPathPanel() {
		JPanel panel = new JPanel();
		return panel;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
