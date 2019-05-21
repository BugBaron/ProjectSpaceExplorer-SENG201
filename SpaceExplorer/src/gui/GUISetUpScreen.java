package gui;

import java.awt.Color;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.GameEnvironment;
import main.Ship;
import main.CrewMemberTypes.Alien;
import main.CrewMemberTypes.CrewMember;
import main.CrewMemberTypes.Cyborg;
import main.CrewMemberTypes.Human;
import main.CrewMemberTypes.Lizard;
import main.CrewMemberTypes.Robot;
import main.CrewMemberTypes.Unicorn;

import javax.swing.JTextField;

public class GUISetUpScreen extends JPanel {

	
	private GUIWindow guiWindow;
	private GameEnvironment gameEnvironment;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_2;
	private JComboBox<String> comboBox_3;
	
	private Ship ship;

	/**
	 * Creates the panel
	 * @param guiWindow the window to create this panel for
	 */
	public GUISetUpScreen(GUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		super.setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		initialize();
	}

	
	/**
	 * Creates a new game with the specified number of days and the specified 
	 * number of crew members
	 * @param maxDays the number of days the game should last
	 * @param numMembers the number of members that should be on the ship
	 */
	private void createGame(int maxDays, int numMembers) {
		String shipName = textField_4.getText();
		if (shipName.length() == 0) {
			ship = new Ship();
		} else {
			ship = new Ship(shipName);
		}
		
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> selectedMembers = new ArrayList<String>();
		ship.getCrewMembers().add(createCrewMember((String) comboBox.getSelectedItem(), 
				textField.getText(), ship, names, selectedMembers));
		ship.getCrewMembers().add(createCrewMember((String) comboBox_1.getSelectedItem(), 
				textField_1.getText(), ship, names, selectedMembers));
		if (numMembers >= 3) {
			ship.getCrewMembers().add(createCrewMember((String) comboBox_2.getSelectedItem(), 
					textField_2.getText(), ship, names, selectedMembers));
			if (numMembers == 4) {
				ship.getCrewMembers().add(createCrewMember((String) comboBox_3.getSelectedItem(), 
						textField_3.getText(), ship, names, selectedMembers));
			}
		}
		
		gameEnvironment.createGame(maxDays, ship);
		guiWindow.mainScreen.lblDayNumber.setText(gameEnvironment.getDayString());
		guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
	}
	
	
	/**
	 * Creates a crew member according to the specified parameters
	 * @param type the type of crew member to create
	 * @param name the name of the crew member to create
	 * @param ship the ship which the crew member should be created for
	 * @param names a list of names of all previous crew members
	 * @param selectedMembers a list of types of all previous crew members
	 * @return
	 */
	private CrewMember createCrewMember(String type, String name, Ship ship, ArrayList<String> names, ArrayList<String> selectedMembers) {
		CrewMember crewMember;
		selectedMembers.add(type);
		
		String new_name = name;
		Integer j = 2;
		while (names.contains(new_name)) {
			if (!name.isEmpty()) {
				new_name = name + " (" + j + ")";
				j ++;
			}
			else if (Collections.frequency(selectedMembers, type) > 1)  {
				Integer number = Collections.frequency(selectedMembers, type);
				new_name = Integer.toString(number);
			}
			else {
				break;
			}
		}
		names.add(new_name);
		
		switch (type) {
		case "Human": if (new_name.length() == 0) { crewMember = new Human(ship); } 
			else {crewMember = new Human(ship, new_name); } break;
		case "Robot": if (new_name.length() == 0) { crewMember = new Robot(ship); } 
			else {crewMember = new Robot(ship, new_name); } break;
		case "Cyborg": if (new_name.length() == 0) { crewMember = new Cyborg(ship); } 
			else {crewMember = new Cyborg(ship, new_name); } break;
		case "Alien": if (new_name.length() == 0) { crewMember = new Alien(ship); } 
			else {crewMember = new Alien(ship, new_name); } break;
		case "Lizard": if (new_name.length() == 0) { crewMember = new Lizard(ship); } 
			else {crewMember = new Lizard(ship, new_name); } break;
		case "Unicorn": if (new_name.length() == 0) { crewMember = new Unicorn(ship); } 
			else {crewMember = new Unicorn(ship, new_name); } break;
		default: crewMember = new Human(ship);
		}
		return crewMember;
		
	}
	
	
	/**
	 * Initialize the panel contents
	 */
	private void initialize() {
		SpaceLabel lblMissionLength = new SpaceLabel("How many days is the mission?");
		lblMissionLength.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissionLength.setFont(new Font("MS Gothic", Font.PLAIN, 18));
		lblMissionLength.setBounds(0, 0, 283, 36);
		this.add(lblMissionLength);
		
		JSlider sliderMissionLength = new JSlider();
		sliderMissionLength.setMajorTickSpacing(1);
		sliderMissionLength.setValue(7);
		sliderMissionLength.setFont(new Font("MS Gothic", Font.PLAIN, 10));
		sliderMissionLength.setSnapToTicks(true);
		sliderMissionLength.setPaintLabels(true);
		sliderMissionLength.setPaintTicks(true);
		sliderMissionLength.setMinimum(3);
		sliderMissionLength.setMaximum(10);
		sliderMissionLength.setForeground(new Color(255, 255, 255));
		sliderMissionLength.setBackground(new Color(25, 25, 112));
		sliderMissionLength.setBounds(0, 46, 283, 36);
		this.add(sliderMissionLength);
		
		SpaceLabel lblHowManyCrew = new SpaceLabel("Number of crew members?");
		lblHowManyCrew.setBounds(303, 0, 283, 36);
		lblMissionLength.setFont(new Font("MS Gothic", Font.PLAIN, 18));
		this.add(lblHowManyCrew);
		
		JSlider slider = new JSlider();
		slider.setFont(new Font("MS Gothic", Font.PLAIN, 10));
		slider.setMajorTickSpacing(1);
		slider.setValue(3);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinimum(2);
		slider.setMaximum(4);
		slider.setForeground(new Color(255, 255, 255));
		slider.setBackground(new Color(25, 25, 112));
		slider.setBounds(303, 44, 283, 46);
		this.add(slider);
		
		SpaceLabel lblCrewMember = new SpaceLabel("Crew Member 1:");
		lblCrewMember.setBounds(0, 93, 145, 30);
		this.add(lblCrewMember);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox.setBounds(145, 93, 145, 25);
		this.add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane.setBackground(new Color(25, 25, 112));
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane.setBounds(303, 93, 283, 50);
		this.add(textPane);
		
		textField = new JTextField();
		textField.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textField.setBounds(145, 118, 145, 25);
		this.add(textField);
		textField.setColumns(10);
		
		SpaceLabel lblCrewMember_1 = new SpaceLabel("Crew Member 2:");
		lblCrewMember_1.setBounds(0, 157, 145, 30);
		this.add(lblCrewMember_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_1.setBounds(145, 157, 145, 25);
		this.add(comboBox_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane_1.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_1.setBackground(new Color(25, 25, 112));
		textPane_1.setBounds(303, 157, 283, 50);
		this.add(textPane_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textField_1.setBounds(145, 182, 145, 25);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		SpaceLabel lblCrewMember_2 = new SpaceLabel("Crew Member 3:");
		lblCrewMember_2.setBounds(0, 218, 145, 30);
		this.add(lblCrewMember_2);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_2.setBounds(145, 218, 145, 25);
		this.add(comboBox_2);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setForeground(Color.WHITE);
		textPane_2.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane_2.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_2.setBackground(new Color(25, 25, 112));
		textPane_2.setBounds(303, 218, 283, 50);
		this.add(textPane_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textField_2.setBounds(145, 243, 145, 25);
		this.add(textField_2);
		textField_2.setColumns(10);
		
		SpaceLabel lblCrewMember_3 = new SpaceLabel("Crew Member 4:");
		lblCrewMember_3.setBounds(0, 285, 145, 30);
		this.add(lblCrewMember_3);
		
		comboBox_3 = new JComboBox<String>();
		comboBox_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_3.setEnabled(false);
		comboBox_3.setBounds(145, 285, 145, 25);
		this.add(comboBox_3);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane_3.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_3.setBackground(new Color(25, 25, 112));
		textPane_3.setBounds(303, 285, 283, 50);
		this.add(textPane_3);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textField_3.setEnabled(false);
		textField_3.setBounds(145, 310, 145, 20);
		this.add(textField_3);
		textField_3.setColumns(10);
		
		SpaceLabel lblShipName = new SpaceLabel("Ship Name:");
		lblShipName.setBackground(new Color(25, 25, 112));
		lblShipName.setBounds(0, 370, 125, 30);
		this.add(lblShipName);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textField_4.setBounds(145, 370, 145, 25);
		this.add(textField_4);
		textField_4.setColumns(10);
		
		SpaceButton btnStartGame = new SpaceButton("Start game");
		btnStartGame.setBounds(303, 367, 283, 36);
		this.add(btnStartGame);
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				if (value < 4) {
					comboBox_3.setEnabled(false);
					textField_3.setEnabled(false);
				} else {
					comboBox_3.setEnabled(true);
					textField_3.setEnabled(true);
				}
				if (value < 3) {
					comboBox_2.setEnabled(false);
					textField_2.setEnabled(false);
				} else {
					comboBox_2.setEnabled(true);
					textField_2.setEnabled(true);
				}
			}
		});
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				CrewMember human = new Human(ship); CrewMember robot = new Robot(ship);
				CrewMember cyborg = new Cyborg(ship); CrewMember alien = new Alien(ship);
				CrewMember lizard = new Lizard(ship); CrewMember unicorn = new Unicorn(ship);
				switch ((String) comboBox.getSelectedItem()) {
				case "Human": textPane.setText("Strength: " + human.getTypeInfo().get("Strength") + 
						"\nWeakness: " + human.getTypeInfo().get("Weakness")); break;
				case "Robot": textPane.setText("Strength: " + robot.getTypeInfo().get("Strength") + 
						"\nWeakness: " + robot.getTypeInfo().get("Weakness")); break;
				case "Cyborg": textPane.setText("Strength: " + cyborg.getTypeInfo().get("Strength") + 
						"\nWeakness: " + cyborg.getTypeInfo().get("Weakness")); break;
				case "Alien": textPane.setText("Strength: " + alien.getTypeInfo().get("Strength") + 
						"\nWeakness: " + alien.getTypeInfo().get("Weakness")); break;
				case "Lizard": textPane.setText("Strength: " + lizard.getTypeInfo().get("Strength") + 
						"\nWeakness: " + lizard.getTypeInfo().get("Weakness")); break;
				case "Unicorn": textPane.setText("Strength: " + unicorn.getTypeInfo().get("Strength") + 
						"\nWeakness: " + unicorn.getTypeInfo().get("Weakness")); break;
				}
			}
		});
		
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				CrewMember human = new Human(ship); CrewMember robot = new Robot(ship);
				CrewMember cyborg = new Cyborg(ship); CrewMember alien = new Alien(ship);
				CrewMember lizard = new Lizard(ship); CrewMember unicorn = new Unicorn(ship);
				switch ((String) comboBox_1.getSelectedItem()) {
				case "Human": textPane_1.setText("Strength: " + human.getTypeInfo().get("Strength") + 
						"\nWeakness: " + human.getTypeInfo().get("Weakness")); break;
				case "Robot": textPane_1.setText("Strength: " + robot.getTypeInfo().get("Strength") + 
						"\nWeakness: " + robot.getTypeInfo().get("Weakness")); break;
				case "Cyborg": textPane_1.setText("Strength: " + cyborg.getTypeInfo().get("Strength") + 
						"\nWeakness: " + cyborg.getTypeInfo().get("Weakness")); break;
				case "Alien": textPane_1.setText("Strength: " + alien.getTypeInfo().get("Strength") + 
						"\nWeakness: " + alien.getTypeInfo().get("Weakness")); break;
				case "Lizard": textPane_1.setText("Strength: " + lizard.getTypeInfo().get("Strength") + 
						"\nWeakness: " + lizard.getTypeInfo().get("Weakness")); break;
				case "Unicorn": textPane_1.setText("Strength: " + unicorn.getTypeInfo().get("Strength") + 
						"\nWeakness: " + unicorn.getTypeInfo().get("Weakness")); break;
				}
			}
		});
		
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				CrewMember human = new Human(ship); CrewMember robot = new Robot(ship);
				CrewMember cyborg = new Cyborg(ship); CrewMember alien = new Alien(ship);
				CrewMember lizard = new Lizard(ship); CrewMember unicorn = new Unicorn(ship);
				switch ((String) comboBox_2.getSelectedItem()) {
				case "Human": textPane_2.setText("Strength: " + human.getTypeInfo().get("Strength") + 
						"\nWeakness: " + human.getTypeInfo().get("Weakness")); break;
				case "Robot": textPane_2.setText("Strength: " + robot.getTypeInfo().get("Strength") + 
						"\nWeakness: " + robot.getTypeInfo().get("Weakness")); break;
				case "Cyborg": textPane_2.setText("Strength: " + cyborg.getTypeInfo().get("Strength") + 
						"\nWeakness: " + cyborg.getTypeInfo().get("Weakness")); break;
				case "Alien": textPane_2.setText("Strength: " + alien.getTypeInfo().get("Strength") + 
						"\nWeakness: " + alien.getTypeInfo().get("Weakness")); break;
				case "Lizard": textPane_2.setText("Strength: " + lizard.getTypeInfo().get("Strength") + 
						"\nWeakness: " + lizard.getTypeInfo().get("Weakness")); break;
				case "Unicorn": textPane_2.setText("Strength: " + unicorn.getTypeInfo().get("Strength") + 
						"\nWeakness: " + unicorn.getTypeInfo().get("Weakness")); break;
				}
			}
		});
		
		comboBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				CrewMember human = new Human(ship); CrewMember robot = new Robot(ship);
				CrewMember cyborg = new Cyborg(ship); CrewMember alien = new Alien(ship);
				CrewMember lizard = new Lizard(ship); CrewMember unicorn = new Unicorn(ship);
				switch ((String) comboBox_3.getSelectedItem()) {
				case "Human": textPane_3.setText("Strength: " + human.getTypeInfo().get("Strength") + 
						"\nWeakness: " + human.getTypeInfo().get("Weakness")); break;
				case "Robot": textPane_3.setText("Strength: " + robot.getTypeInfo().get("Strength") + 
						"\nWeakness: " + robot.getTypeInfo().get("Weakness")); break;
				case "Cyborg": textPane_3.setText("Strength: " + cyborg.getTypeInfo().get("Strength") + 
						"\nWeakness: " + cyborg.getTypeInfo().get("Weakness")); break;
				case "Alien": textPane_3.setText("Strength: " + alien.getTypeInfo().get("Strength") + 
						"\nWeakness: " + alien.getTypeInfo().get("Weakness")); break;
				case "Lizard": textPane_3.setText("Strength: " + lizard.getTypeInfo().get("Strength") + 
						"\nWeakness: " + lizard.getTypeInfo().get("Weakness")); break;
				case "Unicorn": textPane_3.setText("Strength: " + unicorn.getTypeInfo().get("Strength") + 
						"\nWeakness: " + unicorn.getTypeInfo().get("Weakness")); break;
				}
			}
		});
		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createGame(sliderMissionLength.getValue(), slider.getValue());
			}
		});
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		
		comboBox.setSelectedItem("Robot");
		comboBox_1.setSelectedItem("Robot");
		comboBox_2.setSelectedItem("Robot");
		comboBox_3.setSelectedItem("Robot");
		comboBox.setSelectedItem("Human");
		comboBox_1.setSelectedItem("Human");
		comboBox_2.setSelectedItem("Human");
		comboBox_3.setSelectedItem("Human");
	}
}
