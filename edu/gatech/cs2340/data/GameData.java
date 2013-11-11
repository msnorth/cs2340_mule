package edu.gatech.cs2340.data;

import java.io.Serializable;
import java.util.ArrayList;

import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;

public class GameData implements Serializable {
	private PlayerManager playerManager;
	private Store store;
	private GameClock gameClock;
	private ArrayList<MULETimer> timers;
	
	
}
