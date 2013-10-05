import edu.gatech.mule.ui.MainGameWindow;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainGameWindow frame = new MainGameWindow(null);
				frame.setVisible(true);
			}
		});
	}

}
