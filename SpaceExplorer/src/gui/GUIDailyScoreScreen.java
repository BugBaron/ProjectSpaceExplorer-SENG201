package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.GameEnvironment;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIDailyScoreScreen extends JPanel {

	private GUIWindow guiWindow;
	private GameEnvironment gameEnvironment;
	private SpaceLabel lblScore;
	
	/**
	 * Create the panel.
	 */
	public GUIDailyScoreScreen(GUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		initialize();
	}
	
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
		super.add(lblTitle);
		
		lblScore = new SpaceLabel("");
		lblScore.setBounds(0, 182, 586, 63);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		super.add(lblScore);
		
		SpaceButton btnContinue = new SpaceButton("Continue");
		btnContinue.setBounds(151, 367, 284, 36);;
		super.add(btnContinue);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
				guiWindow.updatePane();
			}
		});
	}
	
}
