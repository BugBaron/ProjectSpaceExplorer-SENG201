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

import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

public class GUIMainScreen extends JPanel {
	
	SpaceMessagePane messagePane;
	SpaceLabel lblDayNumber;
	
	private InOutHandler inOut;
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GUIMainScreen(NewGUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		super.setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Control Panel");
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		lblDayNumber = new SpaceLabel(gameEnvironment.getDayString());
		lblDayNumber.setBounds(0, 80, 283, 36);
		super.add(lblDayNumber);
		
		SpaceButton btnViewCrewMember = new SpaceButton("View Crew members");
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		super.add(btnViewCrewMember);
		
		SpaceButton btnViewShipStatus = new SpaceButton("View ship status");
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		super.add(btnViewShipStatus);
		
		SpaceButton btnVisitSpaceOutpost = new SpaceButton("Visit space outpost");
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		super.add(btnVisitSpaceOutpost);
		
		SpaceButton btnContinue = new SpaceButton("Continue to next day");
		btnContinue.setBounds(0, 264, 283, 36);
		super.add(btnContinue);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		super.add(messagePane);
		
		JLabel lblImageControlPanel = new JLabel("");
		lblImageControlPanel.setIcon(new ImageIcon(NewGUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageControlPanel.setForeground(Color.WHITE);
		lblImageControlPanel.setBounds(303, 80, 283, 174);
		super.add(lblImageControlPanel);
		
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
				gameEnvironment.newDay();
				if ((boolean) inOut.getOutput()) {
					// The game has ended
					// TODO add code to change end game screen text based on how they lost
				} else {
					// The game has not ended
					Object output = inOut.getOutput();
					while (!(output instanceof Boolean)) {
						messagePaneContents.add((String) output);
						output = inOut.getOutput();
					}
					lblDayNumber.setText(gameEnvironment.getDayString()); // TODO this is temporary

					// TODO layout.show(frame.getContentPane(), "Score Screen"); and need 
					// to change the contents of the label using '(String) inOut.getContents()'
				}
			}
		});
	}
}
