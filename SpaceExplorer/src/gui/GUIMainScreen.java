package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import main.Consumable;
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
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		lblDayNumber = new SpaceLabel(gameEnvironment.getDayString());
		lblDayNumber.setForeground(Color.WHITE);
		lblDayNumber.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblDayNumber.setBounds(0, 80, 283, 36);
		super.add(lblDayNumber);
		
		SpaceButton btnViewCrewMember = new SpaceButton("View Crew members");
		btnViewCrewMember.setFocusable(false);
		btnViewCrewMember.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewCrewMember.setForeground(Color.WHITE);
		btnViewCrewMember.setBackground(new Color(25, 25, 112));
		btnViewCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		super.add(btnViewCrewMember);
		
		SpaceButton btnViewShipStatus = new SpaceButton("View ship status");
		btnViewShipStatus.setFocusable(false);
		btnViewShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShipStatus.setForeground(Color.WHITE);
		btnViewShipStatus.setBackground(new Color(25, 25, 112));
		btnViewShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		super.add(btnViewShipStatus);
		
		SpaceButton btnVisitSpaceOutpost = new SpaceButton("Visit space outpost");
		btnVisitSpaceOutpost.setFocusable(false);
		btnVisitSpaceOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnVisitSpaceOutpost.setForeground(new Color(255, 255, 255));
		btnVisitSpaceOutpost.setBackground(new Color(25, 25, 112));
		btnVisitSpaceOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		super.add(btnVisitSpaceOutpost);
		
		SpaceButton btnContinue = new SpaceButton("Continue to next day");
		btnContinue.setBounds(0, 264, 283, 36);
		super.add(btnContinue);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		super.add(messagePane);
		
		SpaceLabel lblImageControlPanel = new SpaceLabel("");
		lblImageControlPanel.setIcon(new ImageIcon(NewGUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageControlPanel.setForeground(Color.WHITE);
		lblImageControlPanel.setBounds(303, 80, 283, 174);
		super.add(lblImageControlPanel);
		
		btnViewCrewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Crew Members");
				guiWindow.updateCrewMemberInfo();
				guiWindow.updatePane();
			}
		});
		
		btnViewShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.shipScreen.txtpnShipStatus.setText(gameEnvironment.getShipString());
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
