package edu.gatech.cs2340.data;

/**
 * This interface defines that the implementing class will
 * return a Map. It was created so the GameData class could
 * be mocked for a JUnit test.
 * 
 * @author Dan Fitzgerald
 *
 */
public interface MapHolder {
	public Map getMap();
}
