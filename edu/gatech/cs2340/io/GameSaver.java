package edu.gatech.cs2340.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



import edu.gatech.cs2340.data.GameData;
import edu.gatech.cs2340.sequencing.Waiter;

public class GameSaver {
	private String filename;
	private GameData data;
	
	public GameSaver(String filename, GameData data) {
		this.filename = filename;
		this.data = data;
	}
	
	public void save() {
		//System.out.println("Waiting for save point.");
		Waiter.waitOn(data);
		//System.out.println("Save point reached.");
		
		try {
			FileOutputStream output = new FileOutputStream(filename);
			ObjectOutputStream thout = new ObjectOutputStream(output);
			thout.writeObject(data);
			thout.close();
			output.close();
			//System.out.println("Serialized at " + filename);
		}
		catch (IOException i) {
			i.printStackTrace();
			System.exit(99);
		}
	}
}
