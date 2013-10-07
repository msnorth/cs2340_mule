
package edu.gatech.mule.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.gatech.mule.utils.*;

/**
 * ss
 * @author Madeleine North
 * 
 *         Created for: 	M5 		9/30/13 
 * 		   Modifications:	M5 		10/6/13	Shreyyas Vanarase 
 * 									Updating panel viewability and features
 * 							M5 		10/6/13	Thomas Mark
 * 									Fixed parameter input
 * 
 * 
 *         Purpose: Second panel of game configuration menu. Allows user to
 *         			select game player names, player races, and player colors. This
 *         			information is passed back to Driver to start a new Game.
 */

public class GameConfigMenu extends JPanel{
		private static final long serialVersionUID = 1L;

		public static String[] races = { "Bonzoid", "Buzzite", "Flapper ", "Ugaite" };

		public static String[] colors = {"Blue", "Gold", "Green", "Red"};
		
		public GUIManager manager;
		
		/**
		 * Main constructor
		 * 
		 * @param manager GUIManager to handle callback from "Next" button
		 */
		public GameConfigMenu(GUIManager manager) 
		{
			this.manager = manager;
			
			int numPairs = 2;

			// Create and populate the panel
			SpringLayout layout = new SpringLayout();

			this.setLayout(layout);

			// make a label and text field for each player so that they can input
			// their names
			for (int i = 0; i < numPairs; i++) {
				JLabel nameLabel = new JLabel(" Name: ", JLabel.TRAILING);
				this.add(nameLabel);
				JTextField nameField = new JTextField(10);
				nameLabel.setLabelFor(nameField);
				this.add(nameField);
			}

			for (int i = 0; i < numPairs; i++) {
				JLabel raceLabel = new JLabel(" Race: ", JLabel.TRAILING);
				this.add(raceLabel);
				JComboBox<String> raceList = new JComboBox<String>(races);
				raceLabel.setLabelFor(raceList);
				this.add(raceList);
			}
			
			for (int i = 0; i < numPairs; i++) {
				JLabel colorLabel = new JLabel(" Color: ", JLabel.TRAILING);
				this.add(colorLabel);
				JComboBox<String> colorsList = new JComboBox<String>(races);
				colorLabel.setLabelFor(colorsList);
				this.add(colorsList);
			}
			
			JButton next = new JButton("Start");
			next.addActionListener(new startListener());
			this.add(next);
					
			// Lay out the panel.
			SpringUtilities.makeCompactGrid(this, numPairs, 2, // rows, cols
					6, 6, // initX, initY
					6, 6); // xPad, yPad
	}
		
	/**
	 * Listener class for "Start" button. Passes message to GUIManager.
	 *
	 */
	private class startListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			manager.notify(GameConfigMenu.this,"next");
		}
	}
}