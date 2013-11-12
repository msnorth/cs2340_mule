package edu.gatech.cs2340.ui;

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
import edu.gatech.cs2340.test.DebugPrinter;

public class InGameMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Labels that this is a pause menu
	 */
	private JLabel lblpause;
	/**
	 * Button to save game
	 */
	JButton btnSaveGame;

	/**
	 * Button to load a new game
	 */
	JButton btnLoad;

	/**
	 * Button to go to the main menu
	 */
	JButton btnMainMenu;
	
	/**
	 * Button to return to the game
	 */
	JButton btnExit;
	
	/**
	 * Dialog box to select a file
	 */
	JFileChooser fileChooser;
	
	/**
	 * Previously displayed panel
	 */
	JPanel previousPanel;
	
	/**
	 * The main game window
	 */
	MainGameWindow mainGameWindow;
	
	/**
	 * True if a new game was loaded in the course of the run
	 */
	boolean gameLoaded;

	public InGameMenu() {
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
		
		btnExit = new JButton("Exit to Game");
		btnExit.setBounds(150, 175, 115, 29);
		add(btnExit);
		
		mainGameWindow = MainGameWindow.getInstance();
		fileChooser = new JFileChooser();
		gameLoaded = false; // there hasn't been a new game loaded yet
	}

	/**
	 * Prepare to display on a JFrame. Saves previous state so state can resume later
	 */
	public void initialize(){
		GameClock.pauseClock(); // pause the game
		gameLoaded = false; // there hasn't been a new game loaded yet
		previousPanel = mainGameWindow.getCurrentPanel();
		mainGameWindow.setMainPanel(this);
	}
	
	/**
	 * Perform the appropriate action for the button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// User presses "Save"
		if (e.getSource().equals(btnSaveGame)) {
			Game game = Game.currentGame;
			if (game != null) {
				int returnVal = fileChooser.showDialog(this, "Save M.U.L.E. Game...");
				// If the save dialog successfully chose a file,
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					GameSaver saver = new GameSaver(
							file.getAbsolutePath(),
							game.getGameData());
					saver.save();
				}
			}
		} else if (e.getSource().equals(btnLoad)) {
			// TODO prompt them to save if game isn't saved
			// TODO go to load a new game
		} else if (e.getSource().equals(btnMainMenu)) {
			// TODO prompt them if they haven't yet saved or if that's an issue
			// TODO kill current threads
			// TODO go to main menu
			// Driver.main()? MainMenuManager.runSynchronous()?
			// GameClock.startClock()?
		} else if (e.getSource().equals(btnExit)) {
			if (gameLoaded){
				// TODO go to loaded game
			} else {
				if (previousPanel == null){
					DebugPrinter.println("previousPanel never set in InGameMenu");
				}
				mainGameWindow.setMainPanel(previousPanel);
				GameClock.startClock();
			}
		}
	}
}
