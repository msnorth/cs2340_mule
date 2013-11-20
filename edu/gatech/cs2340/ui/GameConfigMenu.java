package edu.gatech.cs2340.ui;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import edu.gatech.cs2340.sequencing.WaitedOn;


/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: 	M5 		9/30/13 
 * 		   Modifications:	M5 		10/6/2013 Shreyyas Vanarase 
 * 									Updating panel viewability and features
 * 							M6		10/8/13 Stephen Conway
 * 									Made playerCount an instance rather than static and added a getter.
 * 
 *         Purpose: First panel of game configuration menu. Allows user to
 *         			select game difficulty, map type, and number of players This
 *         			information is passed back to GameConfigMenuPanel2
 * 
 */
public class GameConfigMenu extends JPanel implements WaitedOn {

	private static final long serialVersionUID = 1L;

	public static String[] diffLevels = { "Beginner", "Intermediate", "Advanced" };

	public static String[] maps = { "Irata 1", "Random Map" };

	public static Integer[] players = {2,3,4};

	private int playerCount;
	private boolean finished;
	
	private final JComboBox<Integer> numPlayers;
	private final JComboBox<String> gameLevel;
	private final JComboBox<String> mapTypes ;
	
	/**
	 * Main constructor
	 * 
	 * @param manager GUIManager to handle callback from "Next" button
	 */
	public GameConfigMenu() {
		
		this.setBackground(MainGameWindow.BACKGROUND_COLOR);
		
		SpringLayout layout = new SpringLayout();
		
		this.setLayout(layout);
		
		JLabel playerCountLabel = new JLabel("Number of Players: ");
		this.add(playerCountLabel);
		 numPlayers = new JComboBox<Integer>(players);
		playerCountLabel.setLabelFor(numPlayers);
		this.add(numPlayers);

		JLabel gameLevelLabel = new JLabel("Game Level: ");
		this.add(gameLevelLabel);
		gameLevel = new JComboBox<String>(diffLevels);
		gameLevelLabel.setLabelFor(gameLevel);
		this.add(gameLevel);

		JLabel mapTypeLabel = new JLabel("Map Type: ");
		this.add(mapTypeLabel);
		mapTypes = new JComboBox<String>(maps);
		mapTypeLabel.setLabelFor(mapTypes);
		this.add(mapTypes);

		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new nextListener());
		this.add(nextButton);
		
		JPanel fillerPanel = new JPanel();
		fillerPanel.setBackground(MainGameWindow.BACKGROUND_COLOR);
		this.add(fillerPanel);
		
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, 4, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

	}
	/**
	 * Get the player count
	 * @return
	 */
	public int getPlayerCount() {
		return playerCount;
	}
	/**
	 * Get the game difficulty
	 * @return
	 */
	public String getGameDifficulty() {
		return gameLevel.getSelectedItem().toString();
	}
	/**
	 * Get the map type
	 * @return
	 */
	public String getMapType() {
		return mapTypes.getSelectedItem().toString();
	}
	
	/**
	 * Listener class for "Next" button. Passes message to GUIManager.
	 *
	 */
	private class nextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			playerCount = Integer.parseInt(numPlayers.getSelectedItem().toString()); //Are you serious right now?
			finished = true;
		} 
	}

	/**
	 * Used to tell keywaiter to stop
	 */
	@Override
	public boolean isFinished() {
		return finished;
	}

	
}