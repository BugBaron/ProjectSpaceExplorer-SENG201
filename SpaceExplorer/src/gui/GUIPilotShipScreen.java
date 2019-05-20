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
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class GUIPilotShipScreen extends JPanel {

	JTextPane crewMemberInfo;
	SpaceMessagePane messagePane;
	
	private JTextPane crewMember2Info;
	private JComboBox<CrewMember> crewMember2Selection;
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;
	private InOutHandler inOut;
	
	/**
	 * Create the panel.
	 */
	public GUIPilotShipScreen(NewGUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Pilot Ship");
		lblTitle.setBounds(0, 0, 586, 60);
		this.add(lblTitle);
		
		SpaceButton btnConfirmPilotShip = new SpaceButton("Pilot Ship");
		btnConfirmPilotShip.setBounds(0, 264, 283, 36);
		this.add(btnConfirmPilotShip);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		this.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		this.add(btnBack);
		
		SpaceLabel lblCrewMember2Selector = new SpaceLabel("Second crew member:");
		lblCrewMember2Selector.setBounds(0, 80, 200, 36);
		this.add(lblCrewMember2Selector);
		
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
		this.add(crewMember2Selection);
		
		crewMember2Info = new JTextPane();
		crewMember2Info.setEditable(false);
		crewMember2Info.setBackground(new Color(25, 25, 112));
		crewMember2Info.setForeground(new Color(255, 255, 255));
		crewMember2Info.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMember2Info.setText("Second crew member info");
		crewMember2Info.setBounds(0, 126, 283, 128);
		this.add(crewMember2Info);
		
		crewMemberInfo = new JTextPane();
		crewMemberInfo.setEditable(false);
		crewMemberInfo.setText("Crew member info");
		crewMemberInfo.setForeground(Color.WHITE);
		crewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMemberInfo.setBackground(new Color(25, 25, 112));
		crewMemberInfo.setBounds(303, 126, 283, 128);
		this.add(crewMemberInfo);
		
		btnConfirmPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.pilotShip((CrewMember) guiWindow.crewMembersScreen.crewMemberSelection.getSelectedItem(), 
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
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
			}
		});
	}
}
