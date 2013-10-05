package edu.gatech.mule.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import edu.gatech.mule.utils.KeyboardAdapter;


/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: M5 10/3/13 Last modified: M5 10/3/13 Stephen Conway
 * 
 * 
 * 
 *         Purpose: Main JFrame for the game. Channels keyboard input to
 *         KeyboardAdapter.
 * 
 */
public class MainGameWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	// unique integer defining which phase the game is in (i.e. 0 = start menu,
	// 1 = map, etc.)
	private int gamePhase = 0;

	/**
	 * Main constructor. Sets a KeyboardAdapter as the handler for keyboard
	 * input
	 * 
	 * @param keyboardAdapter
	 */
	public MainGameWindow(KeyboardAdapter keyboardAdapter) {
		setTitle("M.U.L.E. FRAME");
		this.setFocusable(true);
		setPanel();
		this.addKeyListener(keyboardAdapter);
	}

	// sets the current panel depending on the game phase
	private void setPanel() {
		switch (gamePhase) {
		// startup menu
		case 0:
			GameConfigMenuPanel1 panel = new GameConfigMenuPanel1();
			getContentPane().add(panel, BorderLayout.CENTER);
			break;

		default:
			// TODO what is default panel?
			break;
		}
	}

	// loads the new panel into the JFrame
	public void load() {
		setPanel();
	}

	public int getGamePhase() {
		return gamePhase;
	}

	public void setGamePhase(int gamePhase) {
		this.gamePhase = gamePhase;
	}

}
