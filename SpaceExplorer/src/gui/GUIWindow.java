package gui;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.CardLayout;

import main.GameEnvironment;
import main.CrewMemberTypes.CrewMember;

import java.util.ArrayList;

public class GUIWindow {

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
		if (messagePaneContents.size() > 0) {
			text = messagePaneContents.get(0);
			for (int i = 1; i < messagePaneContents.size(); i++) {
				text = text + "\n" + messagePaneContents.get(i);
			}
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
		
		GUIOpeningScreen openingScreen = new GUIOpeningScreen(this);
		
		frame.getContentPane().add(openingScreen, "Opening Screen");
		frame.getContentPane().add(setUpScreen, "Set Up Screen");
		frame.getContentPane().add(mainScreen, "Main Screen");
		frame.getContentPane().add(crewMembersScreen, "Crew Members");
		frame.getContentPane().add(useItemScreen, "Use Item");
		frame.getContentPane().add(pilotShipScreen, "Pilot Ship");
		frame.getContentPane().add(shipStatusScreen, "Ship Status");
		frame.getContentPane().add(visitOutpostScreen, "Visit Outpost");
		frame.getContentPane().add(shopScreen, "Shop Screen");
		frame.getContentPane().add(inventoryScreen, "Inventory Screen");
		frame.getContentPane().add(endGameScreen, "End Game Screen");
		frame.getContentPane().add(dailyScoreScreen, "Daily Score");
		
		layout.show(frame.getContentPane(), "Opening Screen");
		
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
