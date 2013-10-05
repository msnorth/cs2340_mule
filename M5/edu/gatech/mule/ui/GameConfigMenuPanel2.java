package edu.gatech.mule.ui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.gatech.mule.utils.InputReceiver;

/**
 * 
 * @author Madeleine North
 * 
 * 
 * 
 * 
 * 
 *         Purpose: Second panel of game configuration menu. Allows user to
 *         select game player names, player races, and player colors. This
 *         information is passed back to Driver to start a new Game.
 */

public class GameConfigMenuPanel2 extends JPanel implements InputReceiver {

	public static String[] races = { "Race1", "Race2", "Race3 ", "Race4" };

	public GameConfigMenuPanel2(String[] labels) {

		int numPairs = labels.length;

		// Create and populate the panel
		SpringLayout layout = new SpringLayout();

		this.setLayout(layout);

		// make a label and text field for each player so that they can input
		// their names
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i] + " Name: ", JLabel.TRAILING);
			this.add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			this.add(textField);
		}

		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i] + " Race: ", JLabel.TRAILING);
			this.add(l);
			JComboBox raceList = new JComboBox(races);
			l.setLabelFor(raceList);
			this.add(raceList);
		}

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, numPairs, 2, // rows, cols
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
