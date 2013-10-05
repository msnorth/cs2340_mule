package edu.gatech.mule.ui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.gatech.mule.utils.InputReceiver;

/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: M5 9/30/13 Last modified: M5 9/30/13
 * 
 * 
 * 
 *         Purpose: First panel of game configuration menu. Allows user to
 *         select game difficulty, map type, and number of players This
 *         information is passed back to GameConfigMenuPanel2
 * 
 */
public class GameConfigMenuPanel1 extends JPanel implements InputReceiver {

	public static String[] diffLevels = { "Beginner" };

	public static String[] maps = { "Map1" };

	public static String[] players = { "1", "2", "3", "4" };

	public GameConfigMenuPanel1() {

		SpringLayout layout = new SpringLayout();

		this.setLayout(layout);

		JLabel i = new JLabel("Number of Players: ");
		this.add(i);
		JComboBox numPlayers = new JComboBox(players);
		i.setLabelFor(numPlayers);
		this.add(numPlayers);

		JLabel j = new JLabel("Game Level: ");
		this.add(j);
		JComboBox gameLevel = new JComboBox(diffLevels);
		j.setLabelFor(gameLevel);
		this.add(gameLevel);

		JLabel k = new JLabel("Map Type: ");
		this.add(k);
		JComboBox mapTypes = new JComboBox(maps);
		k.setLabelFor(mapTypes);
		this.add(mapTypes);

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, 3, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089318394207526152L;

	@Override
	public void gainFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loseFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveInput(String input) {
		// TODO Auto-generated method stub

	}

}
