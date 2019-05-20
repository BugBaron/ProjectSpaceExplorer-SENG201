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

import main.GameEnvironment;
import main.CrewMemberTypes.CrewMember;

public class GUICrewMembersScreen implements GUIScreen {

	JPanel panel;
	SpaceMessagePane messagePane;
	JComboBox<CrewMember> crewMemberSelection;
	JTextPane crewMemberInfo;
	
	private NewGUIWindow guiWindow;
	private GameEnvironment gameEnvironment;
	private ArrayList<String> messagePaneContents;
	private SpaceButton btnUseItem;
	private SpaceButton btnSleep;
	private SpaceButton btnRepairShip;
	private SpaceButton btnSearchPlanet;
	private SpaceButton btnPilotShip;

	/**
	 * Create the application.
	 */
	public GUICrewMembersScreen(NewGUIWindow guiWindow) {
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		initialize();
	}
	
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setLayout(null);
		

		SpaceTitle lblTitle = new SpaceTitle("Crew Members");
		lblTitle.setBounds(0, 0, 586, 60);
		panel.add(lblTitle);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		panel.add(messagePane);
		
		btnUseItem = new SpaceButton("Use food/medical supplies");
		btnUseItem.setBounds(0, 126, 283, 36);
		panel.add(btnUseItem);
		
		btnSleep = new SpaceButton("Sleep");
		btnSleep.setBounds(0, 172, 283, 36);
		panel.add(btnSleep);
		
		btnRepairShip = new SpaceButton("Repair ship shields");
		btnRepairShip.setBounds(0, 218, 283, 36);
		panel.add(btnRepairShip);
		
		btnSearchPlanet = new SpaceButton("Search planet");
		btnSearchPlanet.setBounds(0, 264, 283, 36);
		panel.add(btnSearchPlanet);
		
		btnPilotShip = new SpaceButton("Pilot ship");
		btnPilotShip.setBounds(0, 310, 283, 36);
		panel.add(btnPilotShip);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		panel.add(btnBack);
		
		SpaceLabel lblCrewSelector = new SpaceLabel("Crew Member:");
		lblCrewSelector.setBounds(0, 80, 126, 36);
		panel.add(lblCrewSelector);
		
		crewMemberSelection = new JComboBox<CrewMember>();
		crewMemberSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		crewMemberSelection.setRenderer(new BasicComboBoxRenderer() {
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
		crewMemberSelection.setBounds(135, 80, 441, 36);
		panel.add(crewMemberSelection);
		
		JTextPane txtpnCrewMemberInfo = new JTextPane();
		txtpnCrewMemberInfo.setEditable(false);
		txtpnCrewMemberInfo.setText("Crew member info");
		txtpnCrewMemberInfo.setForeground(Color.WHITE);
		txtpnCrewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		txtpnCrewMemberInfo.setBackground(new Color(25, 25, 112));
		txtpnCrewMemberInfo.setBounds(303, 125, 283, 128);
		panel.add(txtpnCrewMemberInfo);
	}

}
