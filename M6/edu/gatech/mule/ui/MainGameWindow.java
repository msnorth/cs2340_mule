package edu.gatech.mule.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gatech.mule.utils.KeyboardAdapter;


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
 * 
 * 
 * 
 *         Purpose: Main JFrame for the game. Channels keyboard input to
 *         					KeyboardAdapter.
 * 
 */
public class MainGameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	
	private JPanel currentPanel;

	/**
	 * Main constructor. Sets a KeyboardAdapter as the handler for keyboard
	 * input. Handles all setup and visibility of the frame.
	 * 
	 * @param keyboardAdapter
	 */
	public MainGameWindow(KeyboardAdapter keyboardAdapter) {
		currentPanel = null;
		mainPanel = new JPanel();
		
		setFocusable(true);
		Dimension defaultSize = new Dimension(650, 757);
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
	public void setPanel(JPanel currentPanel) {
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
