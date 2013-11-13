package edu.gatech.cs2340.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.gatech.cs2340.engine.Game;
import edu.gatech.cs2340.io.GameSaver;
import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.Waiter;

/**
 * Control class for InGameMenu
 * Handles loading and saving game and returning to main menu, etc. 
 * @author Dan
 *
 */
public class InGameMenuManager implements Runnable{

	/**
	 * Dialog box to select a file
	 */
	private JFileChooser fileChooser;
	
	/**
	 * Previously displayed panel
	 */
	private JPanel previousPanel;
	
	/**
	 * True if a new game was loaded in the course of the run
	 */
	private boolean gameLoaded;
	
	/**
	 * Presenter class associated with this control class
	 */
	private InGameMenu inGameMenu;

	/**
	 * True if this class should return to the main menu
	 */
	private boolean returnToMainMenu;
	
	/**
	 * Method to run the InGameMenuManager in a separate thread.
	 */
	public void runAsynchronous() {
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * Prepare to display on a JFrame. Saves previous state so state can resume later
	 */
	@Override
	public void run() {
		GameClock.pauseClock(); // pause the game
		// set up window
		inGameMenu = new InGameMenu();
		inGameMenu.setManager(this);
		// initialize internal variables
		gameLoaded = false; // there hasn't been a new game loaded yet
		returnToMainMenu = false; // assume we'll load a game
		fileChooser = new JFileChooser();
		// store the previous panel and make menu visible
		previousPanel = MainGameWindow.getCurrentPanel();
		MainGameWindow.setMainPanel(inGameMenu);
		// Wait
		Waiter.waitOn(inGameMenu); // wait until InGameMenu finishes collecting input
		if (returnToMainMenu){
			// TODO return to main menu
		} else {
			GameClock.startClock();
			if (gameLoaded){
				MainGameWindow.setMainPanel(previousPanel); // restore previous panel
			}
			// TODO return to game
		}
	}
	
	/**
	 * Save the game to a file
	 */
	public void saveGame() {
		Game game = Game.currentGame;
		if (game != null) {
			int returnVal = fileChooser.showDialog(inGameMenu, "Save M.U.L.E. Game...");
			// If the save dialog successfully chose a file,
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = fileChooser.getSelectedFile();
				GameSaver saver = new GameSaver(
						file.getAbsolutePath(),
						game.getGameData());
				saver.save();
			}
		}
	}
	
	/**
	 * Load the game from a file
	 */
	public void loadGame() {
		// prompt them to save if game isn't saved?
		// go to load a new game?
		int returnVal = fileChooser.showDialog(inGameMenu, "Save M.U.L.E. Game...");
		// If the save dialog successfully chose a file,
		if (returnVal == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			// TODO Load game
			JOptionPane.showMessageDialog(inGameMenu, "Game loaded");
			gameLoaded = true;
		}
	}

	/**
	 * @param returnToMainMenu True if we need to return to mainMenu upon finishing
	 */
	public void setReturnMainMenu(boolean returnToMainMenu) {
		// prompt to warn losing game data?
		this.returnToMainMenu = returnToMainMenu;		
	}
}
