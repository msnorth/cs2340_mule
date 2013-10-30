package edu.gatech.cs2340.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.sequencing.WaitedOn;

/**
 * 
 * @author Madeleine North
 * 
 * 		Function group:		Control: UI
 * 		Created for:		M5		09/30/13
 * 		Modifications:		M5      10/6/13 Shreyyas Vanarase
 * 									Updating panel viewability and features 
 * 							M5      10/6/13 Thomas Mark 
 * 									Fixed parameter input
 * 							M8      10/26/13 Shreyyas Vanarase
 * 									Added Human race. 

 *         Purpose: Second panel of game configuration menu. Allows user to
 *         select game player names, player races, and player colors. This
 *         information is passed back to Driver to start a new Game.
 */

public class PlayerConfigMenu extends JPanel implements WaitedOn {
	private static final long serialVersionUID = 1L;

	public static String[] races = { "Bonzoid", "Buzzite", "Flapper", "Ugaite", "Human" };

	public static String[] colorStrings = { "Blue", "Gold", "Green", "Red" };
	private Map<Integer, String> takenColors = new HashMap<Integer, String>();
	public static Color[] colors = { Color.BLUE,
			Color.YELLOW, Color.GREEN, Color.RED };

	private boolean finished;
	private Player[] players;
	private GameConfigMenu menu;
	
	private JTextField[] nameFields;
	private JComboBox<String>[] raceLists;
	private JComboBox<String>[] colorsLists;

	/**
	 * Main constructor
	 * @param manager
	 *    
	 */
	public PlayerConfigMenu(int numPairs) {
		menu 	 = new GameConfigMenu();
		finished = false;

		// Create and populate the panel
		SpringLayout layout = new SpringLayout();

		this.setLayout(layout);

		nameFields = new JTextField[numPairs];
		raceLists = new JComboBox[numPairs];
		colorsLists = new JComboBox[numPairs];

		// make a label and text field for each player so that they can input
		// their names
		for (int i = 0; i < numPairs; i++) {
			JLabel nameLabel = new JLabel("Player " + (i + 1) + " Name: ",
					JLabel.TRAILING);
			this.add(nameLabel);
			JTextField nameField = new JTextField(10);
			nameLabel.setLabelFor(nameField);
			this.add(nameField);
			nameFields[i] = nameField;

			JLabel raceLabel = new JLabel(" Race: ", JLabel.TRAILING);
			this.add(raceLabel);
			JComboBox<String> raceList = new JComboBox<String>(races);
			raceLabel.setLabelFor(raceList);
			this.add(raceList);
			raceLists[i] = raceList;

			JLabel colorLabel = new JLabel(" Color: ", JLabel.TRAILING);
			this.add(colorLabel);

			JComboBox<String> colorsList = new JComboBox<String>(colorStrings);
			colorsLists[i] = colorsList;
			takenColors.put(i, colorStrings[i]);
			colorsList.setSelectedIndex(i);
			colorsList.addActionListener(new cascadeListener());
			colorLabel.setLabelFor(colorsList);
			this.add(colorsList);
			
		}
		
		removeTakenColors();

		this.add(new JPanel());
		this.add(new JPanel());
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new startListener());
		this.add(startButton);
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());

		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, numPairs + 1, 6, // rows, cols
				5, 5, // initialX, initialY
				5, 5);// xPad, yPad
	}

	public Player[] getPlayers() {
		return players;
	}

	/*
	 * Listener class for Player Color ComboBoxes to ensure that no two players
	 * have the same color
	 */
	private class cascadeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int numPairs = nameFields.length;
			Object sender = e.getSource();
			String previousColor = null;
			String newColor = null;
			// find which ComboBox was selected
			for (int i = 0; i < numPairs; i++) {
				if (colorsLists[i].equals(sender)) {
					previousColor = takenColors.get(i);
					newColor = colorsLists[i].getSelectedItem().toString();
					takenColors.put(i, newColor);
					break;
				}
			}

			ActionListener al;
			// remove selected color from remaining ComboBoxes
			for (int i = 0; i < numPairs; i++) {
				JComboBox<String> cb = colorsLists[i];
				if(cb != sender && cb != null){
					al = cb.getActionListeners()[cb.getActionListeners().length - 1];
					cb.removeActionListener(al);
					cb.removeItem(newColor);
					cb.addItem(previousColor);
					cb.addActionListener(al);
				}

			}
			
			//
			removeTakenColors();

		}

	}
	
	//helper method for removing taken colors from comboboxes
	private void removeTakenColors(){
		ActionListener al;
		int numPairs = nameFields.length;
		// remove selected color from remaining ComboBoxes
		for (int i = 0; i < numPairs; i++) {
			JComboBox<String> cb = colorsLists[i];
			for(int j=0; j < colorStrings.length; j++){
				String color = colorStrings[j];
				if(takenColors.containsValue(color) && takenColors.get(i) != color){
					al = cb.getActionListeners()[cb.getActionListeners().length - 1];
					cb.removeItem(color);
					cb.addActionListener(al);
				}
			}
		}
	}

	/**
	 * Listener class for "Start" button. Passes message to GUIManager.
	 * 
	 */
	private class startListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int numPairs = nameFields.length;
			players = new Player[numPairs];
			for (int i = 0; i < numPairs; i++) {
				String name = nameFields[i].getText();
				String race = raceLists[i].getSelectedItem().toString();
				Color color = colors[Arrays.asList(colorStrings).indexOf(takenColors.get(i))];
				players[i] = new Player(name, race, color);
			//	players[i].setGameDifficulty(menu.getGameDifficulty());
			}
			finished = true;
		}
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
}