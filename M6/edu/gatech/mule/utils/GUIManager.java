package edu.gatech.mule.utils;

import javax.swing.JPanel;


/**
 * 
 * @author Stephen Conway
 * 
 *         Created for: 	M5 		10/6/13 
 *         Modifications:	M5 		10/6/13	Stephen Conway
 *         							Initial creation
 *         					M6		10/8/13 Stephen
 *         							Added Runnable, WaitedOn interfaces.
 * 
 * 
 * 
 *         Purpose: Im
 */
public interface GUIManager extends WaitedOn{
	public void notify(JPanel panel, String message);
}
