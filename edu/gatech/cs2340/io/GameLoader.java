package edu.gatech.cs2340.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;






import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.sequencing.Waiter;

/**
 * 
 * @author Stephen
 *
 * Class to load game from save file.
 */
public class GameLoader {
	private String filename;
	
	/**
	 * Main constructor. Needs a filename.
	 * @param filename
	 */
	public GameLoader(String filename) {
		this.filename = filename;
	}
	
	/**
	 * Execution. Attempts to read in GameData from the file.
	 * @return
	 */
	public GameData load() {
		GameData result = null;
		try {
			FileInputStream input = new FileInputStream(filename);
			ObjectInputStream thin = new ObjectInputStream(input);
			result = (GameData) thin.readObject();
			thin.close();
			input.close();
			//System.out.println("Loaded from " + filename);
		}
		catch (IOException i) {
			i.printStackTrace();
			System.exit(99);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(99);
		}
		return result;
	}
}
