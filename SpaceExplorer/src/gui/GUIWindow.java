package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUIWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIWindow window = new GUIWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.getContentPane().setLayout(new CardLayout(20, 20));
		
		JPanel mainScreen = new JPanel();
		mainScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(mainScreen, "name_113972757431300");
		mainScreen.setLayout(null);
		
		JLabel lblControlPanelTitle = new JLabel("Control Panel");
		lblControlPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlPanelTitle.setForeground(Color.YELLOW);
		lblControlPanelTitle.setFont(new Font("Lucida Console", Font.PLAIN, 40));
		lblControlPanelTitle.setBounds(0, 0, 586, 60);
		mainScreen.add(lblControlPanelTitle);
		
		JLabel lblDayNumber = new JLabel("Day number: 3/7");
		lblDayNumber.setForeground(Color.WHITE);
		lblDayNumber.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		lblDayNumber.setBounds(0, 80, 283, 36);
		mainScreen.add(lblDayNumber);
		
		JButton btnViewCrewMember = new JButton("View Crew members");
		btnViewCrewMember.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		mainScreen.add(btnViewCrewMember);
		
		JButton btnViewShipStatus = new JButton("View ship status");
		btnViewShipStatus.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		mainScreen.add(btnViewShipStatus);
		
		JButton btnVisitSpaceOutpost = new JButton("Visit space outpost");
		btnVisitSpaceOutpost.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		mainScreen.add(btnVisitSpaceOutpost);
		
		JButton btnContinue = new JButton("Continue to next day");
		btnContinue.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		btnContinue.setBounds(0, 264, 283, 36);
		mainScreen.add(btnContinue);
		
		JTextPane txtpnMessagePane = new JTextPane();
		txtpnMessagePane.setText("Message pane");
		txtpnMessagePane.setForeground(new Color(0, 0, 128));
		txtpnMessagePane.setFont(new Font("Lucida Console", Font.PLAIN, 20));
		txtpnMessagePane.setBounds(303, 264, 283, 93);
		mainScreen.add(txtpnMessagePane);
		
		JLabel lblImage = new JLabel("image");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setForeground(Color.WHITE);
		lblImage.setBounds(303, 80, 283, 174);
		mainScreen.add(lblImage);
		
		JPanel crewMembers = new JPanel();
		crewMembers.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(crewMembers, "name_113983805666300");
		crewMembers.setLayout(null);
		
		JLabel lblCrewMembersTitle = new JLabel("Crew Members");
		lblCrewMembersTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrewMembersTitle.setForeground(Color.YELLOW);
		lblCrewMembersTitle.setFont(new Font("Lucida Console", Font.PLAIN, 40));
		lblCrewMembersTitle.setBounds(0, 0, 586, 60);
		crewMembers.add(lblCrewMembersTitle);
		
		JTextPane messagePaneCrewMembers = new JTextPane();
		messagePaneCrewMembers.setText("Message pane");
		messagePaneCrewMembers.setForeground(new Color(0, 0, 128));
		messagePaneCrewMembers.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		messagePaneCrewMembers.setBounds(303, 264, 283, 93);
		crewMembers.add(messagePaneCrewMembers);
		
		JButton btnUseItem = new JButton("Use food/medical supplies");
		btnUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnUseItem.setBounds(0, 126, 283, 36);
		crewMembers.add(btnUseItem);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnSleep.setBounds(0, 172, 283, 36);
		crewMembers.add(btnSleep);
		
		JButton btnRepairShip = new JButton("Repair ship shields");
		btnRepairShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnRepairShip.setBounds(0, 218, 283, 36);
		crewMembers.add(btnRepairShip);
		
		JButton btnSearchPlanet = new JButton("Search planet");
		btnSearchPlanet.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnSearchPlanet.setBounds(0, 264, 283, 36);
		crewMembers.add(btnSearchPlanet);
		
		JButton btnPilotShip = new JButton("Pilot ship");
		btnPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnPilotShip.setBounds(0, 310, 283, 36);
		crewMembers.add(btnPilotShip);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBack.setBounds(303, 367, 283, 36);
		crewMembers.add(btnBack);
		
		JLabel lblCrewSelector = new JLabel("Crew Member:");
		lblCrewSelector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewSelector.setForeground(new Color(255, 255, 255));
		lblCrewSelector.setBounds(0, 80, 126, 36);
		crewMembers.add(lblCrewSelector);
		
		JComboBox comboBoxCrewMemberSelection = new JComboBox();
		comboBoxCrewMemberSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewMemberSelection.setModel(new DefaultComboBoxModel(new String[] {"Donald Trump, Human", "Robocop, Robot", "The White Wolf, Cyborg", "Chameleos, Lizard"}));
		comboBoxCrewMemberSelection.setBounds(135, 80, 441, 36);
		crewMembers.add(comboBoxCrewMemberSelection);
		
		JTextPane txtpnCrewMemberInfo = new JTextPane();
		txtpnCrewMemberInfo.setText("Crew member info");
		txtpnCrewMemberInfo.setForeground(Color.WHITE);
		txtpnCrewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnCrewMemberInfo.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfo.setBounds(303, 125, 283, 128);
		crewMembers.add(txtpnCrewMemberInfo);
		
		JPanel useItem = new JPanel();
		useItem.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(useItem, "name_116775304885600");
		useItem.setLayout(null);
		
		JLabel lblUseItemTitle = new JLabel("Use Item");
		lblUseItemTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblUseItemTitle.setForeground(Color.YELLOW);
		lblUseItemTitle.setFont(new Font("Lucida Console", Font.PLAIN, 40));
		lblUseItemTitle.setBounds(0, 0, 586, 60);
		useItem.add(lblUseItemTitle);
		
		JTextPane messagePaneUseItem = new JTextPane();
		messagePaneUseItem.setText("Message pane");
		messagePaneUseItem.setForeground(new Color(0, 0, 128));
		messagePaneUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		messagePaneUseItem.setBounds(303, 264, 283, 93);
		useItem.add(messagePaneUseItem);
		
		JButton btnBackUseItem = new JButton("Back");
		btnBackUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackUseItem.setBounds(303, 367, 283, 36);
		useItem.add(btnBackUseItem);
		
		JLabel lblItemSelector = new JLabel("Item:");
		lblItemSelector.setForeground(Color.WHITE);
		lblItemSelector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblItemSelector.setBounds(0, 80, 50, 36);
		useItem.add(lblItemSelector);
		
		JComboBox comboBoxItemSelection = new JComboBox();
		comboBoxItemSelection.setModel(new DefaultComboBoxModel(new String[] {"Space Plague Cure", "Band-Aid", "First Aid Kit", "Alien Meat", "Space Ration", "Water"}));
		comboBoxItemSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxItemSelection.setBounds(60, 80, 222, 36);
		useItem.add(comboBoxItemSelection);
		
		JButton btnConfirmUseItem = new JButton("Use item");
		btnConfirmUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnConfirmUseItem.setBounds(0, 264, 283, 36);
		useItem.add(btnConfirmUseItem);
		
		JTextPane txtpnCrewMemberInfoUseItem = new JTextPane();
		txtpnCrewMemberInfoUseItem.setText("Crew member info");
		txtpnCrewMemberInfoUseItem.setForeground(Color.WHITE);
		txtpnCrewMemberInfoUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnCrewMemberInfoUseItem.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfoUseItem.setBounds(303, 80, 283, 174);
		useItem.add(txtpnCrewMemberInfoUseItem);
		
		JTextPane txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(0, 126, 283, 128);
		useItem.add(txtpnItemInfo);
		
		JPanel pilotShip = new JPanel();
		pilotShip.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(pilotShip, "name_119415888073800");
		pilotShip.setLayout(null);
		
		JLabel lblPilotShipTitle = new JLabel("Pilot Ship");
		lblPilotShipTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPilotShipTitle.setForeground(Color.YELLOW);
		lblPilotShipTitle.setFont(new Font("Lucida Console", Font.PLAIN, 40));
		lblPilotShipTitle.setBounds(0, 0, 586, 60);
		pilotShip.add(lblPilotShipTitle);
		
		JButton btnConfirmPilotShip = new JButton("Pilot Ship");
		btnConfirmPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnConfirmPilotShip.setBounds(0, 264, 283, 36);
		pilotShip.add(btnConfirmPilotShip);
		
		JTextPane messagePanePilotShip = new JTextPane();
		messagePanePilotShip.setText("Message pane");
		messagePanePilotShip.setForeground(new Color(0, 0, 128));
		messagePanePilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		messagePanePilotShip.setBounds(303, 264, 283, 93);
		pilotShip.add(messagePanePilotShip);
		
		JButton btnBackPilotShip = new JButton("Back");
		btnBackPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackPilotShip.setBounds(303, 367, 283, 36);
		pilotShip.add(btnBackPilotShip);
		
		JLabel lblCrewMember2Selector = new JLabel("Second crew member:");
		lblCrewMember2Selector.setForeground(Color.WHITE);
		lblCrewMember2Selector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewMember2Selector.setBounds(0, 80, 198, 36);
		pilotShip.add(lblCrewMember2Selector);
		
		JComboBox comboBoxCrewMember2Selection = new JComboBox();
		comboBoxCrewMember2Selection.setModel(new DefaultComboBoxModel(new String[] {"Donald Trump, Human", "Robocop, Robot", "Chameleos, Lizard"}));
		comboBoxCrewMember2Selection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewMember2Selection.setBounds(208, 80, 368, 36);
		pilotShip.add(comboBoxCrewMember2Selection);
		
		JTextPane txtpnSecondCrewMember = new JTextPane();
		txtpnSecondCrewMember.setBackground(new Color(25, 25, 112));
		txtpnSecondCrewMember.setForeground(new Color(255, 255, 255));
		txtpnSecondCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnSecondCrewMember.setText("Second crew member info");
		txtpnSecondCrewMember.setBounds(0, 126, 283, 128);
		pilotShip.add(txtpnSecondCrewMember);
		
		JTextPane txtpnCrewMemberInfoPilotShip = new JTextPane();
		txtpnCrewMemberInfoPilotShip.setText("Crew member info");
		txtpnCrewMemberInfoPilotShip.setForeground(Color.WHITE);
		txtpnCrewMemberInfoPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnCrewMemberInfoPilotShip.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfoPilotShip.setBounds(303, 126, 283, 128);
		pilotShip.add(txtpnCrewMemberInfoPilotShip);
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
