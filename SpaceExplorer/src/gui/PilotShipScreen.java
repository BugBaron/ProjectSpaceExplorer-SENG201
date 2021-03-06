package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

/**
 * A screen to allow the user to pilot their ship to a new planet.
 * @author Daniel Harris and Rebekah McKinnon
 *
 */
public class PilotShipScreen extends JPanel {

	/* 
	 * These variables have default visibility so they can be adjusted and/or 
	 * used to update other widgets 
	 */
	/** A message pane to display important information. */
	SpaceMessagePane messagePane;
	/** A text pane to display information about the selected crew member. */
	JTextPane crewMemberInfo;
	/** A combo box to select which other crew member to pilot the ship with. */
	JComboBox<CrewMember> crewMember2Selection;
	/** A text pane to display information about the other selected crew member. */
	JTextPane crewMember2Info;
	
	/** The window holding this panel. */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in. */
	private GameEnvironment gameEnvironment;
	/** The contents of the message pane. */
	private ArrayList<String> messagePaneContents;
	/** The object which is handling the input and output of the game environment. */
	private InOutHandler inOut;
	
	
	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public PilotShipScreen(GUIWindow tempWindow) {
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
	 * Initialize the panel contents.
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Pilot Ship");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		SpaceButton btnConfirmPilotShip = new SpaceButton("Pilot Ship");
		btnConfirmPilotShip.setBounds(0, 264, 283, 36);
		add(btnConfirmPilotShip);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		SpaceLabel lblCrewMember2Selector = new SpaceLabel("Second crew member:");
		lblCrewMember2Selector.setBounds(0, 80, 200, 36);
		add(lblCrewMember2Selector);
		
		crewMember2Selection = new JComboBox<CrewMember>();
		crewMember2Selection.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) value;
					setText(crewMember.getName() + ", " + crewMember.getTypeInfo().get("Type"));
				}
				
				return this;	
			}
		});
		crewMember2Selection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		crewMember2Selection.setBounds(200, 80, 379, 36);
		add(crewMember2Selection);
		
		crewMember2Info = new JTextPane();
		crewMember2Info.setEditable(false);
		crewMember2Info.setBackground(new Color(25, 25, 112));
		crewMember2Info.setForeground(new Color(255, 255, 255));
		crewMember2Info.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMember2Info.setText("Second crew member info");
		crewMember2Info.setBounds(0, 126, 283, 128);
		add(crewMember2Info);
		
		crewMemberInfo = new JTextPane();
		crewMemberInfo.setEditable(false);
		crewMemberInfo.setText("Crew member info");
		crewMemberInfo.setForeground(Color.WHITE);
		crewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMemberInfo.setBackground(new Color(25, 25, 112));
		crewMemberInfo.setBounds(303, 126, 283, 128);
		add(crewMemberInfo);
		
		btnConfirmPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.pilotShip((CrewMember) 
						guiWindow.crewMembersScreen.crewMemberSelection.getSelectedItem(), 
						(CrewMember) crewMember2Selection.getSelectedItem());
				Object output = inOut.getOutput();
				while (output != null) {
					messagePaneContents.add((String) output);
					output = inOut.getOutput();
				}
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
				guiWindow.updatePane();
			}
		});
		
		crewMember2Selection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object item = crewMember2Selection.getSelectedItem();
				if (item instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) item;
					String crewMemberText = crewMember.toString() + "\nActions: " + crewMember.getActions();
					crewMemberText = crewMemberText.substring(crewMemberText.indexOf("\n") + 1);
					crewMember2Info.setText(crewMemberText);
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Crew Members");
			}
		});
	}
}
