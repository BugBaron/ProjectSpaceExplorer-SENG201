package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

/**
 * A screen for the user to choose what they would like to do in the game
 * @author Daniel Harris and Rebekah McKinnon
 *
 */
public class MainScreen extends JPanel {
	
	/* 
	 * These variables have default visibility so they can be adjusted and/or 
	 * used to update other widgets 
	 */
	/** A message pane to display important information */
	SpaceMessagePane messagePane;
	SpaceLabel lblDayNumber;
	
	/** The window holding this panel */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in */
	private GameEnvironment gameEnvironment;
	/** The contents of the message pane */
	private ArrayList<String> messagePaneContents;
	/** The object which is handling the input and output of the game environment */
	private InOutHandler inOut;

	
	/**
	 * Creates the panel
	 * @param guiWindow the window to create this panel for
	 */
	public MainScreen(GUIWindow guiWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
	/**
	 * Initialize the panel contents
	 */
	public void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Control Panel");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		lblDayNumber = new SpaceLabel(gameEnvironment.getDayString());
		lblDayNumber.setBounds(0, 80, 283, 36);
		add(lblDayNumber);
		
		SpaceButton btnViewCrewMember = new SpaceButton("View Crew members");
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		add(btnViewCrewMember);
		
		SpaceButton btnViewShipStatus = new SpaceButton("View ship status");
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		add(btnViewShipStatus);
		
		SpaceButton btnVisitSpaceOutpost = new SpaceButton("Visit space outpost");
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		add(btnVisitSpaceOutpost);
		
		SpaceButton btnContinue = new SpaceButton("Continue to next day");
		btnContinue.setBounds(0, 264, 283, 36);
		add(btnContinue);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		JLabel lblImageControlPanel = new JLabel("");
		lblImageControlPanel.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageControlPanel.setForeground(Color.WHITE);
		lblImageControlPanel.setBounds(303, 80, 283, 174);
		add(lblImageControlPanel);
		
		SpaceButton btnInstructions = new SpaceButton("Instructions");
		btnInstructions.setBounds(303, 367, 283, 36);
		add(btnInstructions);
		
		btnViewCrewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<CrewMember> crewMembers = gameEnvironment.getCrewMembers();
				CrewMember[] newMembers = new CrewMember[crewMembers.size()];
				for (int i = 0; i < crewMembers.size(); i++) {
					newMembers[i] = crewMembers.get(i);
				}
				guiWindow.crewMembersScreen.crewMemberSelection.setModel(new DefaultComboBoxModel<CrewMember>(newMembers));
				guiWindow.crewMembersScreen.updateCrewMembers();
				
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Crew Members");
				guiWindow.updatePane();
			}
		});
		
		btnViewShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.shipStatusScreen.txtpnShipStatus.setText(gameEnvironment.getShipString());
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Ship Status");
				guiWindow.updatePane();
			}
		});

		btnVisitSpaceOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Visit Outpost");
				guiWindow.updatePane();
			}
		});
		
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = gameEnvironment.newDay();
				if (result == true) {
					guiWindow.endGameScreen.runEndDay(false);
					guiWindow.layout.show(guiWindow.frame.getContentPane(), "End Game Screen");
					guiWindow.updatePane();
				} else {
					// The game has not ended
					Object output = inOut.getOutput();
					while (!(output instanceof Boolean)) {
						messagePaneContents.add((String) output);
						output = inOut.getOutput();
					}
					String scoreOutput = "" + inOut.getOutput();
					guiWindow.dailyScoreScreen.dailyScore(scoreOutput);
					guiWindow.layout.show(guiWindow.frame.getContentPane(), "Daily Score");
					guiWindow.updatePane();
				}
			}
		});
		
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Instructions Screen");
			}
		});
		
	}
}
