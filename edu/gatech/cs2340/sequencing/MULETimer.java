package edu.gatech.cs2340.sequencing;

import java.io.Serializable;

import edu.gatech.cs2340.test.DebugPrinter;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller: Sequencing
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M6		10/11/13
 * 									Added method to end thread.	
 * 							M7		10/21/13
 * 									Changed to run asynchronously (and optionally synchronously)
 * 
 * 		Purpose: Blocks for a set amount of time.
 */
public class MULETimer implements WaitedOn, Serializable {
	private final long duration_ms;
	private long remaining_ms;
	private boolean finished;
	private boolean stopped;
	private boolean running;
	private long startTime_tick;
	
	/**
	 * Constructor to
	 * 
	 * @param duration_ms
	 */
	public MULETimer(long duration_ms) {
		this.duration_ms = duration_ms;
		remaining_ms = duration_ms;
		stopped = false;
		//GameClock.registerTimer(this);
	}

	@Override
	public boolean isFinished() {
		return stopped || startTime_tick + duration_ms/GameClock.TICK_LENGTH <= GameClock.getTick();
		//return finished;
	}

	/**
	 * Method to start the timer
	 */
	public void start() {
		startTime_tick = GameClock.getTick();
		//GameClock.start(this);
		//running = true;
	}
	
	/**
	 * Method to stop the timer. Cannot be restarted.
	 */
	public void stop() {
		//running = false;
		//finished = true;
		stopped = true;
		//dispose();
	}
	
	/**
	 * Method to get remaining time on the timer
	 * @return
	 */
	public long getTimeRemaining() {
		/*
		if (running) {
			return GameClock.getTimeRemaining(this);
		}
		else {
			return remaining_ms;
		}
		*/
		return (GameClock.getTick() - startTime_tick + duration_ms/GameClock.TICK_LENGTH) * GameClock.TICK_LENGTH;
	}
	
	/**
	 * Method to get the original duration of the timer
	 * @return
	 */
	public long getTimerDuration() {
		return duration_ms;
	}
	
	/**
	 * Method to remove the timer from GameClock collection
	 */
	/*
	private void dispose() {
		GameClock.removeTimer(this);
	}
	*/
	/**
	 * Method to set the start time when the timer isn't running
	 */
	/*
	public void setStartTime(long startTime) {
		this.startTime_tick = startTime;
	}
	*/
	/**
	 * Method to get starting time in System.current time
	 * @return
	 */
	public long getStartTime() {
		return startTime_tick;
	}
	
	/**
	 * Method to determine if timer is currently running
	 * @return
	 */
	/*
	public boolean isRunning() {
		return running;
	}
	*/

}
