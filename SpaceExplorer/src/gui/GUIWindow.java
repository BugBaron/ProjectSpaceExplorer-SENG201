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
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.AbstractListModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		lblControlPanelTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblControlPanelTitle.setBounds(0, 0, 586, 60);
		mainScreen.add(lblControlPanelTitle);
		
		JLabel lblDayNumber = new JLabel("Day number: 3/7");
		lblDayNumber.setForeground(Color.WHITE);
		lblDayNumber.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblDayNumber.setBounds(0, 80, 283, 36);
		mainScreen.add(lblDayNumber);
		
		JButton btnViewCrewMember = new JButton("View Crew members");
		btnViewCrewMember.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewCrewMember.setForeground(Color.WHITE);
		btnViewCrewMember.setBackground(new Color(25, 25, 112));
		btnViewCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		mainScreen.add(btnViewCrewMember);
		
		JButton btnViewShipStatus = new JButton("View ship status");
		btnViewShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShipStatus.setForeground(Color.WHITE);
		btnViewShipStatus.setBackground(new Color(25, 25, 112));
		btnViewShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		mainScreen.add(btnViewShipStatus);
		
		JButton btnVisitSpaceOutpost = new JButton("Visit space outpost");
		btnVisitSpaceOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnVisitSpaceOutpost.setForeground(new Color(255, 255, 255));
		btnVisitSpaceOutpost.setBackground(new Color(25, 25, 112));
		btnVisitSpaceOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		mainScreen.add(btnVisitSpaceOutpost);
		
		JButton btnContinue = new JButton("Continue to next day");
		btnContinue.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnContinue.setForeground(Color.WHITE);
		btnContinue.setBackground(new Color(25, 25, 112));
		btnContinue.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnContinue.setBounds(0, 264, 283, 36);
		mainScreen.add(btnContinue);
		
		JTextPane txtpnMessagePane = new JTextPane();
		txtpnMessagePane.setEditable(false);
		txtpnMessagePane.setText("Message pane");
		txtpnMessagePane.setForeground(new Color(0, 0, 128));
		txtpnMessagePane.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePane.setBounds(303, 264, 283, 93);
		mainScreen.add(txtpnMessagePane);
		
		JLabel lblImageControlPanel = new JLabel("");
		lblImageControlPanel.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageControlPanel.setForeground(Color.WHITE);
		lblImageControlPanel.setBounds(303, 80, 283, 174);
		mainScreen.add(lblImageControlPanel);
		
		JPanel crewMembers = new JPanel();
		crewMembers.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(crewMembers, "name_113983805666300");
		crewMembers.setLayout(null);
		
		JLabel lblCrewMembersTitle = new JLabel("Crew Members");
		lblCrewMembersTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrewMembersTitle.setForeground(Color.YELLOW);
		lblCrewMembersTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblCrewMembersTitle.setBounds(0, 0, 586, 60);
		crewMembers.add(lblCrewMembersTitle);
		
		JTextPane txtpnMessagePaneCrewMembers = new JTextPane();
		txtpnMessagePaneCrewMembers.setEditable(false);
		txtpnMessagePaneCrewMembers.setText("Message pane");
		txtpnMessagePaneCrewMembers.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneCrewMembers.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePaneCrewMembers.setBounds(303, 264, 283, 93);
		crewMembers.add(txtpnMessagePaneCrewMembers);
		
		JButton btnUseItem = new JButton("Use food/medical supplies");
		btnUseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnUseItem.setForeground(Color.WHITE);
		btnUseItem.setBackground(new Color(25, 25, 112));
		btnUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnUseItem.setBounds(0, 126, 283, 36);
		crewMembers.add(btnUseItem);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnSleep.setForeground(Color.WHITE);
		btnSleep.setBackground(new Color(25, 25, 112));
		btnSleep.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnSleep.setBounds(0, 172, 283, 36);
		crewMembers.add(btnSleep);
		
		JButton btnRepairShip = new JButton("Repair ship shields");
		btnRepairShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnRepairShip.setForeground(Color.WHITE);
		btnRepairShip.setBackground(new Color(25, 25, 112));
		btnRepairShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnRepairShip.setBounds(0, 218, 283, 36);
		crewMembers.add(btnRepairShip);
		
		JButton btnSearchPlanet = new JButton("Search planet");
		btnSearchPlanet.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnSearchPlanet.setForeground(Color.WHITE);
		btnSearchPlanet.setBackground(new Color(25, 25, 112));
		btnSearchPlanet.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnSearchPlanet.setBounds(0, 264, 283, 36);
		crewMembers.add(btnSearchPlanet);
		
		JButton btnPilotShip = new JButton("Pilot ship");
		btnPilotShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnPilotShip.setForeground(Color.WHITE);
		btnPilotShip.setBackground(new Color(25, 25, 112));
		btnPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnPilotShip.setBounds(0, 310, 283, 36);
		crewMembers.add(btnPilotShip);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(25, 25, 112));
		btnBack.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBack.setBounds(303, 367, 283, 36);
		crewMembers.add(btnBack);
		
		JLabel lblCrewSelector = new JLabel("Crew Member:");
		lblCrewSelector.setForeground(Color.WHITE);
		lblCrewSelector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
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
		lblUseItemTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblUseItemTitle.setBounds(0, 0, 586, 60);
		useItem.add(lblUseItemTitle);
		
		JTextPane txtpnMessagePaneUseItem = new JTextPane();
		txtpnMessagePaneUseItem.setEditable(false);
		txtpnMessagePaneUseItem.setText("Message pane");
		txtpnMessagePaneUseItem.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePaneUseItem.setBounds(303, 264, 283, 93);
		useItem.add(txtpnMessagePaneUseItem);
		
		JButton btnBackUseItem = new JButton("Back");
		btnBackUseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackUseItem.setForeground(Color.WHITE);
		btnBackUseItem.setBackground(new Color(25, 25, 112));
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
		btnConfirmUseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnConfirmUseItem.setForeground(Color.WHITE);
		btnConfirmUseItem.setBackground(new Color(25, 25, 112));
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
		lblPilotShipTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblPilotShipTitle.setBounds(0, 0, 586, 60);
		pilotShip.add(lblPilotShipTitle);
		
		JButton btnConfirmPilotShip = new JButton("Pilot Ship");
		btnConfirmPilotShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnConfirmPilotShip.setForeground(Color.WHITE);
		btnConfirmPilotShip.setBackground(new Color(25, 25, 112));
		btnConfirmPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnConfirmPilotShip.setBounds(0, 264, 283, 36);
		pilotShip.add(btnConfirmPilotShip);
		
		JTextPane txtpnMessagePanePilotShip = new JTextPane();
		txtpnMessagePanePilotShip.setEditable(false);
		txtpnMessagePanePilotShip.setText("Message pane");
		txtpnMessagePanePilotShip.setForeground(new Color(0, 0, 128));
		txtpnMessagePanePilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePanePilotShip.setBounds(303, 264, 283, 93);
		pilotShip.add(txtpnMessagePanePilotShip);
		
		JButton btnBackPilotShip = new JButton("Back");
		btnBackPilotShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackPilotShip.setForeground(Color.WHITE);
		btnBackPilotShip.setBackground(new Color(25, 25, 112));
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
		
		JPanel shipStatus = new JPanel();
		shipStatus.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(shipStatus, "name_37658506724700");
		shipStatus.setLayout(null);
		
		JLabel lblShipStatus = new JLabel("Ship Status");
		lblShipStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblShipStatus.setForeground(Color.YELLOW);
		lblShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblShipStatus.setBounds(0, 0, 586, 60);
		shipStatus.add(lblShipStatus);
		
		JLabel lblImageShipStatus = new JLabel("");
		lblImageShipStatus.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageShipStatus.setVerticalAlignment(SwingConstants.TOP);
		lblImageShipStatus.setBounds(303, 80, 283, 174);
		shipStatus.add(lblImageShipStatus);
		
		JTextPane txtpnMessagePaneShipStatus = new JTextPane();
		txtpnMessagePaneShipStatus.setEditable(false);
		txtpnMessagePaneShipStatus.setText("Message pane");
		txtpnMessagePaneShipStatus.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePaneShipStatus.setBounds(303, 264, 283, 93);
		shipStatus.add(txtpnMessagePaneShipStatus);
		
		JButton btnBackShipStatus = new JButton("Back");
		btnBackShipStatus.setForeground(Color.WHITE);
		btnBackShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackShipStatus.setBackground(new Color(25, 25, 112));
		btnBackShipStatus.setBounds(303, 367, 283, 36);
		shipStatus.add(btnBackShipStatus);
		
		JTextPane txtpnShipStatus = new JTextPane();
		txtpnShipStatus.setText("Ship Status");
		txtpnShipStatus.setForeground(Color.WHITE);
		txtpnShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnShipStatus.setBackground(new Color(25, 25, 112));
		txtpnShipStatus.setBounds(0, 80, 283, 174);
		shipStatus.add(txtpnShipStatus);
		
		JPanel visitOutpost = new JPanel();
		visitOutpost.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(visitOutpost, "name_43303579242700");
		visitOutpost.setLayout(null);
		
		JLabel lblVisitOutpostTitle = new JLabel("Outpost");
		lblVisitOutpostTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisitOutpostTitle.setForeground(Color.YELLOW);
		lblVisitOutpostTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblVisitOutpostTitle.setBounds(0, 0, 586, 60);
		visitOutpost.add(lblVisitOutpostTitle);
		
		JLabel lblImageVisitOutpost = new JLabel("");
		lblImageVisitOutpost.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/OUTPOST.png")));
		lblImageVisitOutpost.setVerticalAlignment(SwingConstants.TOP);
		lblImageVisitOutpost.setBounds(303, 80, 283, 174);
		visitOutpost.add(lblImageVisitOutpost);
		
		JTextPane txtpnMessagePaneVisitOutpost = new JTextPane();
		txtpnMessagePaneVisitOutpost.setEditable(false);
		txtpnMessagePaneVisitOutpost.setText("Message pane");
		txtpnMessagePaneVisitOutpost.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneVisitOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePaneVisitOutpost.setBounds(303, 264, 283, 93);
		visitOutpost.add(txtpnMessagePaneVisitOutpost);
		
		JButton btnBackVisitOutpost = new JButton("Back");
		btnBackVisitOutpost.setForeground(Color.WHITE);
		btnBackVisitOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackVisitOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackVisitOutpost.setBackground(new Color(25, 25, 112));
		btnBackVisitOutpost.setBounds(303, 367, 283, 36);
		visitOutpost.add(btnBackVisitOutpost);
		
		JButton btnViewShop = new JButton("View Shop");
		btnViewShop.setForeground(Color.WHITE);
		btnViewShop.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShop.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShop.setBackground(new Color(25, 25, 112));
		btnViewShop.setBounds(0, 80, 283, 36);
		visitOutpost.add(btnViewShop);
		
		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.setForeground(Color.WHITE);
		btnViewInventory.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewInventory.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewInventory.setBackground(new Color(25, 25, 112));
		btnViewInventory.setBounds(0, 126, 283, 36);
		visitOutpost.add(btnViewInventory);
		
		JPanel shopScreen = new JPanel();
		shopScreen.setLayout(null);
		shopScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(shopScreen, "name_43805666209300");
		
		JComboBox comboBoxShopItemSelection = new JComboBox();
		comboBoxShopItemSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxShopItemSelection.setBounds(60, 80, 222, 36);
		shopScreen.add(comboBoxShopItemSelection);
		
		JLabel lblShopItemSelector = new JLabel("Item:");
		lblShopItemSelector.setForeground(Color.WHITE);
		lblShopItemSelector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblShopItemSelector.setBounds(0, 80, 50, 36);
		shopScreen.add(lblShopItemSelector);
		
		JTextPane txtpnShopItemInfo = new JTextPane();
		txtpnShopItemInfo.setText("Item info");
		txtpnShopItemInfo.setForeground(Color.WHITE);
		txtpnShopItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnShopItemInfo.setBackground(new Color(25, 25, 112));
		txtpnShopItemInfo.setBounds(0, 126, 283, 128);
		shopScreen.add(txtpnShopItemInfo);
		
		JButton btnPurchaseItem = new JButton("Purchase item");
		btnPurchaseItem.setForeground(Color.WHITE);
		btnPurchaseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnPurchaseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnPurchaseItem.setBackground(new Color(25, 25, 112));
		btnPurchaseItem.setBounds(0, 264, 283, 36);
		shopScreen.add(btnPurchaseItem);
		
		JTextPane txtpnMessagePaneShopScreen = new JTextPane();
		txtpnMessagePaneShopScreen.setEditable(false);
		txtpnMessagePaneShopScreen.setText("Message pane");
		txtpnMessagePaneShopScreen.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePaneShopScreen.setBounds(303, 264, 283, 93);
		shopScreen.add(txtpnMessagePaneShopScreen);
		
		JButton btnBackShopScreen = new JButton("Back");
		btnBackShopScreen.setForeground(Color.WHITE);
		btnBackShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackShopScreen.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackShopScreen.setBackground(new Color(25, 25, 112));
		btnBackShopScreen.setBounds(303, 367, 283, 36);
		shopScreen.add(btnBackShopScreen);
		
		JLabel lblShopScreenTitle = new JLabel("Shop");
		lblShopScreenTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShopScreenTitle.setForeground(Color.YELLOW);
		lblShopScreenTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblShopScreenTitle.setBounds(0, 0, 586, 60);
		shopScreen.add(lblShopScreenTitle);
		
		JLabel lblImageShopScreen = new JLabel("");
		lblImageShopScreen.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/STORAGE.PNG")));
		lblImageShopScreen.setVerticalAlignment(SwingConstants.TOP);
		lblImageShopScreen.setBounds(303, 80, 283, 174);
		shopScreen.add(lblImageShopScreen);
		
		JPanel inventoryScreen = new JPanel();
		inventoryScreen.setLayout(null);
		inventoryScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(inventoryScreen, "name_44043117652400");
		
		JTextPane txtpnMessagePaneInventoryScreen = new JTextPane();
		txtpnMessagePaneInventoryScreen.setEditable(false);
		txtpnMessagePaneInventoryScreen.setText("Message pane");
		txtpnMessagePaneInventoryScreen.setForeground(new Color(0, 0, 0));
		txtpnMessagePaneInventoryScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnMessagePaneInventoryScreen.setBounds(303, 264, 283, 93);
		inventoryScreen.add(txtpnMessagePaneInventoryScreen);
		
		JButton btnBackInventoryScreen = new JButton("Back");
		btnBackInventoryScreen.setForeground(Color.WHITE);
		btnBackInventoryScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackInventoryScreen.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackInventoryScreen.setBackground(new Color(25, 25, 112));
		btnBackInventoryScreen.setBounds(303, 367, 283, 36);
		inventoryScreen.add(btnBackInventoryScreen);
		
		JLabel lblInventoryTitle = new JLabel("Inventory");
		lblInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventoryTitle.setForeground(Color.YELLOW);
		lblInventoryTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblInventoryTitle.setBounds(0, 0, 586, 60);
		inventoryScreen.add(lblInventoryTitle);
		
		JLabel lblImageInventoryScreen = new JLabel("");
		lblImageInventoryScreen.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/STORAGE.PNG")));
		lblImageInventoryScreen.setVerticalAlignment(SwingConstants.TOP);
		lblImageInventoryScreen.setBounds(303, 80, 283, 174);
		inventoryScreen.add(lblImageInventoryScreen);
		
		JTree treeInventoryContainers = new JTree();
		treeInventoryContainers.setOpaque(false);
		treeInventoryContainers.setToggleClickCount(1);
		treeInventoryContainers.setForeground(new Color(255, 255, 255));
		treeInventoryContainers.setBackground(new Color(25, 25, 112));
		treeInventoryContainers.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		treeInventoryContainers.setRootVisible(false);
		treeInventoryContainers.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Inventory") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Banana");
						node_1.add(new DefaultMutableTreeNode("Food item"));
						node_1.add(new DefaultMutableTreeNode("Restores 2 Nutrition"));
						node_1.add(new DefaultMutableTreeNode("Quantity: 1"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Coffee");
						node_1.add(new DefaultMutableTreeNode("Food item"));
						node_1.add(new DefaultMutableTreeNode("Restores 2 Nutrition and 1 Energy"));
						node_1.add(new DefaultMutableTreeNode("Quantity: 1"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Egg");
						node_1.add(new DefaultMutableTreeNode("Food item"));
						node_1.add(new DefaultMutableTreeNode("Restores 3 Nutrition"));
						node_1.add(new DefaultMutableTreeNode("Quantity: 1"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Water");
						node_1.add(new DefaultMutableTreeNode("Food item"));
						node_1.add(new DefaultMutableTreeNode("Restores 2 Nutrition"));
						node_1.add(new DefaultMutableTreeNode("Quantity: 1"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Band-Aid");
						node_1.add(new DefaultMutableTreeNode("Medical item"));
						node_1.add(new DefaultMutableTreeNode("Restores 2 Health"));
						node_1.add(new DefaultMutableTreeNode("Quantity: 1"));
					add(node_1);
				}
			}
		));
		DefaultTreeCellRenderer newRenderer = new DefaultTreeCellRenderer();
		newRenderer.setBorderSelectionColor(new Color(50, 205, 50));
		newRenderer.setLeafIcon(null);
		newRenderer.setOpenIcon(null);
		newRenderer.setClosedIcon(null);
		newRenderer.setBackgroundNonSelectionColor(new Color(25, 25, 112));
		newRenderer.setBackgroundSelectionColor(new Color(25, 25, 112));
		newRenderer.setTextNonSelectionColor(Color.WHITE);
		newRenderer.setTextSelectionColor(Color.WHITE);
		treeInventoryContainers.setCellRenderer(newRenderer);
		treeInventoryContainers.setBounds(0, 80, 285, 323);
		inventoryScreen.add(treeInventoryContainers);
		
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
