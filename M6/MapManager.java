import javax.swing.JPanel;

import edu.gatech.mule.ui.MainGameWindow;
import edu.gatech.mule.utils.GUIManager;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Dan
 * 		Modifications:								
 * 
 * 
 * 
 * 		Purpose: Manages free movement of player over the game world
 */
public class MapManager implements GUIManager{
	private MainGameWindow mainGameWindow;
	private MULETimer timer;
	
	
	public MapManager(MainGameWindow mainGameWindow, long timeout_ms) {
		this.mainGameWindow = mainGameWindow;
		timer = new MULETimer(timeout_ms);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isFinished() {
		return timer.isFinished();
	}

	@Override
	public void notify(JPanel panel, String message) {
		// TODO Auto-generated method stub
		
	}
	
	
}
