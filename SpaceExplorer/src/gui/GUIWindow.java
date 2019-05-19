package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.DefaultCaret;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.Inventory;
import main.CrewMemberTypes.CrewMember;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GUIWindow {

	private JFrame frame;
	private CardLayout layout;
	private ArrayList<String> messagePaneContents = new ArrayList<String>();
	private InOutHandler inOut = new InOutHandler();
	private GameEnvironment gameEnvironment = new GameEnvironment(inOut);

	
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
		gameEnvironment.createGame();
		initialize();
	}
	
	/**
	 * Update the text for the specified message pane
	 */
	public void updatePane(JTextPane msgPane) {
		String text = "";
		for (int i=0; i < messagePaneContents.size() && i < 3; i++) {
			text = messagePaneContents.get(messagePaneContents.size() - i - 1) + "\n" + text;
		}
		if (messagePaneContents.size() > 3) {
			text = "...\n" + text;
		}
		msgPane.setText(text);
		msgPane.setCaretPosition(msgPane.getDocument().getLength());
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.getContentPane().setLayout(new CardLayout(20, 20));
		layout = (CardLayout) frame.getContentPane().getLayout();
		
		JPanel mainScreen = new JPanel();
		mainScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(mainScreen, "Main Screen");
		mainScreen.setLayout(null);

		JPanel crewMembers = new JPanel();
		crewMembers.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(crewMembers, "Crew Members");
		crewMembers.setLayout(null);

		JPanel useItem = new JPanel();
		useItem.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(useItem, "Use Item");
		useItem.setLayout(null);

		JPanel pilotShip = new JPanel();
		pilotShip.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(pilotShip, "Pilot Ship");
		pilotShip.setLayout(null);

		JPanel shipStatus = new JPanel();
		shipStatus.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(shipStatus, "Ship Status");
		shipStatus.setLayout(null);

		JPanel visitOutpost = new JPanel();
		visitOutpost.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(visitOutpost, "Visit Outpost");
		visitOutpost.setLayout(null);

		/*JPanel shopScreen = new JPanel();
		shopScreen.setLayout(null);
		shopScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(shopScreen, "Shop Screen");*/
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(panel, "Shop Screen");
		panel.setLayout(null);

		JPanel inventoryScreen = new JPanel();
		inventoryScreen.setLayout(null);
		inventoryScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(inventoryScreen, "Inventory Screen");
		
		// Main screen
		
		JLabel lblControlPanelTitle = new JLabel("Control Panel");
		lblControlPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlPanelTitle.setForeground(Color.YELLOW);
		lblControlPanelTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblControlPanelTitle.setBounds(0, 0, 586, 60);
		mainScreen.add(lblControlPanelTitle);
		
		JLabel lblDayNumber = new JLabel(gameEnvironment.getDayString());
		lblDayNumber.setForeground(Color.WHITE);
		lblDayNumber.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblDayNumber.setBounds(0, 80, 283, 36);
		mainScreen.add(lblDayNumber);
		
		JButton btnViewCrewMember = new JButton("View Crew members");
		btnViewCrewMember.setFocusable(false);
		btnViewCrewMember.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewCrewMember.setForeground(Color.WHITE);
		btnViewCrewMember.setBackground(new Color(25, 25, 112));
		btnViewCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewCrewMember.setBounds(0, 126, 283, 36);
		mainScreen.add(btnViewCrewMember);
		
		JButton btnViewShipStatus = new JButton("View ship status");
		btnViewShipStatus.setFocusable(false);
		btnViewShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShipStatus.setForeground(Color.WHITE);
		btnViewShipStatus.setBackground(new Color(25, 25, 112));
		btnViewShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShipStatus.setBounds(0, 172, 283, 36);
		mainScreen.add(btnViewShipStatus);
		
		JButton btnVisitSpaceOutpost = new JButton("Visit space outpost");
		btnVisitSpaceOutpost.setFocusable(false);
		btnVisitSpaceOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnVisitSpaceOutpost.setForeground(new Color(255, 255, 255));
		btnVisitSpaceOutpost.setBackground(new Color(25, 25, 112));
		btnVisitSpaceOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnVisitSpaceOutpost.setBounds(0, 218, 283, 36);
		mainScreen.add(btnVisitSpaceOutpost);
		
		JButton btnContinue = new JButton("Continue to next day");
		btnContinue.setFocusable(false);
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
		txtpnMessagePane.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePane.setBounds(303, 264, 283, 93);
		mainScreen.add(txtpnMessagePane);
		
		JLabel lblImageControlPanel = new JLabel("");
		lblImageControlPanel.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageControlPanel.setForeground(Color.WHITE);
		lblImageControlPanel.setBounds(303, 80, 283, 174);
		mainScreen.add(lblImageControlPanel);
		
		// Crew members pane
		
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
		txtpnMessagePaneCrewMembers.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneCrewMembers.setBounds(303, 264, 283, 93);
		crewMembers.add(txtpnMessagePaneCrewMembers);
		
		JButton btnUseItem = new JButton("Use food/medical supplies");
		btnUseItem.setFocusable(false);
		btnUseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnUseItem.setForeground(Color.WHITE);
		btnUseItem.setBackground(new Color(25, 25, 112));
		btnUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnUseItem.setBounds(0, 126, 283, 36);
		crewMembers.add(btnUseItem);
		
		JButton btnSleep = new JButton("Sleep");
		btnSleep.setFocusable(false);
		btnSleep.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnSleep.setForeground(Color.WHITE);
		btnSleep.setBackground(new Color(25, 25, 112));
		btnSleep.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnSleep.setBounds(0, 172, 283, 36);
		crewMembers.add(btnSleep);
		
		JButton btnRepairShip = new JButton("Repair ship shields");
		btnRepairShip.setFocusable(false);
		btnRepairShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnRepairShip.setForeground(Color.WHITE);
		btnRepairShip.setBackground(new Color(25, 25, 112));
		btnRepairShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnRepairShip.setBounds(0, 218, 283, 36);
		crewMembers.add(btnRepairShip);
		
		JButton btnSearchPlanet = new JButton("Search planet");
		btnSearchPlanet.setFocusable(false);
		btnSearchPlanet.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnSearchPlanet.setForeground(Color.WHITE);
		btnSearchPlanet.setBackground(new Color(25, 25, 112));
		btnSearchPlanet.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnSearchPlanet.setBounds(0, 264, 283, 36);
		crewMembers.add(btnSearchPlanet);
		
		JButton btnPilotShip = new JButton("Pilot ship");
		btnPilotShip.setFocusable(false);
		btnPilotShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnPilotShip.setForeground(Color.WHITE);
		btnPilotShip.setBackground(new Color(25, 25, 112));
		btnPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnPilotShip.setBounds(0, 310, 283, 36);
		crewMembers.add(btnPilotShip);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFocusable(false);
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
		
		JComboBox<CrewMember> comboBoxCrewMemberSelection = new JComboBox<CrewMember>();
		comboBoxCrewMemberSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewMemberSelection.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) value;
					setText(crewMember.getName() + ", " + crewMember.getTypeInfo().get("Type"));
				}
				
				return this;	
			}
		});
		comboBoxCrewMemberSelection.setBounds(135, 80, 441, 36);
		crewMembers.add(comboBoxCrewMemberSelection);
		
		JTextPane txtpnCrewMemberInfo = new JTextPane();
		txtpnCrewMemberInfo.setEditable(false);
		txtpnCrewMemberInfo.setText("Crew member info");
		txtpnCrewMemberInfo.setForeground(Color.WHITE);
		txtpnCrewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		txtpnCrewMemberInfo.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfo.setBounds(303, 125, 283, 128);
		crewMembers.add(txtpnCrewMemberInfo);
		
		// Use Item Screen
		
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
		txtpnMessagePaneUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneUseItem.setBounds(303, 264, 283, 93);
		useItem.add(txtpnMessagePaneUseItem);
		
		JButton btnBackUseItem = new JButton("Back");
		btnBackUseItem.setFocusable(false);
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
		
		JComboBox<Consumable> comboBoxItemSelection = new JComboBox<Consumable>();
		comboBoxItemSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxItemSelection.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof Consumable) {
					Consumable item = (Consumable) value;
					setText(item.getName() + ", Quantity: " + gameEnvironment.getInventory().get(item));
				}
				
				return this;	
			}
		});
		comboBoxItemSelection.setBounds(60, 80, 526, 36);
		useItem.add(comboBoxItemSelection);
		
		JButton btnConfirmUseItem = new JButton("Use item");
		btnConfirmUseItem.setFocusable(false);
		btnConfirmUseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnConfirmUseItem.setForeground(Color.WHITE);
		btnConfirmUseItem.setBackground(new Color(25, 25, 112));
		btnConfirmUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnConfirmUseItem.setBounds(0, 264, 283, 36);
		useItem.add(btnConfirmUseItem);
		
		JTextPane txtpnCrewMemberInfoUseItem = new JTextPane();
		txtpnCrewMemberInfoUseItem.setText("Crew member info");
		txtpnCrewMemberInfoUseItem.setForeground(Color.WHITE);
		txtpnCrewMemberInfoUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		txtpnCrewMemberInfoUseItem.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfoUseItem.setBounds(303, 126, 283, 138);
		useItem.add(txtpnCrewMemberInfoUseItem);
		
		JTextPane txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(0, 126, 283, 128);
		useItem.add(txtpnItemInfo);
		
		// Pilot Ship Screen
		
		JLabel lblPilotShipTitle = new JLabel("Pilot Ship");
		lblPilotShipTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPilotShipTitle.setForeground(Color.YELLOW);
		lblPilotShipTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblPilotShipTitle.setBounds(0, 0, 586, 60);
		pilotShip.add(lblPilotShipTitle);
		
		JButton btnConfirmPilotShip = new JButton("Pilot Ship");
		btnConfirmPilotShip.setFocusable(false);
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
		txtpnMessagePanePilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePanePilotShip.setBounds(303, 264, 283, 93);
		pilotShip.add(txtpnMessagePanePilotShip);
		
		JButton btnBackPilotShip = new JButton("Back");
		btnBackPilotShip.setFocusable(false);
		btnBackPilotShip.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackPilotShip.setForeground(Color.WHITE);
		btnBackPilotShip.setBackground(new Color(25, 25, 112));
		btnBackPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackPilotShip.setBounds(303, 367, 283, 36);
		pilotShip.add(btnBackPilotShip);
		
		JLabel lblCrewMember2Selector = new JLabel("Second crew member:");
		lblCrewMember2Selector.setForeground(Color.WHITE);
		lblCrewMember2Selector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewMember2Selector.setBounds(0, 80, 200, 36);
		pilotShip.add(lblCrewMember2Selector);
		
		JComboBox<CrewMember> comboBoxCrewMember2Selection = new JComboBox<CrewMember>();
		comboBoxCrewMember2Selection.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) value;
					setText(crewMember.getName() + ", " + crewMember.getTypeInfo().get("Type"));
				}
				
				return this;	
			}
		});
		comboBoxCrewMember2Selection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewMember2Selection.setBounds(200, 80, 379, 36);
		pilotShip.add(comboBoxCrewMember2Selection);
		
		JTextPane txtpnSecondCrewMember = new JTextPane();
		txtpnSecondCrewMember.setEditable(false);
		txtpnSecondCrewMember.setBackground(new Color(25, 25, 112));
		txtpnSecondCrewMember.setForeground(new Color(255, 255, 255));
		txtpnSecondCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		txtpnSecondCrewMember.setText("Second crew member info");
		txtpnSecondCrewMember.setBounds(0, 126, 283, 128);
		pilotShip.add(txtpnSecondCrewMember);
		
		JTextPane txtpnCrewMemberInfoPilotShip = new JTextPane();
		txtpnCrewMemberInfoPilotShip.setEditable(false);
		txtpnCrewMemberInfoPilotShip.setText("Crew member info");
		txtpnCrewMemberInfoPilotShip.setForeground(Color.WHITE);
		txtpnCrewMemberInfoPilotShip.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		txtpnCrewMemberInfoPilotShip.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfoPilotShip.setBounds(303, 126, 283, 128);
		pilotShip.add(txtpnCrewMemberInfoPilotShip);
		
		// Ship Status Screen
		
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
		txtpnMessagePaneShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneShipStatus.setBounds(303, 264, 283, 93);
		shipStatus.add(txtpnMessagePaneShipStatus);
		
		JButton btnBackShipStatus = new JButton("Back");
		btnBackShipStatus.setFocusable(false);
		btnBackShipStatus.setForeground(Color.WHITE);
		btnBackShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackShipStatus.setBackground(new Color(25, 25, 112));
		btnBackShipStatus.setBounds(303, 367, 283, 36);
		shipStatus.add(btnBackShipStatus);
		
		JTextPane txtpnShipStatus = new JTextPane();
		txtpnShipStatus.setEditable(false);
		txtpnShipStatus.setText("Ship Status");
		txtpnShipStatus.setForeground(Color.WHITE);
		txtpnShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnShipStatus.setBackground(new Color(25, 25, 112));
		txtpnShipStatus.setBounds(0, 80, 283, 174);
		shipStatus.add(txtpnShipStatus);
		
		// Visit Outpost Screen
		
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
		txtpnMessagePaneVisitOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneVisitOutpost.setBounds(303, 264, 283, 93);
		visitOutpost.add(txtpnMessagePaneVisitOutpost);
		
		JButton btnBackVisitOutpost = new JButton("Back");
		btnBackVisitOutpost.setFocusable(false);
		btnBackVisitOutpost.setForeground(Color.WHITE);
		btnBackVisitOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackVisitOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackVisitOutpost.setBackground(new Color(25, 25, 112));
		btnBackVisitOutpost.setBounds(303, 367, 283, 36);
		visitOutpost.add(btnBackVisitOutpost);
		
		JButton btnViewShop = new JButton("View Shop");
		btnViewShop.setFocusable(false);
		btnViewShop.setForeground(Color.WHITE);
		btnViewShop.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShop.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShop.setBackground(new Color(25, 25, 112));
		btnViewShop.setBounds(0, 80, 283, 36);
		visitOutpost.add(btnViewShop);
		
		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.setFocusable(false);
		btnViewInventory.setForeground(Color.WHITE);
		btnViewInventory.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewInventory.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewInventory.setBackground(new Color(25, 25, 112));
		btnViewInventory.setBounds(0, 126, 283, 36);
		visitOutpost.add(btnViewInventory);
		
		// Shop Screen
		
		JLabel lblShopTitle = new JLabel("Shop");
		lblShopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShopTitle.setForeground(Color.YELLOW);
		lblShopTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblShopTitle.setBounds(0, 0, 586, 60);
		panel.add(lblShopTitle);
		
		JTextPane txtpnItemInfoShopScreen = new JTextPane();
		txtpnItemInfoShopScreen.setText("Item info");
		txtpnItemInfoShopScreen.setForeground(Color.WHITE);
		txtpnItemInfoShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfoShopScreen.setEditable(false);
		txtpnItemInfoShopScreen.setBackground(new Color(25, 25, 112));
		txtpnItemInfoShopScreen.setBounds(303, 80, 283, 128);
		panel.add(txtpnItemInfoShopScreen);
		
		JTextPane txtpnMessagePaneShopScreen = new JTextPane();
		txtpnMessagePaneShopScreen.setText("Message pane");
		txtpnMessagePaneShopScreen.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneShopScreen.setEditable(false);
		txtpnMessagePaneShopScreen.setBounds(303, 264, 283, 93);
		panel.add(txtpnMessagePaneShopScreen);
		
		JButton btnBackShopScreen = new JButton("Back");
		btnBackShopScreen.setFocusable(false);
		btnBackShopScreen.setForeground(Color.WHITE);
		btnBackShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackShopScreen.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackShopScreen.setBackground(new Color(25, 25, 112));
		btnBackShopScreen.setBounds(303, 367, 283, 36);
		panel.add(btnBackShopScreen);
		
		JButton btnPurchaseItem = new JButton("Purchase item");
		btnPurchaseItem.setFocusable(false);
		btnPurchaseItem.setForeground(Color.WHITE);
		btnPurchaseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnPurchaseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnPurchaseItem.setBackground(new Color(25, 25, 112));
		btnPurchaseItem.setBounds(303, 218, 283, 36);
		panel.add(btnPurchaseItem);
		
		JList<Consumable> listShopItems = new JList<Consumable>();
		listShopItems.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof Consumable) {
					Consumable item = (Consumable) value;
					String name = item.getName();
					setText(item.getName() + "," + " ".repeat(17 - name.length()) + "$" + 
							item.getPrice() + ", " + gameEnvironment.getShop().get(item));
				}
				
				return this;	
			}
		});
		listShopItems.setBackground(new Color(25, 25, 112));
		listShopItems.setForeground(new Color(255, 255, 255));
		listShopItems.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		listShopItems.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		listShopItems.setVisibleRowCount(9);
		listShopItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listShopItems.setBounds(0, 80, 283, 277);
		panel.add(listShopItems);	
		
		JLabel lblMoney = new JLabel("Money: $100");
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblMoney.setBounds(0, 367, 285, 36);
		panel.add(lblMoney);
		
		//TODO remove
		/*
		JComboBox<String> comboBoxShopItemSelection = new JComboBox<String>();
		comboBoxShopItemSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxShopItemSelection.setBounds(60, 80, 222, 36);
		shopScreen.add(comboBoxShopItemSelection);
		
		JLabel lblShopItemSelector = new JLabel("Item:");
		lblShopItemSelector.setForeground(Color.WHITE);
		lblShopItemSelector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblShopItemSelector.setBounds(0, 80, 50, 36);
		shopScreen.add(lblShopItemSelector);
		
		JTextPane txtpnShopItemInfo = new JTextPane();
		txtpnShopItemInfo.setEditable(false);
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
		txtpnMessagePaneShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 15));
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
		*/
		// Inventory Screen
		
		JTextPane txtpnMessagePaneInventoryScreen = new JTextPane();
		txtpnMessagePaneInventoryScreen.setEditable(false);
		txtpnMessagePaneInventoryScreen.setText("Message pane");
		txtpnMessagePaneInventoryScreen.setForeground(new Color(0, 0, 0));
		txtpnMessagePaneInventoryScreen.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneInventoryScreen.setBounds(303, 264, 283, 93);
		inventoryScreen.add(txtpnMessagePaneInventoryScreen);
		
		JButton btnBackInventoryScreen = new JButton("Back");
		btnBackInventoryScreen.setFocusable(false);
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
		DefaultTreeCellRenderer newRenderer = new DefaultTreeCellRenderer();
		newRenderer.setBorderSelectionColor(new Color(25, 25, 112));
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
		
		final class Methods {
			Methods() {};
			
			/** Shows the Main Screen and updates visible widgets */
			public final void gotoMainScreen() {
				layout.show(frame.getContentPane(), "Main Screen");
				updatePane(txtpnMessagePane);
				lblDayNumber.setText(gameEnvironment.getDayString());
			}
			
			/** Shows the Crew Member screen and updates visible widgets */
			public final void gotoCrewMember() {
				layout.show(frame.getContentPane(), "Crew Members");
				updatePane(txtpnMessagePaneCrewMembers);
				
				ArrayList<CrewMember> crewMembers = gameEnvironment.getCrewMembers();
				CrewMember[] newMembers = new CrewMember[crewMembers.size()];
				for (int i = 0; i < crewMembers.size(); i++) {
					newMembers[i] = crewMembers.get(i);
				}
				comboBoxCrewMemberSelection.setModel(new DefaultComboBoxModel<CrewMember>(newMembers));
			}
			
			/** Updates the visible widgets of the Crew Member screen */
			public final void updateCrewMembers() {
				Object item = comboBoxCrewMemberSelection.getSelectedItem();
				if (item instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) item;
					int actions = crewMember.getActions();
					btnUseItem.setEnabled(actions > 0 && gameEnvironment.getInventory().size() > 0);
					btnSleep.setEnabled(actions > 0);
					btnRepairShip.setEnabled(actions > 0);
					btnSearchPlanet.setEnabled(actions > 0);
					btnPilotShip.setEnabled(actions > 0 && gameEnvironment.getAvailableMembers().size() > 1);
					String crewMemberInfo = crewMember.toString() + "\nActions: " + crewMember.getActions();
					crewMemberInfo = crewMemberInfo.substring(crewMemberInfo.indexOf("\n") + 1);
					txtpnCrewMemberInfo.setText(crewMemberInfo);
					txtpnCrewMemberInfoUseItem.setText(crewMemberInfo);
					txtpnCrewMemberInfoPilotShip.setText(crewMemberInfo);
				}
			}
			
			/** Updates the visible widgets of the Shop Screen */
			public final void updateShopScreen() {
				lblMoney.setText("Money: $" + gameEnvironment.getMoney());
				Consumable initialSelection = listShopItems.getSelectedValue();
				DefaultListModel<Consumable> list = new DefaultListModel<Consumable>();
				list.addAll(gameEnvironment.getShop().getKeys());
				listShopItems.setModel(list);
				if (gameEnvironment.getShop().getKeys().contains(initialSelection)) {
					listShopItems.setSelectedValue(initialSelection, false);
				}
				updateShopItem();
			}
			
			/** Updates the item info widget of the Shop Screen */
			public final void updateShopItem() {
				Object selection = listShopItems.getSelectedValue();
				String text = "Item info";
				if (selection instanceof Consumable) {
					Consumable item = (Consumable) selection;
					text = item.getName() + "\n" + 
							item.getDescription() + "\n" +
							"Price: $" + item.getPrice() + "\n" + 
							"Quantity: " + gameEnvironment.getShop().get(item);
					if (gameEnvironment.getMoney() < item.getPrice()) {
						btnPurchaseItem.setEnabled(false);
						text = text + "\nYou do not have enough money to purchase this item";
					} else {
						btnPurchaseItem.setEnabled(true);
					}
				} else {
					btnPurchaseItem.setEnabled(false);
				}
				txtpnItemInfoShopScreen.setText(text);
			}
		}
		
		final Methods METHODS = new Methods();
		
		
		
		btnViewCrewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoCrewMember();
				METHODS.updateCrewMembers();
			}
		});
		
		btnViewShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpnShipStatus.setText(gameEnvironment.getShipString());
				layout.show(frame.getContentPane(), "Ship Status");
				updatePane(txtpnMessagePaneShipStatus);
			}
		});

		btnVisitSpaceOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(frame.getContentPane(), "Visit Outpost");
				updatePane(txtpnMessagePaneVisitOutpost);
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
		
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(frame.getContentPane(), "Use Item");
				updatePane(txtpnMessagePaneUseItem);
				
				ArrayList<Consumable> keys = gameEnvironment.getInventory().getKeys();
				Consumable[] items = new Consumable[keys.size()];
				for (int i = 0; i < keys.size(); i++) {
					items[i] = keys.get(i);
				}
				comboBoxItemSelection.setModel(new DefaultComboBoxModel<Consumable>(items));
				
				Object item = comboBoxItemSelection.getSelectedItem();
				if (item instanceof Consumable) {
					Consumable newItem = (Consumable) item;
					String itemInfo = newItem.getName() + "\n" + newItem.getClassification() + " item\n" + 
							newItem.getDescription() + "\nQuantity: " + gameEnvironment.getInventory().get(newItem);
					txtpnItemInfo.setText(itemInfo);
				}
			}
		});
		
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.sleep((CrewMember) comboBoxCrewMemberSelection.getSelectedItem());
				messagePaneContents.add((String) inOut.getOutput());
				METHODS.gotoMainScreen();
			}
		});
		
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.repairShip((CrewMember) comboBoxCrewMemberSelection.getSelectedItem());
				Object output = inOut.getOutput();
				while (output != null) {
					messagePaneContents.add((String) output);
					output = inOut.getOutput();
				}
				METHODS.gotoMainScreen();
			}
		});
		
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.searchPlanet((CrewMember) comboBoxCrewMemberSelection.getSelectedItem());
				Object output = inOut.getOutput();
				while (output != null) {
					messagePaneContents.add((String) output);
					output = inOut.getOutput();
				}
				METHODS.gotoMainScreen();
			}
		});
		
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<CrewMember> availableMembers = new Vector(gameEnvironment.getAvailableMembers());
				availableMembers.remove((CrewMember) comboBoxCrewMemberSelection.getSelectedItem());
				comboBoxCrewMember2Selection.setModel(new DefaultComboBoxModel<CrewMember>(availableMembers));
				Object item = comboBoxCrewMember2Selection.getSelectedItem();
				if (item instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) item;
					String crewMemberInfo = crewMember.toString() + "\nActions: " + crewMember.getActions();
					crewMemberInfo = crewMemberInfo.substring(crewMemberInfo.indexOf("\n") + 1);
					txtpnSecondCrewMember.setText(crewMemberInfo);
				}
				
				layout.show(frame.getContentPane(), "Pilot Ship");
				updatePane(txtpnMessagePanePilotShip);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoMainScreen();
			}
		});
		
		comboBoxCrewMemberSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				METHODS.updateCrewMembers();
			}
		});
		
		comboBoxItemSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object item = comboBoxItemSelection.getSelectedItem();
				if (item instanceof Consumable) {
					Consumable newItem = (Consumable) item;
					String itemInfo = newItem.getName() + "\n" + newItem.getClassification() + " item\n" + 
							newItem.getDescription() + "\nQuantity: " + gameEnvironment.getInventory().get(newItem);
					txtpnItemInfo.setText(itemInfo);
				}
			}
		});
		
		btnConfirmUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.useItem((CrewMember) comboBoxCrewMemberSelection.getSelectedItem(), 
						(Consumable) comboBoxItemSelection.getSelectedItem());
				messagePaneContents.add((String) inOut.getOutput());
				METHODS.gotoMainScreen();
			}
		});
		
		btnBackUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoCrewMember();
			}
		});
		
		btnConfirmPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.pilotShip((CrewMember) comboBoxCrewMemberSelection.getSelectedItem(), 
						(CrewMember) comboBoxCrewMember2Selection.getSelectedItem());
				Object output = inOut.getOutput();
				while (output != null) {
					messagePaneContents.add((String) output);
					output = inOut.getOutput();
				}
				METHODS.gotoMainScreen();
			}
		});
		
		btnBackPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoCrewMember();
			}
		});
		
		btnBackShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoMainScreen();
			}
		});
		
		btnViewShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.updateShopScreen();
				layout.show(frame.getContentPane(), "Shop Screen");
				updatePane(txtpnMessagePaneShopScreen);
			}
		});
		
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				treeInventoryContainers.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Inventory") {{
							DefaultMutableTreeNode node;
							for (Consumable item : gameEnvironment.getInventory().getKeys()) {
								node = new DefaultMutableTreeNode(item.getName());
								node.add(new DefaultMutableTreeNode(item.getClassification() + " item"));
								node.add(new DefaultMutableTreeNode(item.getDescription()));
								node.add(new DefaultMutableTreeNode("Quantity: " + gameEnvironment.getInventory().get(item)));
								add(node);
							}
							
						}}
					));
				layout.show(frame.getContentPane(), "Inventory Screen");
				updatePane(txtpnMessagePaneInventoryScreen);
			}
		});
		
		btnBackVisitOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoMainScreen();
			}
		});
		
		btnPurchaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.purchaseItem(listShopItems.getSelectedValue());
				METHODS.updateShopScreen();
				messagePaneContents.add((String) inOut.getOutput());
				updatePane(txtpnMessagePaneShopScreen);
			}
		});
		
		btnBackShopScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(frame.getContentPane(), "Visit Outpost");
				updatePane(txtpnMessagePaneVisitOutpost);
			}
		});
		
		listShopItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Object item = listShopItems.getSelectedValue();
				if (item instanceof Consumable) {
					METHODS.updateShopItem();
				}
			}
		});
		
		btnBackInventoryScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(frame.getContentPane(), "Visit Outpost");
				updatePane(txtpnMessagePaneVisitOutpost);
			}
		});
		
		
		
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
