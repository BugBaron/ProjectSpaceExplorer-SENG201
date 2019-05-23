package gui;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.CardLayout;

import main.GameEnvironment;
import main.CrewMemberTypes.CrewMember;

import java.util.ArrayList;

/**
 * A container to put all frames for running the game. Uses a card layout to do this.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class GUIWindow {

	/*
	 * These variables have default visibility as their attributes are all
	 * adjusted and edited by each other
	 */
	/** The frame which contains all the components of the GUI. */
	JFrame frame;
	/** The layout which the frame uses. */
	CardLayout layout;
	/** The contents of the message pane. */
	ArrayList<String> messagePaneContents = new ArrayList<String>();
	/** The game environment which the game is running with. */
	GameEnvironment gameEnvironment = new GameEnvironment();
	
	/** The main screen. */
	MainScreen mainScreen = new MainScreen(this);
	/** The screen where crew members can do actions and be viewed. */
	CrewMembersScreen crewMembersScreen = new CrewMembersScreen(this);
	/** The screen where items can be used. */
	UseItemScreen useItemScreen = new UseItemScreen(this);
	/** The screen where the ship can be piloted to a new planet. */
	PilotShipScreen pilotShipScreen = new PilotShipScreen(this);
	/** The screen where the status of the ship can be seen. */
	ShipStatusScreen shipStatusScreen = new ShipStatusScreen(this);
	/** The screen where the shop or inventory screen can be opened. */
	VisitOutpostScreen visitOutpostScreen = new VisitOutpostScreen(this);
	/** The screen where items in the shop can be purchased. */
	ViewShopScreen shopScreen = new ViewShopScreen(this);
	/** The screen where the inventory can be viewed. */
	ViewInventoryScreen inventoryScreen = new ViewInventoryScreen(this);
	/** The screen which displays a message when the game ends. */
	EndGameScreen endGameScreen = new EndGameScreen(this);
	/** The screen which displays the daily score at the end of each day. */
	DailyScoreScreen dailyScoreScreen = new DailyScoreScreen(this);
	
	/** The screen where the game is set up. */
	private SetUpScreen setUpScreen = new SetUpScreen(this);
	/** The screen where the instructions are shown. */
	private InstructionsScreen instructionsScreen = new InstructionsScreen(this);
	
	
	/**
	 * Launch the application.
	 * @param args the arguments to run the application with
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
	 * Updates the information displayed about the selected crew member.
	 */
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
	 * Update the text for the specified message pane.
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
		
		OpeningScreen openingScreen = new OpeningScreen(this);
		
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
		frame.getContentPane().add(instructionsScreen, "Instructions Screen");
		
		layout.show(frame.getContentPane(), "Opening Screen");
		
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Space Explorer: The Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
