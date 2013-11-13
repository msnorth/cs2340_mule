package edu.gatech.cs2340.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	public static final Color BACKGROUND_COLOR = new Color(255, 255, 102);
	private static JPanel mainPanel;
	public static final int DIM_X = 72*9 + 40;
	public static final int DIM_Y = 550;
	public static final int LOWER_PANEL_HEIGHT = 150;
	
	private static JPanel currentPanel;
	//panel to hold status bar
	private static JPanel lowerPanel;
	private static StatusBar statusBar;
	private static JLabel alertLabel;
	
	private static MainGameWindow instance = null;
	
	
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
		JPanel alertPanel = new JPanel();
		this.setLayout(new BorderLayout());
		
		setFocusable(true);
		Dimension defaultSize = new Dimension(DIM_X, DIM_Y + LOWER_PANEL_HEIGHT);
		setMinimumSize(defaultSize);
		setMaximumSize(defaultSize);
		setPreferredSize(defaultSize);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension lowerSize = new Dimension(DIM_X, LOWER_PANEL_HEIGHT);
		lowerPanel.setPreferredSize(lowerSize);
		lowerPanel.setMinimumSize(lowerSize);
		lowerPanel.setMaximumSize(lowerSize);
		lowerPanel.setLayout(new BorderLayout());
		
		alertLabel = new JLabel();
		alertLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		alertLabel.setForeground(Color.RED);
		alertPanel.add(alertLabel);
		
		mainPanel.setBackground(BACKGROUND_COLOR);
		lowerPanel.setBackground(BACKGROUND_COLOR);
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
	public static void setMainPanel(JPanel currentPanel) {
		if (currentPanel != null) {
			mainPanel.removeAll();
			mainPanel.repaint();
		}

		MainGameWindow.currentPanel = currentPanel;
		mainPanel.add(currentPanel);
		instance.pack();
	}
	
	/**
	 * Set the current panel to display
	 * 
	 * Should be set before the main panel
	 * 
	 * @param lowerPanel
	 */
	public static void setLowerPanel(StatusBar statusBar) {
		MainGameWindow.statusBar = statusBar;
		
		if(statusBar != null){
			lowerPanel.removeAll();
			lowerPanel.repaint();
		}
		lowerPanel.add(statusBar, BorderLayout.CENTER);
		JPanel alertPanel = new JPanel();
		alertPanel.add(alertLabel);
		lowerPanel.add(alertPanel, BorderLayout.SOUTH);
		instance.pack();
	}
	
	/**
	 * Get a reference to the currently displayed panel
	 * 
	 * @return Current panel
	 */
	public static JPanel getCurrentPanel() {
		return currentPanel;
	}
	
	/**
	 * Get a reference to the currently displayed lower panel
	 * 
	 * @return status bar panel
	 */
	public static StatusBar getLowerPanel() {
		return statusBar;
	}
	
	/**
	 * Displays a message in red at the bottom on the stat bar
	 * 
	 * @param message - Message to be displayed
	 */
	public static void setMessage(String message){
		alertLabel.setText(message);
		
	}
	/**
	 * Clears the message from the bottom of the screen
	 */
	public static void clearMessage(){
		alertLabel.setText("");
	}
}
