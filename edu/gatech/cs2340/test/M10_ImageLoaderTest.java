package edu.gatech.cs2340.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

import org.junit.Test;

import edu.gatech.cs2340.data.HillTile;
import edu.gatech.cs2340.data.MountainTile;
import edu.gatech.cs2340.data.PeakTile;
import edu.gatech.cs2340.data.PlainsTile;
import edu.gatech.cs2340.data.Player;
import edu.gatech.cs2340.data.RiverTile;
import edu.gatech.cs2340.data.Tile;
import edu.gatech.cs2340.data.TownTile;
import edu.gatech.cs2340.io.ImageLoader;
import edu.gatech.cs2340.io.SpriteImageLoader;
import edu.gatech.cs2340.io.StartScreenImageLoader;
import edu.gatech.cs2340.io.StoreImageLoader;
import edu.gatech.cs2340.io.TileImageLoader;
import edu.gatech.cs2340.io.TownImageLoader;
import edu.gatech.cs2340.sequencing.MULETimer;
import edu.gatech.cs2340.sequencing.Waiter;

/**
 * 
 * @author Stephen Conway
 * 
 * Class to test the loading of images.
 */
public class M10_ImageLoaderTest {

	/**
	 * Main test method. Loads all images through ImageLoader, then tests that all images are available.
	 */
	@Test
	public void test() {
		
		testPreLoad();
		
		ImageLoader.loadAllImages();
		MULETimer timer = new MULETimer(1000);
		timer.start();
		Waiter.waitOn(timer);
		
		spriteImageLoaderTest();
		startScreenLoaderTest();
		storeImageLoaderTest();
		tileImageLoaderTest();
		townImageLoaderTest();
		
		testPostLoad();
	}
	
	
	/**
	 * Test that all sprite images loaded properly
	 */
	private void spriteImageLoaderTest() {
		SpriteImageLoader loader = new SpriteImageLoader();
		String[] races = { "Bonzoid", "Buzzite", "Flapper", "Ugaite", "Human" };
		Color[] colors = { Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED };
		for (String race : races) {
			for (Color color : colors) {
				Player player = new Player("Name", race, color);
				ImageIcon image = loader.getImage(player);
				assertEquals(image.getImageLoadStatus(), MediaTracker.COMPLETE);
			}
		}
	}
	
	/**
	 * Test proper loading of the start screen.
	 */
	private void startScreenLoaderTest() {
		StartScreenImageLoader loader = new StartScreenImageLoader();
		loader.run();
		ImageIcon image = loader.getImage();
		assertEquals(image.getImageLoadStatus(), MediaTracker.COMPLETE);
	}
	
	/**
	 * Test that store images were loaded properly and are available for use.
	 */
	private void storeImageLoaderTest() {
		String[] imageNames = {"crystite", "energy", "food", "mule", "smithore"};
		StoreImageLoader loader = new StoreImageLoader();
		for (String imageName : imageNames) {
			ImageIcon image = loader.getImage(imageName);
			assertEquals(image.getImageLoadStatus(), MediaTracker.COMPLETE);
		}
	}
	
	/**
	 * Test that tile images were loaded properly and are available for use.
	 */
	private void tileImageLoaderTest() {
		Tile[] tiles = {new PlainsTile(null, null), new RiverTile(null, null), new TownTile(null, null), new HillTile(null, null), new MountainTile(null, null), new PeakTile(null, null)};
		TileImageLoader loader = new TileImageLoader();
		for (Tile tile : tiles) {
			ImageIcon image = loader.getImage(tile);
			assertEquals(image.getImageLoadStatus(), MediaTracker.COMPLETE);
		}
		
	}
	
	/**
	 * Test that town image was loaded properly and is available for use.
	 */
	private void townImageLoaderTest() {
		TownImageLoader loader = new TownImageLoader();
		ImageIcon image = loader.getImage();
		assertEquals(image.getImageLoadStatus(), MediaTracker.COMPLETE);
	}
	
	/**
	 * Test that loaders throw exception when attempts are made to access images before loading.
	 */
	private void testPreLoad() {
		//SpriteImageLoader
		boolean pass = false;
		SpriteImageLoader siLoader = new SpriteImageLoader();
		try {
			siLoader.getImage(new Player("ShreShre", "Human", Color.BLUE));
		}
		catch (ImageLoader.ImagesNotInitializedException e) {
			pass = true;
		}
		assertTrue(pass);
		
		//StartScreenLoader
		pass = false;
		StartScreenImageLoader ssLoader = new StartScreenImageLoader();
		try {
			ssLoader.getImage();
		}
		catch (ImageLoader.ImagesNotInitializedException e) {
			pass = true;
		}
		assertTrue(pass);
		
		//StoreImageLoader
		pass = false;
		StoreImageLoader stLoader = new StoreImageLoader();
		try {
			stLoader.getImage("smithore");
		}
		catch (ImageLoader.ImagesNotInitializedException e) {
			pass = true;
		}
		assertTrue(pass);	
		
		//TileImageLoader
		pass = false;
		TileImageLoader tiLoader = new TileImageLoader();
		try {
			tiLoader.getImage(new PlainsTile(null, null));
		}
		catch (ImageLoader.ImagesNotInitializedException e) {
			pass = true;
		}
		assertTrue(pass);	
		
		//TownImageLoader
		pass = false;
		TownImageLoader tnLoader = new TownImageLoader();
		try {
			tnLoader.getImage();
		}
		catch (ImageLoader.ImagesNotInitializedException e) {
			pass = true;
		}
		assertTrue(pass);	
	}
	
	/**
	 * Test that loaders throw exception when attempts are made to load after loading has occurred. 
	 */
	private void testPostLoad() {
		//SpriteImageLoader
		boolean pass = false;
		SpriteImageLoader siLoader = new SpriteImageLoader();
		try {
			siLoader.getImage(new Player("ShreShre", "Human", Color.BLUE));
		}
		catch (ImageLoader.ImagesAlreadyLoadedException e) {
			pass = true;
		}
		assertTrue(pass);
		
		//StartScreenLoader
		pass = false;
		StartScreenImageLoader ssLoader = new StartScreenImageLoader();
		try {
			ssLoader.getImage();
		}
		catch (ImageLoader.ImagesAlreadyLoadedException e) {
			pass = true;
		}
		assertTrue(pass);
		
		//StoreImageLoader
		pass = false;
		StoreImageLoader stLoader = new StoreImageLoader();
		try {
			stLoader.getImage("smithore");
		}
		catch (ImageLoader.ImagesAlreadyLoadedException e) {
			pass = true;
		}
		assertTrue(pass);	
		
		//TileImageLoader
		pass = false;
		TileImageLoader tiLoader = new TileImageLoader();
		try {
			tiLoader.getImage(new PlainsTile(null, null));
		}
		catch (ImageLoader.ImagesAlreadyLoadedException e) {
			pass = true;
		}
		assertTrue(pass);	
		
		//TownImageLoader
		pass = false;
		TownImageLoader tnLoader = new TownImageLoader();
		try {
			tnLoader.getImage();
		}
		catch (ImageLoader.ImagesAlreadyLoadedException e) {
			pass = true;
		}
		assertTrue(pass);	
	}
}
