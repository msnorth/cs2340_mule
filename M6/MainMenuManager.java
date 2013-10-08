import javax.swing.JPanel;

import edu.gatech.mule.ui.GameConfigMenu;
import edu.gatech.mule.ui.MainGameWindow;
import edu.gatech.mule.ui.PlayerConfigMenu;
import edu.gatech.mule.utils.GUIManager;


/**
 * 
 * @author Stephen Conway
 * 		Function group:		Controller
 * 		Created for:		M6		10/8/13
 * 		Assigned to:		Stephen
 * 		Modifications:		M6		10/8/13		Stephen
 * 									Moved all management of the main manus to this calss from Driver			
 * 
 * 
 * 
 * 		Purpose: Common parent class for any GUI components			 
 */
public class MainMenuManager implements GUIManager{
	private boolean menuSequenceFinished;
	private MainGameWindow mainGameWindow;
	
	/**
	 * Main constructor. 
	 * @param mainGameWindow
	 */
	public MainMenuManager(MainGameWindow mainGameWindow) {
		this.mainGameWindow = mainGameWindow;
	}
	
	/**
	 * Method to start the main menu sequence
	 */
	public void run() {
		mainGameWindow.setPanel(new PlayerConfigMenu(this));
	}
	
	
	@Override
	public void notify(JPanel panel, String message) {
		if (panel instanceof PlayerConfigMenu && message.equals("next")) {
			GameConfigMenu gameConfig = new GameConfigMenu(this);
			mainGameWindow.setPanel(gameConfig);
		} 
		else if (panel instanceof GameConfigMenu && message.equals("next")) {
			menuSequenceFinished = true;
		}
	}

	@Override
	public boolean isFinished() {
		return menuSequenceFinished;
	}
	
}
