package edu.gatech.cs2340.io;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.gatech.cs2340.data.Player;

public class SaveTester {
	private static String filename = "C:/Users/Stephen/Desktop/poopy.ser";
	public static void main(String[] args) {
		saveTest();
		loadTest();
	}
	
	public static void saveTest() {
		Player player = new Player("ShreyShreyHayHay", "Baller as they come", Color.BLACK);
		System.out.println(player);
		File file = new File(filename);

		try {
			if (!file.exists()) {
				if (!file.createNewFile()) {
					System.out.println("File creation failed: " + filename);
					System.exit(99);
				}
			}
			FileOutputStream output = new FileOutputStream(filename);
			ObjectOutputStream myAnus = new ObjectOutputStream(output);
			myAnus.writeObject(player);
			myAnus.close();
			output.close();
			System.out.println("Serialized at " + filename);
		}
		catch (IOException i) {
			System.out.println("Ho Wee Shee\nSum Ting Wong\nWee Too Lo");
			i.printStackTrace();
			System.exit(99);
		}
	}
	
	public static void loadTest() {
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("File finding failed: " + filename);
			System.exit(99);
		}
		try {
			FileInputStream input = new FileInputStream(filename);
			ObjectInputStream myMouth = new ObjectInputStream(input);
			Player player = (Player)myMouth.readObject();
			myMouth.close();
			input.close();
			System.out.println(player);
		}
		catch (IOException i){
			i.printStackTrace();
			System.exit(99);
		}
		catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			System.exit(99);
		}
	}

}
