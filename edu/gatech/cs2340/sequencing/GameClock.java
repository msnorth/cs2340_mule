package edu.gatech.cs2340.sequencing;

import java.util.ArrayList;

public class GameClock implements Runnable {
	public static final long TICK_LENGTH = 50;
	private static int tick = 0;
	private static ArrayList<MULETimer> timers;
	private static ArrayList<MULETimer> toDelete;
	private static boolean timersLocked = false;
	private static boolean clockRunning = false;
	private static Thread thread;
	
	/**
	 * Disable creation of objects of this class by others
	 */
	private GameClock() {}
	
	/**
	 * Register a timer with the clock
	 * @param timer
	 */
	public static void registerTimer(MULETimer timer) {
		while (timersLocked) {
			Thread.yield();
		}
		timersLocked = true;
		timers.add(timer);
		timersLocked = false;
	}
	
	/**
	 * Method to remove dead timers from list
	 * @param timer
	 */
	public static synchronized void removeTimer(MULETimer timer) {
		if (toDelete == null) {
			toDelete = new ArrayList<MULETimer>();
		}
		toDelete.add(timer);
		if (!timersLocked) {
			for (MULETimer gonbdelete : toDelete) {
				timers.remove(gonbdelete);
			}
			toDelete.clear();
		}
	}
	
	/**
	 * Return time remaining on a given timer
	 * @param timer
	 * @return
	 */
	public static long getTimeRemaining(MULETimer timer) {
		return (tick - timer.getStartTime())*TICK_LENGTH;
	}
	
	/**
	 * Saves start time of a timer
	 * @param timer
	 */
	public static void start(MULETimer timer) {
		timer.setStartTime(tick);
	}
	
	/**
	 * Method to start/resume the clock. Creates a new thread the first time.
	 */
	public static void startClock() {
		if (thread == null) {
			Thread thread = new Thread(new GameClock());
			thread.start();
		}
		clockRunning = true;
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
		timers = new ArrayList<MULETimer>();
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
				while (timersLocked) {
					Thread.yield();
				}
				timersLocked = true;
				for (int i=0; i<timers.size(); i++) {
					MULETimer timer = timers.get(i);
					if (timer.isRunning() && (timer.getStartTime() + timer.getTimerDuration()/TICK_LENGTH) <= tick) {
						timer.stop();
					}
				}
				timersLocked = false;
			}
		}
	}
}
