package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;
import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

/**
 * A screen to display crew members and to complete actions for each one.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class CrewMembersScreen extends JPanel {

	/* 
	 * These variables have default visibility so they can be adjusted and/or 
	 * used to update other widgets 
	 */
	/** A message pane to display important information. */
	SpaceMessagePane messagePane;
	/** A combo box to select which crew member to view. */
	JComboBox<CrewMember> crewMemberSelection;
	/** A text pane to display information about the selected crew member. */
	JTextPane crewMemberInfo;
	
	/** The window holding this panel. */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in. */
	private GameEnvironment gameEnvironment;
	/** The contents of the message pane. */
	private ArrayList<String> messagePaneContents;
	/** The object which is handling the input and output of the game environment. */
	private InOutHandler inOut;
	
	/** A button to go to the use item screen. */
	private SpaceButton btnUseItem;
	/** A button to make the selected crew member sleep. */
	private SpaceButton btnSleep;
	/** A button to make the selected crew member repair the ship. */
	private SpaceButton btnRepairShip;
	/** A button to make the selected crew member search the planet. */
	private SpaceButton btnSearchPlanet;
	/** A button to go to the pilot ship screen. */
	private SpaceButton btnPilotShip;

	
	/**
	 * Creates the pane.
	 * @param tempWindow the window to create this panel for
	 */
	public CrewMembersScreen(GUIWindow tempWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		guiWindow = tempWindow;
		gameEnvironment = tempWindow.gameEnvironment;
		messagePaneContents = tempWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
	/**
	 * Updates the buttons and related crew member info on the crew member screen.
	 */
	public void updateCrewMembers() {
		Object item = crewMemberSelection.getSelectedItem();
		if (item instanceof CrewMember) {
			CrewMember crewMember = (CrewMember) item;
			int actions = crewMember.getActions();
			btnUseItem.setEnabled(actions > 0 && gameEnvironment.getInventory().size() > 0);
			btnSleep.setEnabled(actions > 0);
			btnRepairShip.setEnabled(actions > 0);
			btnSearchPlanet.setEnabled(actions > 0);
			btnPilotShip.setEnabled(actions > 0 && gameEnvironment.getAvailableMembers().size() > 1);
			guiWindow.updateCrewMemberInfo();
		}
	}

	
	/**
	 * Initialize the panel contents.
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Crew Members");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		btnUseItem = new SpaceButton("Use food/medical supplies");
		btnUseItem.setBounds(0, 126, 283, 36);
		add(btnUseItem);
		
		btnSleep = new SpaceButton("Sleep");
		btnSleep.setBounds(0, 172, 283, 36);
		add(btnSleep);
		
		btnRepairShip = new SpaceButton("Repair ship shields");
		btnRepairShip.setBounds(0, 218, 283, 36);
		add(btnRepairShip);
		
		btnSearchPlanet = new SpaceButton("Search planet");
		btnSearchPlanet.setBounds(0, 264, 283, 36);
		add(btnSearchPlanet);
		
		btnPilotShip = new SpaceButton("Pilot ship");
		btnPilotShip.setBounds(0, 310, 283, 36);
		add(btnPilotShip);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		SpaceLabel lblCrewSelector = new SpaceLabel("Crew Member:");
		lblCrewSelector.setBounds(0, 80, 126, 36);
		add(lblCrewSelector);
		
		crewMemberSelection = new JComboBox<CrewMember>();
		crewMemberSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		crewMemberSelection.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) value;
					String text = crewMember.getName() + ", " + crewMember.getTypeInfo().get("Type");
					if (crewMember.getActions() == 0) text = text + ", No actions remaining";
					setText(text);
				}
				
				return this;
			}
		});
		crewMemberSelection.setBounds(135, 80, 441, 36);
		add(crewMemberSelection);
		
		crewMemberInfo = new JTextPane();
		crewMemberInfo.setEditable(false);
		crewMemberInfo.setText("Crew member info");
		crewMemberInfo.setForeground(Color.WHITE);
		crewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMemberInfo.setBackground(new Color(25, 25, 112));
		crewMemberInfo.setBounds(303, 125, 283, 128);
		add(crewMemberInfo);
		
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Use Item");
				guiWindow.updatePane();
				
				ArrayList<Consumable> keys = gameEnvironment.getInventory().getKeys();
				Vector<Consumable> items = new Vector<Consumable>(keys);
				guiWindow.useItemScreen.itemSelection.setModel(
						new DefaultComboBoxModel<Consumable>(items));
				guiWindow.useItemScreen.updateItemInfo();
			}
		});
		
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.sleep((CrewMember) crewMemberSelection.getSelectedItem());
				messagePaneContents.add((String) inOut.getOutput());
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
				guiWindow.updatePane();
			}
		});
		
		btnRepairShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.repairShip((CrewMember) crewMemberSelection.getSelectedItem());
				messagePaneContents.add((String) inOut.getOutput());
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
				guiWindow.updatePane();
			}
		});
		
		btnSearchPlanet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = gameEnvironment.searchPlanet(
						(CrewMember) crewMemberSelection.getSelectedItem());
				Object output = inOut.getOutput();
				while (output != null) {
					messagePaneContents.add((String) output);
					output = inOut.getOutput();
				}
				if (!result) {
					guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
					guiWindow.updatePane();
				} else { // If the game has now ended
					guiWindow.endGameScreen.runEndDay(true);
					guiWindow.layout.show(guiWindow.frame.getContentPane(), "End Game Screen");
				}
			}
		});
		
		btnPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<CrewMember> availableMembers = new Vector<CrewMember>(
						gameEnvironment.getAvailableMembers());
				availableMembers.remove((CrewMember) crewMemberSelection.getSelectedItem());
				guiWindow.pilotShipScreen.crewMember2Selection.setModel(
						new DefaultComboBoxModel<CrewMember>(availableMembers));
				
				Object item = guiWindow.pilotShipScreen.crewMember2Selection.getSelectedItem();
				if (item instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) item;
					String tempCrewMemberInfo = crewMember.toString()
							+ "\nActions: " + crewMember.getActions();
					tempCrewMemberInfo = tempCrewMemberInfo.substring(
							tempCrewMemberInfo.indexOf("\n") + 1);
					guiWindow.pilotShipScreen.crewMember2Info.setText(tempCrewMemberInfo);
				}
				
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Pilot Ship");
				guiWindow.updatePane();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
			}
		});
		
		crewMemberSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateCrewMembers();
			}
		});
	}

}
