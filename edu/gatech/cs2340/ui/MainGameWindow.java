package edu.gatech.cs2340.ui;


import java.awt.BorderLayout;
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
	
	public static final int LOWER_PANEL_HEIGHT = 200;
	
	private JPanel currentPanel;
	//panel to hold status bar
	private  JPanel lowerPanel;
	private StatusBar statusBar;
	
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
		lowerPanel = null;
		mainPanel = new JPanel();
		lowerPanel = new JPanel();
		this.setLayout(new BorderLayout());
		
		setFocusable(true);
		Dimension defaultSize = new Dimension(DIM_X, DIM_Y);
		setMinimumSize(defaultSize);
		setMaximumSize(defaultSize);
		setPreferredSize(defaultSize);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension lowerSize = new Dimension(DIM_X, LOWER_PANEL_HEIGHT);
		lowerPanel.setPreferredSize(lowerSize);
		lowerPanel.setMaximumSize(lowerSize);
		lowerPanel.setMinimumSize(lowerSize);
		lowerPanel.setLayout(new BorderLayout());
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);
		setTitle("M.U.L.E. FRAME");
		addKeyListener(keyboardAdapter);
		
		setVisible(true);
		
	}

	/**
	 * Set the current panel to display
	 * 
	 * @param currentPanel
	 */
	public void setMainPanel(JPanel currentPanel) {
		if (currentPanel != null) {
			mainPanel.removeAll();
			mainPanel.repaint();
		}

		this.currentPanel = currentPanel;
		mainPanel.add(currentPanel);
		this.pack();
	}
	
	/**
	 * Set the current panel to display
	 * 
	 * Should be set before the main panel
	 * 
	 * @param lowerPanel
	 */
	public void setLowerPanel(StatusBar statusBar) {
		this.statusBar = statusBar;
		
		if(statusBar != null){
			lowerPanel.removeAll();
			lowerPanel.repaint();
		}
		lowerPanel.add(statusBar, BorderLayout.CENTER);
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
	
	/**
	 * Get a reference to the currently displayed lower panel
	 * 
	 * @return status bar panel
	 */
	public StatusBar getLowerPanel() {
		return statusBar;
	}
}
