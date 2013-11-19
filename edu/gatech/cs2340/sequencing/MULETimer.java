package edu.gatech.cs2340.sequencing;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Stephen Conway Function group: Controller: Sequencing Created for: M6
 *         10/8/13 Assigned to: Stephen Modifications: M6 10/11/13 Added method
 *         to end thread. M7 10/21/13 Changed to run asynchronously (and
 *         optionally synchronously)
 * 
 *         Purpose: Blocks for a set amount of time.
 */
public class MULETimer implements WaitedOn, Serializable {
	protected static ArrayList<MULETimer> activeTimers;

	protected final long duration_ms;
	protected boolean stopped;
	protected long startTime_tick;
	protected long timeRemaining_ms;

	public static ArrayList<MULETimer> getActiveTimers() {
		if (activeTimers == null) {
			activeTimers = new ArrayList<MULETimer>();
		}
		return activeTimers;
	}

	/**
	 * Constructor to
	 * 
	 * @param duration_ms
	 */
	public MULETimer(long duration_ms) {
		this.duration_ms = duration_ms;
		stopped = false;
	}

	@Override
	public boolean isFinished() {
		boolean result = stopped
				|| startTime_tick + duration_ms / GameClock.TICK_LENGTH <= GameClock
						.getTick();
		if (result) {
			this.stop();
			activeTimers.remove(this);
		}
		return result;
	}

	/**
	 * Method to start the timer
	 */
	public void start() {
		stopped = false;
		if (activeTimers == null) {
			activeTimers = new ArrayList<MULETimer>();
		}
		startTime_tick = GameClock.getTick();
		timeRemaining_ms = duration_ms;

		activeTimers.add(this);

	}

	/**
	 * Method to run timer off the clock.
	 */
	public void startSynchronous() {
		try {
			Thread.sleep(duration_ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(99);
		}
		stopped = true;
	}

	/**
	 * Method to stop the timer. Cannot be restarted.
	 */
	public void stop() {
		stopped = true;
		timeRemaining_ms = getTimeRemaining();

	}

	/**
	 * Method to get remaining time on the timer
	 * 
	 * @return
	 */
	public long getTimeRemaining() {
		return duration_ms + (startTime_tick - GameClock.getTick())
				* GameClock.TICK_LENGTH;
	}

	/**
	 * Method to get the original duration of the timer
	 * 
	 * @return
	 */
	public long getTimerDuration() {
		return duration_ms;
	}

	/**
	 * Method to get starting time in System.current time
	 * 
	 * @return
	 */
	public long getStartTime() {
		return startTime_tick;
	}

}
