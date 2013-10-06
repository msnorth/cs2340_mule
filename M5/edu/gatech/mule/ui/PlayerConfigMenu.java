package edu.gatech.mule.ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.gatech.mule.utils.*;

/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: 	M5 9/30/13 
 * 		   Modifications:	M5 10/6/2013 Shreyyas Vanarase 
 * 										 Updating panel viewability and features
 * 
 * 
 *         Purpose: First panel of game configuration menu. Allows user to
 *         select game difficulty, map type, and number of players This
 *         information is passed back to GameConfigMenuPanel2
 * 
 */
public class PlayerConfigMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	public static String[] diffLevels = { "Beginner", "Intermediate", "Advanced" };

	public static String[] maps = { "Irata 1" };

	public static Integer[] players = {2,3,4};
	
	private GUIManager manager;
	
	public PlayerConfigMenu(GUIManager manager) {
		this.manager = manager;
		
		SpringLayout layout = new SpringLayout();
		
		this.setLayout(layout);

		JLabel i = new JLabel("Number of Players: ");
		this.add(i);
		JComboBox<Integer> numPlayers = new JComboBox<Integer>(players);
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

		JButton next = new JButton("Next");
		next.addActionListener(new nextListener());
		this.add(next);
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, 3, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

	}
	
	/**
	 * 
	 */

	private class nextListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			manager.notify(PlayerConfigMenu.this,"next");
		}
		 
	}
}