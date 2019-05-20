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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class NewGUIWindow {

	JFrame frame;
	CardLayout layout;
	ArrayList<String> messagePaneContents = new ArrayList<String>();
	GameEnvironment gameEnvironment = new GameEnvironment();
	
	GUISetUpScreen setUpScreen = new GUISetUpScreen(this);
	GUIMainScreen mainScreen = new GUIMainScreen(this);
	GUICrewMembersScreen crewMembersScreen = new GUICrewMembersScreen(this);
	GUIUseItemScreen useItemScreen = new GUIUseItemScreen(this);
	GUIPilotShipScreen pilotShipScreen = new GUIPilotShipScreen(this);
	GUIShipStatusScreen shipStatusScreen = new GUIShipStatusScreen(this);
	GUIVisitOutpost visitOutpostScreen = new GUIVisitOutpost(this);
	GUIViewShop shopScreen = new GUIViewShop(this);
	GUIViewInventory inventoryScreen = new GUIViewInventory(this);
	GUIEndGameScreen endGameScreen = new GUIEndGameScreen(this);
	GUIDailyScoreScreen dailyScoreScreen = new GUIDailyScoreScreen(this);
	
	private InOutHandler inOut = gameEnvironment.getInOut();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGUIWindow window = new NewGUIWindow();
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
	public NewGUIWindow() {
		gameEnvironment.createGame();
		initialize();
	}
	
	public void updateCrewMemberInfo() {
		Object item = crewMembersScreen.crewMemberSelection.getSelectedItem();
		if (item instanceof CrewMember) {
			CrewMember crewMember = (CrewMember) item;
			String memberText = crewMember.toString() + "\nActions: " + crewMember.getActions();
			memberText = memberText.substring(memberText.indexOf("\n") + 1);
			crewMembersScreen.crewMemberInfo.setText(memberText);
			useItemScreen.crewMemberInfo.setText(memberText);
			pilotShipScreen.crewMemberInfo.setText(memberText);
		}
	}
	
	/**
	 * Update the text for the specified message pane
	 */
	public void updatePane() {
		String text = "";
		for (int i=0; i < messagePaneContents.size(); i++) {
			text = messagePaneContents.get(i) + "\n" + text;
		}
		mainScreen.messagePane.setText(text);
		crewMembersScreen.messagePane.setText(text);
		useItemScreen.messagePane.setText(text);
		pilotShipScreen.messagePane.setText(text);
		shipStatusScreen.messagePane.setText(text);
		visitOutpostScreen.messagePane.setText(text);
		shopScreen.messagePane.setText(text);
		inventoryScreen.messagePane.setText(text);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		layout = new CardLayout(20, 20);
		frame.getContentPane().setLayout(layout);
		
		frame.getContentPane().add(mainScreen, "Main Screen");
		frame.getContentPane().add(crewMembersScreen, "Crew Members");
		frame.getContentPane().add(useItemScreen, "Use Item");
		frame.getContentPane().add(pilotShipScreen, "Pilot Ship");
		frame.getContentPane().add(shipStatusScreen, "Ship Status");
		frame.getContentPane().add(visitOutpostScreen, "Visit Outpost");
		frame.getContentPane().add(shopScreen, "Shop Screen");
		frame.getContentPane().add(inventoryScreen, "Inventory Screen");
		
		layout.show(frame.getContentPane(), "Main Screen");
		
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
