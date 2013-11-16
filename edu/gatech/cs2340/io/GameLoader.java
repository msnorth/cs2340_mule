package edu.gatech.cs2340.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;






import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.sequencing.Waiter;

public class GameLoader {
	private String filename;
	
	public GameLoader(String filename) {
		this.filename = filename;
	}
	
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
