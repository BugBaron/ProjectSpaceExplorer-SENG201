package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class GUISetUpScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISetUpScreen window = new GUISetUpScreen();
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
	public GUISetUpScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		frame.setBackground(new Color(0, 0, 128));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		frame.getContentPane().setLayout(null);
		
		JLabel lblMissionLength = new JLabel("How many days is the mission?");
		lblMissionLength.setHorizontalAlignment(SwingConstants.CENTER);
		lblMissionLength.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblMissionLength.setForeground(new Color(255, 255, 255));
		lblMissionLength.setBounds(113, 11, 373, 30);
		frame.getContentPane().add(lblMissionLength);
		
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
		frame.getContentPane().add(sliderMissionLength);
		
		JLabel lblHowManyCrew = new JLabel("How many crew members do you have?");
		lblHowManyCrew.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblHowManyCrew.setForeground(new Color(255, 255, 255));
		lblHowManyCrew.setBounds(126, 91, 379, 19);
		frame.getContentPane().add(lblHowManyCrew);
		
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
		frame.getContentPane().add(slider);
		
		JLabel lblCrewMember = new JLabel("Crew Member 1:");
		lblCrewMember.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewMember.setForeground(new Color(255, 255, 255));
		lblCrewMember.setBounds(23, 149, 145, 30);
		frame.getContentPane().add(lblCrewMember);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(0, 0, 128));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox.setBounds(188, 153, 125, 22);
		frame.getContentPane().add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane.setBackground(new Color(0, 0, 128));
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane.setBounds(323, 153, 236, 50);
		frame.getContentPane().add(textPane);
		
		JLabel lblCrewMember_1 = new JLabel("Crew Member 2:");
		lblCrewMember_1.setForeground(Color.WHITE);
		lblCrewMember_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewMember_1.setBounds(23, 213, 145, 30);
		frame.getContentPane().add(lblCrewMember_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setForeground(Color.WHITE);
		comboBox_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_1.setBackground(new Color(0, 0, 128));
		comboBox_1.setBounds(188, 217, 125, 22);
		frame.getContentPane().add(comboBox_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane_1.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_1.setBackground(new Color(0, 0, 128));
		textPane_1.setBounds(323, 217, 236, 50);
		frame.getContentPane().add(textPane_1);
		
		JLabel lblCrewMember_2 = new JLabel("Crew Member 3:");
		lblCrewMember_2.setForeground(Color.WHITE);
		lblCrewMember_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewMember_2.setBounds(23, 274, 145, 30);
		frame.getContentPane().add(lblCrewMember_2);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setForeground(Color.WHITE);
		comboBox_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_2.setBackground(new Color(0, 0, 128));
		comboBox_2.setBounds(188, 278, 125, 22);
		frame.getContentPane().add(comboBox_2);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setForeground(Color.WHITE);
		textPane_2.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane_2.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_2.setBackground(new Color(0, 0, 128));
		textPane_2.setBounds(323, 278, 236, 50);
		frame.getContentPane().add(textPane_2);
		
		JLabel lblCrewMember_3 = new JLabel("Crew Member 4:");
		lblCrewMember_3.setForeground(Color.WHITE);
		lblCrewMember_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblCrewMember_3.setBounds(23, 341, 145, 30);
		frame.getContentPane().add(lblCrewMember_3);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setForeground(Color.WHITE);
		comboBox_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBox_3.setBackground(new Color(0, 0, 128));
		comboBox_3.setBounds(188, 345, 125, 22);
		frame.getContentPane().add(comboBox_3);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setForeground(Color.WHITE);
		textPane_3.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		textPane_3.setBorder(new LineBorder(new Color(255, 255, 255)));
		textPane_3.setBackground(new Color(0, 0, 128));
		textPane_3.setBounds(323, 345, 236, 50);
		frame.getContentPane().add(textPane_3);
		
		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setForeground(new Color(255, 255, 255));
		lblShipName.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblShipName.setBackground(new Color(0, 0, 128));
		lblShipName.setBounds(188, 403, 125, 30);
		frame.getContentPane().add(lblShipName);
		
		textField = new JTextField();
		textField.setBounds(330, 412, 96, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 185, 125, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(188, 247, 125, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(188, 309, 125, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(188, 375, 125, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
