package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import main.CrewMemberTypes.CrewMember;

public class GUIMainScreen implements GUIScreen{
	
	JPanel panel;
	SpaceMessagePane messagePane;
	SpaceLabel lblDayNumber;
	
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GUIMainScreen(NewGUIWindow guiWindow) {
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setLayout(null);
		
		SpaceTitle lblTitle = new SpaceTitle("Control Panel");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblTitle.setBounds(0, 0, 586, 60);
		panel.add(lblTitle);
		
		lblDayNumber = new SpaceLabel(gameEnvironment.getDayString());
		lblDayNumber.setForeground(Color.WHITE);
		lblDayNumber.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblDayNumber.setBounds(0, 80, 283, 36);
		panel.add(lblDayNumber);
		
		JButton btnViewCrewMember = new JButton("View Crew members");
		btnViewCrewMember.setFocusable(false);
		btnViewCrewMember.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewCrewMember.setForeground(Color.WHITE);
		btnViewCrewMember.setBackground(new Color(25, 25, 112));
		btnViewCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		panel.add(btnViewCrewMember);
		
		JButton btnViewShipStatus = new JButton("View ship status");
		btnViewShipStatus.setFocusable(false);
		btnViewShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShipStatus.setForeground(Color.WHITE);
		btnViewShipStatus.setBackground(new Color(25, 25, 112));
		btnViewShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		panel.add(btnViewShipStatus);
		
		JButton btnVisitSpaceOutpost = new JButton("Visit space outpost");
		btnVisitSpaceOutpost.setFocusable(false);
		btnVisitSpaceOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnVisitSpaceOutpost.setForeground(new Color(255, 255, 255));
		btnVisitSpaceOutpost.setBackground(new Color(25, 25, 112));
		btnVisitSpaceOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		panel.add(btnVisitSpaceOutpost);
		
		JButton btnContinue = new JButton("Continue to next day");
		btnContinue.setFocusable(false);
		btnContinue.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(new Color(25, 25, 112));
		btnContinue.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnContinue.setBounds(0, 264, 283, 36);
		panel.add(btnContinue);
		
		JTextPane txtpnMessagePane = new JTextPane();
		txtpnMessagePane.setEditable(false);
		txtpnMessagePane.setText("Message pane");
		txtpnMessagePane.setForeground(new Color(0, 0, 128));
		txtpnMessagePane.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePane.setBounds(303, 264, 283, 93);
		panel.add(txtpnMessagePane);
		
		SpaceLabel lblImageControlPanel = new SpaceLabel("");
		lblImageControlPanel.setIcon(new ImageIcon(NewGUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageControlPanel.setForeground(Color.WHITE);
		lblImageControlPanel.setBounds(303, 80, 283, 174);
		panel.add(lblImageControlPanel);
	}
}
