package edu.gatech.cs2340.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import edu.gatech.cs2340.engine.Game;
import edu.gatech.cs2340.io.GameSaver;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.WaitedOn;
import edu.gatech.cs2340.test.DebugPrinter;

/**
 * Menu for loading and saving game, and returning to main menu
 * @author Dan
 * #M9
 */
public class InGameMenu extends JPanel implements ActionListener, WaitedOn {
	private static final long serialVersionUID = 1L;

	/**
	 * Labels that this is a pause menu
	 */
	private JLabel lblpause;
	/**
	 * Button to save game
	 */
	private JButton btnSaveGame;
	/**
	 * Button to load a new game
	 */
	private JButton btnLoad;
	/**
	 * Button to go to the main menu
	 */
	private JButton btnMainMenu;
	/**
	 * Button to return to the game
	 */
	private JButton btnExit;
	/**
	 * Reference to model corresponding to this presenter class
	 */
	InGameMenuManager inGameMenuManager;
	/**
	 * True if the panel is ready to return to the game
	 */
	private boolean isFinished;

	public InGameMenu() {
		this.setPreferredSize(new Dimension(445, 225));
		
		setLayout(null);

		lblpause = new JLabel("--PAUSE--");
		lblpause.setBounds(170, 15, 115, 29);
		add(lblpause);

		btnSaveGame = new JButton("Save Game");
		btnSaveGame.setBounds(150, 55, 115, 29);
		add(btnSaveGame);
		btnSaveGame.addActionListener(this);

		btnLoad = new JButton("Load Game");
		btnLoad.setBounds(150, 95, 115, 29);
		add(btnLoad);
		btnLoad.addActionListener(this);

		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(150, 135, 115, 29);
		add(btnMainMenu);
		btnMainMenu.addActionListener(this);
		
		btnExit = new JButton("Resume");
		btnExit.addActionListener(this);
		btnExit.setBounds(150, 175, 115, 29);
		add(btnExit);
	}	
	
	/**
	 * Perform the appropriate action for the button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSaveGame)) { // save
			inGameMenuManager.saveGame();
		} else if (e.getSource().equals(btnLoad)) { // load
			inGameMenuManager.loadGame();
		} else if (e.getSource().equals(btnMainMenu)) { // go to main menu
			inGameMenuManager.setReturnMainMenu(true);
			isFinished = true;
		} else if (e.getSource().equals(btnExit)) { // return to currently loaded game
			isFinished = true;
		}
	}

	/**
	 * True if panel has collected all user input
	 */
	@Override
	public boolean isFinished() {
		return isFinished;
	}
	
	/**
	 * Set the panel with a reference to its managing class
	 * @param inGameMenuManager
	 */
	public void setManager(InGameMenuManager inGameMenuManager) {
		this.inGameMenuManager = inGameMenuManager;		
	}
}
