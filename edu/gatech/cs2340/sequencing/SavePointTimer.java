package edu.gatech.cs2340.sequencing;

import edu.gatech.cs2340.data.GameData;

public class SavePointTimer extends MULETimer {

	GameData data;
	
	public SavePointTimer(long duration_ms, GameData data) {
		super(duration_ms);
		this.data = data;
	}
	
	@Override
	public boolean isFinished() {
		boolean result = stopped || startTime_tick + duration_ms/GameClock.TICK_LENGTH <= GameClock.getTick();
		if (result) {
			activeTimers.remove(this);
		}
		
		data.savePoint();
		
		return result;
	}

}
