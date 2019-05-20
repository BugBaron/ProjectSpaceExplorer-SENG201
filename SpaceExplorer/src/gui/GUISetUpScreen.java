package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import main.GameEnvironment;

import javax.swing.JTextField;

public class GUISetUpScreen extends JPanel {

	
	private NewGUIWindow guiWindow;
	private GameEnvironment gameEnvironment;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the application.
	 */
	public GUISetUpScreen() {
		super();
		super.setBackground(new Color(25, 25, 112));
		super.setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		SpaceLabel lblMissionLength = new SpaceLabel("How many days is the mission?");
		lblMissionLength.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissionLength.setBounds(113, 11, 373, 30);
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
		sliderMissionLength.setBackground(new Color(0, 0, 128));
		sliderMissionLength.setBounds(188, 44, 200, 36);
		this.add(sliderMissionLength);
		
		SpaceLabel lblHowManyCrew = new SpaceLabel("How many crew members do you have?");
		lblHowManyCrew.setBounds(126, 91, 379, 19);
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
		slider.setBackground(new Color(0, 0, 128));
		slider.setBounds(188, 110, 200, 30);
		this.add(slider);
		
		SpaceLabel lblCrewMember = new SpaceLabel("Crew Member 1:");
		lblCrewMember.setBounds(23, 149, 145, 30);
		this.add(lblCrewMember);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(0, 0, 128));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox.setBounds(188, 153, 125, 22);
		this.add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane.setBackground(new Color(0, 0, 128));
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane.setBounds(323, 153, 236, 50);
		this.add(textPane);
		
		SpaceLabel lblCrewMember_1 = new SpaceLabel("Crew Member 2:");
		lblCrewMember_1.setBounds(23, 213, 145, 30);
		this.add(lblCrewMember_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setForeground(Color.WHITE);
		comboBox_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_1.setBackground(new Color(0, 0, 128));
		comboBox_1.setBounds(188, 217, 125, 22);
		this.add(comboBox_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane_1.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_1.setBackground(new Color(0, 0, 128));
		textPane_1.setBounds(323, 217, 236, 50);
		this.add(textPane_1);
		
		SpaceLabel lblCrewMember_2 = new SpaceLabel("Crew Member 3:");
		lblCrewMember_2.setBounds(23, 274, 145, 30);
		this.add(lblCrewMember_2);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setForeground(Color.WHITE);
		comboBox_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_2.setBackground(new Color(0, 0, 128));
		comboBox_2.setBounds(188, 278, 125, 22);
		this.add(comboBox_2);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setForeground(Color.WHITE);
		textPane_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane_2.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_2.setBackground(new Color(0, 0, 128));
		textPane_2.setBounds(323, 278, 236, 50);
		this.add(textPane_2);
		
		SpaceLabel lblCrewMember_3 = new SpaceLabel("Crew Member 4:");
		lblCrewMember_3.setBounds(23, 341, 145, 30);
		this.add(lblCrewMember_3);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setForeground(Color.WHITE);
		comboBox_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_3.setBackground(new Color(0, 0, 128));
		comboBox_3.setBounds(188, 345, 125, 22);
		this.add(comboBox_3);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane_3.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_3.setBackground(new Color(0, 0, 128));
		textPane_3.setBounds(323, 345, 236, 50);
		this.add(textPane_3);
		
		SpaceLabel lblShipName = new SpaceLabel("Ship Name:");
		lblShipName.setBackground(new Color(0, 0, 128));
		lblShipName.setBounds(188, 403, 125, 30);
		this.add(lblShipName);
		
		textField = new JTextField();
		textField.setBounds(330, 412, 96, 20);
		this.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 185, 125, 20);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 247, 125, 20);
		this.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(188, 309, 125, 20);
		this.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(188, 375, 125, 20);
		this.add(textField_4);
		textField_4.setColumns(10);
	}
}
