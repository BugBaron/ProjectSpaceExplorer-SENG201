package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceTitle;
import main.GameEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A screen to display the score at the end of every day
 * @author Daniel Harris and Rebekah McKinnon
 */
public class DailyScoreScreen extends JPanel {

	/** The window holding this panel */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in */
	private GameEnvironment gameEnvironment;
	
	/** A label to display the score that was achieved in the day */
	private SpaceLabel lblScore;
	
	
	/**
	 * Creates the panel
	 * @param guiWindow the window to create this panel for
	 */
	public DailyScoreScreen(GUIWindow guiWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		initialize();
	}
	
	
	/**
	 * Sets the labels to display the score
	 * @param scoreOutput the score to display
	 */
	public void dailyScore(String scoreOutput) {
		lblScore.setText(scoreOutput);
		guiWindow.mainScreen.lblDayNumber.setText(gameEnvironment.getDayString());
	}
	
	
	/**
	 * Initialize the panel contents
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Daily Score");
		lblTitle.setBounds(0, 0, 586, 80);
		add(lblTitle);
		
		lblScore = new SpaceLabel("");
		lblScore.setBounds(0, 182, 586, 63);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblScore);
		
		SpaceButton btnContinue = new SpaceButton("Continue");
		btnContinue.setBounds(151, 367, 284, 36);;
		add(btnContinue);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
				guiWindow.updatePane();
			}
		});
	}
	
}
