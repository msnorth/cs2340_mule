package edu.gatech.cs2340.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

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
	
	public InGameMenu(){
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
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(150, 135, 115, 29);
		add(btnMainMenu);
		btnMainMenu.addActionListener(this);
		
	}

	/**
	 * Perform the appropriate action for the button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSaveGame)){
			// TODO save game
		} else if (e.getSource().equals(btnLoad)){
			// TODO prompt them to save if game isn't saved
			// TODO go to load a new game
		} else if (e.getSource().equals(btnMainMenu)){
			// TODO prompt them if they haven't yet saved or if that's an issue
			// TODO go to the main menu
		}
	}
}
