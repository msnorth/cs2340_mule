package edu.gatech.mule.utils;

import javax.swing.JPanel;

/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: 	M5 		10/6/13 
 *         Modifications:	M5 		10/6/13	Stephen Conway
 *         							Initial creation
 * 
 * 
 * 
 *         Purpose: implemented by anything expecting feedback from GUI
 */
public interface GUIManager{
	public void notify(JPanel panel, String message);
	
}
