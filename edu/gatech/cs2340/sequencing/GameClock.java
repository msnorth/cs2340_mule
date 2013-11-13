package edu.gatech.cs2340.sequencing;

import java.io.Serializable;

public class GameClock implements Runnable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6101080574828442432L;

	private static GameClock currentClock;
	
	public static final long TICK_LENGTH = 5;
	private static int tick = 0;
	private static boolean clockRunning = false;
	private static Thread thread;
	
	/**
	 * Disable creation of objects of this class by others
	 */
	private GameClock() {}
	
	
	public static long getTick() {
		return tick;
	}
	
	/**
	 * Method to start/resume the clock. Creates a new thread the first time.
	 */
	public static void startClock() {
		if (thread == null) {
			currentClock = new GameClock();
			thread = new Thread(currentClock);
			thread.start();
		}
		clockRunning = true;
	}
	
	/**
	 * Method used to start clock again from save file
	 * @param clock
	 */
	public static void startClock(GameClock clock) {
		if (thread == null) {
			currentClock = clock;
			Thread thread = new Thread(currentClock);
			thread.start();
		}
		clockRunning = true;
	}
	
	/**
	 * Method to get current clock for saving purposes
	 * @return
	 */
	public static GameClock getClock() {
		return currentClock;
	}
	
	/**
	 * Method to stop clock tick from incrementing
	 */
	public static void pauseClock() {
		clockRunning = false;
	}
	
	
	/**
	 * Method to increment the clock tick and alert timers that are done
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(TICK_LENGTH);
			} 
			catch (InterruptedException e) {
				System.out.println("Game Clock Interrupted.");
				e.printStackTrace();
				System.exit(0);
			}
			
			if (clockRunning) {
				tick++;
			}
		}
	}
	
	/**
	 * Method to block execution if the clock is not running
	 */
	public static void sync() {
		while (!clockRunning) {
			try {
				Thread.sleep(TICK_LENGTH);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(99);
			}
		}
	}
}
