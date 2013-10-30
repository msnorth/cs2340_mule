package edu.gatech.cs2340.test;

/**
 * 
 * @author Stephen Conway
 * 		Function group:		Tester
 * 		Created for:		M7		10/21/13
 * 		Assigned to:		
 * 		Modifications:		
 * 
 * 
 * 		Purpose: Print debug statements if debug is enabled, else don't
 */
public abstract class DebugPrinter {
	public static final boolean DEBUG_ENABLED = false;
	
	public static void println(String message) {
		if (DEBUG_ENABLED) {
			System.out.println(message);
		}
	}
}
