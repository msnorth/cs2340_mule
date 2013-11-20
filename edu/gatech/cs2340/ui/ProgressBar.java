package edu.gatech.cs2340.ui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import edu.gatech.cs2340.sequencing.GameClock;
import edu.gatech.cs2340.sequencing.MULETimer;
/**
 * Class to show the timer bar's decrease during the game
 * @author ShreyyasV
 *
 */
public class ProgressBar extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final int HORIZONTAL_PADDING = 40;

	private MULETimer timer;
	private JProgressBar progressBar;
	Thread thread;

	/**
	 * Initializes the progress bar and adds it to a JPanel.
	 * 
	 * Max time for progress bar is turn length
	 * 
	 * Progress bar counts down
	 * 
	 * @param timer the timer used to set the progress
	 */
	public ProgressBar(MULETimer timer) {
		this.timer = timer;

		progressBar = new JProgressBar(JProgressBar.VERTICAL, 0,
				(int) timer.getTimerDuration());
		Dimension prefSize = progressBar.getPreferredSize();
		prefSize.height = MainGameWindow.LOWER_PANEL_HEIGHT
				- HORIZONTAL_PADDING;
		progressBar.setPreferredSize(prefSize);

		this.add(progressBar);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));

		thread = new Thread(this);
		thread.start();

	}
	/**
	 * Runs the progress bar
	 */
	@Override
	public void run() {

		while (!timer.isFinished()) {

			progressBar.setValue((int) timer.getTimeRemaining());

			try {
				Thread.sleep(GameClock.TICK_LENGTH);
			} catch (InterruptedException e) {

			}
		}

	}

}
