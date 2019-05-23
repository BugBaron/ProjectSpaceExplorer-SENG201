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

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
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

/**
 * A screen for the user to create a game.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class SetUpScreen extends JPanel {

	/** The window holding this panel. */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in. */
	private GameEnvironment gameEnvironment;
	
	/** The text field to name the first crew member. */
	private JTextField textFieldCrewName1;
	/** The text field to name the second crew member. */
	private JTextField textFieldCrewName2;
	/** The text field to name the third crew member. */
	private JTextField textFieldCrewName3;
	/** The text field to name the fourth crew member. */
	private JTextField textFieldCrewName4;
	/** The text field to name the ship. */
	private JTextField textFieldShipName;
	
	/** The combo box to choose the type of the first crew member. */
	private JComboBox<String> comboBoxCrewType1;
	/** The combo box to choose the type of the second crew member. */
	private JComboBox<String> comboBoxCrewType2;
	/** The combo box to choose the type of the third crew member. */
	private JComboBox<String> comboBoxCrewType3;
	/** The combo box to choose the type of the fourth crew member. */
	private JComboBox<String> comboBoxCrewType4;
	
	/** The ship that this game is being set up with. */
	private Ship ship;

	
	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public SetUpScreen(GUIWindow tempWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		guiWindow = tempWindow;
		gameEnvironment = tempWindow.gameEnvironment;
		initialize();
	}

	
	/**
	 * Creates a new game with the specified number of days
	 * and the specified number of crew members.
	 * @param maxDays the number of days the game should last
	 * @param numMembers the number of members that should be on the ship
	 */
	private void createGame(int maxDays, int numMembers) {
		String shipName = textFieldShipName.getText();
		if (shipName.length() == 0) {
			ship = new Ship();
		} else {
			ship = new Ship(shipName);
		}
		
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> selectedMembers = new ArrayList<String>();
		ship.getCrewMembers().add(createCrewMember((String) comboBoxCrewType1.getSelectedItem(),
				textFieldCrewName1.getText(), ship, names, selectedMembers));
		ship.getCrewMembers().add(createCrewMember((String) comboBoxCrewType2.getSelectedItem(),
				textFieldCrewName2.getText(), ship, names, selectedMembers));
		if (numMembers >= 3) {
			ship.getCrewMembers().add(createCrewMember((String) comboBoxCrewType3.getSelectedItem(),
					textFieldCrewName3.getText(), ship, names, selectedMembers));
			if (numMembers == 4) {
				ship.getCrewMembers().add(createCrewMember((String) comboBoxCrewType4.getSelectedItem(),
						textFieldCrewName4.getText(), ship, names, selectedMembers));
			}
		}
		
		gameEnvironment.createGame(maxDays, ship);
		guiWindow.mainScreen.lblDayNumber.setText(gameEnvironment.getDayString());
		guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
	}
	
	/**
	 * Updates the text pane to describe the crew member selected in the combo box.
	 * @param comboBox the combo box to get the selection from
	 * @param textPane the text pane to update
	 */
	private void updateTextPane(JComboBox<String> comboBox, JTextPane textPane) {
		CrewMember human = new Human(ship); CrewMember robot = new Robot(ship);
		CrewMember cyborg = new Cyborg(ship); CrewMember alien = new Alien(ship);
		CrewMember lizard = new Lizard(ship); CrewMember unicorn = new Unicorn(ship);
		switch ((String) comboBox.getSelectedItem()) {
		case "Human": textPane.setText("Strength: " + human.getTypeInfo().get("Strength")
				+ "\nWeakness: " + human.getTypeInfo().get("Weakness")); break;
		case "Robot": textPane.setText("Strength: " + robot.getTypeInfo().get("Strength")
				+ "\nWeakness: " + robot.getTypeInfo().get("Weakness")); break;
		case "Cyborg": textPane.setText("Strength: " + cyborg.getTypeInfo().get("Strength")
				+ "\nWeakness: " + cyborg.getTypeInfo().get("Weakness")); break;
		case "Alien": textPane.setText("Strength: " + alien.getTypeInfo().get("Strength")
				+ "\nWeakness: " + alien.getTypeInfo().get("Weakness")); break;
		case "Lizard": textPane.setText("Strength: " + lizard.getTypeInfo().get("Strength")
				+ "\nWeakness: " + lizard.getTypeInfo().get("Weakness")); break;
		case "Unicorn": textPane.setText("Strength: " + unicorn.getTypeInfo().get("Strength")
				+ "\nWeakness: " + unicorn.getTypeInfo().get("Weakness")); break;
		default: textPane.setText("Strength: " + human.getTypeInfo().get("Strength")
				+ "\nWeakness: " + human.getTypeInfo().get("Weakness"));
		}
	}
	
	
	/**
	 * Creates a crew member according to the specified parameters.
	 * @param type the type of crew member to create
	 * @param name the name of the crew member to create
	 * @param tempShip the ship which the crew member should be created for
	 * @param names a list of names of all previous crew members
	 * @param selectedMembers a list of types of all previous crew members
	 * @return the new crew member
	 */
	private CrewMember createCrewMember(String type, String name, Ship tempShip, ArrayList<String> names, ArrayList<String> selectedMembers) {
		CrewMember crewMember;
		selectedMembers.add(type);
		
		String newName = name;
		Integer j = 2;
		while (names.contains(newName)) {
			if (!name.isEmpty()) {
				newName = name + " (" + j + ")";
				j++;
			}
			else if (Collections.frequency(selectedMembers, type) > 1)  {
				Integer number = Collections.frequency(selectedMembers, type);
				newName = Integer.toString(number);
			}
			else {
				break;
			}
		}
		names.add(newName);
		
		switch (type) {
		case "Human": if (newName.length() == 0) { crewMember = new Human(tempShip); }
			else { crewMember = new Human(tempShip, newName); } break;
		case "Robot": if (newName.length() == 0) { crewMember = new Robot(tempShip); }
			else { crewMember = new Robot(tempShip, newName); } break;
		case "Cyborg": if (newName.length() == 0) { crewMember = new Cyborg(tempShip); }
			else { crewMember = new Cyborg(tempShip, newName); } break;
		case "Alien": if (newName.length() == 0) { crewMember = new Alien(tempShip); }
			else { crewMember = new Alien(tempShip, newName); } break;
		case "Lizard": if (newName.length() == 0) { crewMember = new Lizard(tempShip); }
			else { crewMember = new Lizard(tempShip, newName); } break;
		case "Unicorn": if (newName.length() == 0) { crewMember = new Unicorn(tempShip); }
			else { crewMember = new Unicorn(tempShip, newName); } break;
		default: crewMember = new Human(tempShip);
		}
		return crewMember;
		
	}
	
	
	/**
	 * Initialize the panel contents.
	 */
	private void initialize() {
		SpaceLabel lblMissionLength = new SpaceLabel("How many days is the mission?");
		lblMissionLength.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissionLength.setFont(new Font("MS Gothic", Font.PLAIN, 18));
		lblMissionLength.setBounds(0, 0, 283, 36);
		add(lblMissionLength);
		
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
		add(sliderMissionLength);
		
		SpaceLabel lblHowManyCrew = new SpaceLabel("Number of crew members?");
		lblHowManyCrew.setBounds(303, 0, 283, 36);
		lblMissionLength.setFont(new Font("MS Gothic", Font.PLAIN, 18));
		add(lblHowManyCrew);
		
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
		add(slider);
		
		SpaceLabel lblCrewMember = new SpaceLabel("Crew Member 1:");
		lblCrewMember.setBounds(0, 93, 145, 30);
		add(lblCrewMember);
		
		comboBoxCrewType1 = new JComboBox<String>();
		comboBoxCrewType1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewType1.setBounds(145, 93, 145, 25);
		add(comboBoxCrewType1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane.setBackground(new Color(25, 25, 112));
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane.setBounds(303, 93, 283, 50);
		add(textPane);
		
		textFieldCrewName1 = new JTextField();
		textFieldCrewName1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textFieldCrewName1.setBounds(145, 118, 145, 25);
		add(textFieldCrewName1);
		textFieldCrewName1.setColumns(10);
		
		SpaceLabel lblCrewMember1 = new SpaceLabel("Crew Member 2:");
		lblCrewMember1.setBounds(0, 157, 145, 30);
		add(lblCrewMember1);
		
		comboBoxCrewType2 = new JComboBox<String>();
		comboBoxCrewType2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewType2.setBounds(145, 157, 145, 25);
		add(comboBoxCrewType2);
		
		JTextPane textPane1 = new JTextPane();
		textPane1.setEditable(false);
		textPane1.setForeground(Color.WHITE);
		textPane1.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane1.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane1.setBackground(new Color(25, 25, 112));
		textPane1.setBounds(303, 157, 283, 50);
		add(textPane1);

		textFieldCrewName2 = new JTextField();
		textFieldCrewName2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textFieldCrewName2.setBounds(145, 182, 145, 25);
		add(textFieldCrewName2);
		textFieldCrewName2.setColumns(10);
		
		SpaceLabel lblCrewMember2 = new SpaceLabel("Crew Member 3:");
		lblCrewMember2.setBounds(0, 218, 145, 30);
		add(lblCrewMember2);
		
		comboBoxCrewType3 = new JComboBox<String>();
		comboBoxCrewType3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewType3.setBounds(145, 218, 145, 25);
		add(comboBoxCrewType3);
		
		JTextPane textPane2 = new JTextPane();
		textPane2.setEditable(false);
		textPane2.setForeground(Color.WHITE);
		textPane2.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane2.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane2.setBackground(new Color(25, 25, 112));
		textPane2.setBounds(303, 218, 283, 50);
		add(textPane2);

		textFieldCrewName3 = new JTextField();
		textFieldCrewName3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textFieldCrewName3.setBounds(145, 243, 145, 25);
		add(textFieldCrewName3);
		textFieldCrewName3.setColumns(10);
		
		SpaceLabel lblCrewMember3 = new SpaceLabel("Crew Member 4:");
		lblCrewMember3.setBounds(0, 285, 145, 30);
		add(lblCrewMember3);
		
		comboBoxCrewType4 = new JComboBox<String>();
		comboBoxCrewType4.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxCrewType4.setEnabled(false);
		comboBoxCrewType4.setBounds(145, 285, 145, 25);
		add(comboBoxCrewType4);
		
		JTextPane textPane3 = new JTextPane();
		textPane3.setEditable(false);
		textPane3.setForeground(Color.WHITE);
		textPane3.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		textPane3.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane3.setBackground(new Color(25, 25, 112));
		textPane3.setBounds(303, 285, 283, 50);
		add(textPane3);

		textFieldCrewName4 = new JTextField();
		textFieldCrewName4.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textFieldCrewName4.setEnabled(false);
		textFieldCrewName4.setBounds(145, 310, 145, 20);
		add(textFieldCrewName4);
		textFieldCrewName4.setColumns(10);
		
		SpaceLabel lblShipName = new SpaceLabel("Ship Name:");
		lblShipName.setBackground(new Color(25, 25, 112));
		lblShipName.setBounds(0, 370, 125, 30);
		add(lblShipName);
		
		textFieldShipName = new JTextField();
		textFieldShipName.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textFieldShipName.setBounds(145, 370, 145, 25);
		add(textFieldShipName);
		textFieldShipName.setColumns(10);
		
		SpaceButton btnStartGame = new SpaceButton("Start game");
		btnStartGame.setBounds(303, 367, 283, 36);
		add(btnStartGame);
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				if (value < 4) {
					comboBoxCrewType4.setEnabled(false);
					textFieldCrewName4.setEnabled(false);
				} else {
					comboBoxCrewType4.setEnabled(true);
					textFieldCrewName4.setEnabled(true);
				}
				if (value < 3) {
					comboBoxCrewType3.setEnabled(false);
					textFieldCrewName3.setEnabled(false);
				} else {
					comboBoxCrewType3.setEnabled(true);
					textFieldCrewName3.setEnabled(true);
				}
			}
		});
		
		comboBoxCrewType1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateTextPane(comboBoxCrewType1, textPane);
			}
		});
		
		comboBoxCrewType2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateTextPane(comboBoxCrewType2, textPane1);
			}
		});
		
		comboBoxCrewType3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateTextPane(comboBoxCrewType3, textPane2);
			}
		});
		
		comboBoxCrewType4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				updateTextPane(comboBoxCrewType4, textPane3);
			}
		});
		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createGame(sliderMissionLength.getValue(), slider.getValue());
			}
		});
		
		comboBoxCrewType1.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		comboBoxCrewType2.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		comboBoxCrewType3.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		comboBoxCrewType4.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Human", "Robot", "Cyborg", "Alien", "Lizard", "Unicorn"}));
		
		comboBoxCrewType1.setSelectedItem("Robot");
		comboBoxCrewType2.setSelectedItem("Robot");
		comboBoxCrewType3.setSelectedItem("Robot");
		comboBoxCrewType4.setSelectedItem("Robot");
		comboBoxCrewType1.setSelectedItem("Human");
		comboBoxCrewType2.setSelectedItem("Human");
		comboBoxCrewType3.setSelectedItem("Human");
		comboBoxCrewType4.setSelectedItem("Human");
	}
}
