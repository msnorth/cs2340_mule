package edu.gatech.cs2340.ui;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gatech.cs2340.io.KeyboardAdapter;



/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: 	M5 		10/3/13 
 *         Modifications: 	M5 		10/6/13 Stephen Conway
 *         							Removed Control from View class
 *         					M5 		10/6/13	Thomas Mark
 *         							Modified replacing JFrame panels
 * 							M5 		10/7/13	Stephen Conway
 * 									Added default close operation. Reordered calls in constructor.
 * 							M6		10/15/13 Thomas Mark
 * 									Made dimensions constant and set at beginning of class.
 * 							M6		10/15/13 Stephen Conway
 * 									Added static getInstance method to prevent having to pass instance 5 layers deep.
 * 
 *         Purpose: Main JFrame for the game. Channels keyboard input to
 *         					KeyboardAdapter.
 * 
 */
public class MainGameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	public static final int DIM_X = 72*9 + 40;
	public static final int DIM_Y = 600;
	
	private JPanel currentPanel;

	private static MainGameWindow instance = null;
	
	/**
	 * 
	 * @return
	 */
	public static MainGameWindow getInstance() {
		return instance;
	}
	
	
	public static void initialize() {
		if (instance != null) {
			throw new RuntimeException("MainGameWindow already created!");
		}
		KeyboardAdapter kba = KeyboardAdapter.getInstance();
		if (kba == null) {
			throw new RuntimeException("KeyboardAdapter has not been initialized!");
		}
		instance = new MainGameWindow(kba);
	}
	
	/**
	 * Main constructor. Sets a KeyboardAdapter as the handler for keyboard
	 * input. Handles all setup and visibility of the frame.
	 * 
	 * @param keyboardAdapter
	 */
	private MainGameWindow(KeyboardAdapter keyboardAdapter) {
		instance = this;
		currentPanel = null;
		mainPanel = new JPanel();
		
		setFocusable(true);
		Dimension defaultSize = new Dimension(DIM_X, DIM_Y);
		setMinimumSize(defaultSize);
		setMaximumSize(defaultSize);
		setPreferredSize(defaultSize);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(mainPanel);
		setTitle("M.U.L.E. FRAME");
		addKeyListener(keyboardAdapter);
		
		setVisible(true);
		
	}

	/**
	 * Set the current panel to display
	 * 
	 * @param currentPanel
	 */
	public synchronized void setPanel(JPanel currentPanel) {
		if (currentPanel != null) {
			mainPanel.removeAll();
			mainPanel.repaint();
		}
		this.currentPanel = currentPanel;
		mainPanel.add(currentPanel);
		this.pack();
	}
	
	/**
	 * Get a reference to the currently displayed panel
	 * 
	 * @return Current panel
	 */
	public JPanel getCurrentPanel() {
		return currentPanel;
	}
}
