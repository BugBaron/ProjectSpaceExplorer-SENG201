package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.GameEnvironment;

public class GUIShipStatusScreen extends JPanel {
	
	SpaceMessagePane messagePane;
	JTextPane txtpnShipStatus;
	
	private GUIWindow guiWindow;
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GUIShipStatusScreen(GUIWindow guiWindow) {
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
	public void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Ship Status");
		lblTitle.setBounds(0, 0, 586, 60);
		this.add(lblTitle);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		this.add(lblImage);
		
		messagePane = new SpaceMessagePane();
		messagePane.setEditable(false);
		messagePane.setText("Message pane");
		messagePane.setForeground(new Color(0, 0, 128));
		messagePane.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		messagePane.setBounds(303, 264, 283, 93);
		this.add(messagePane);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFocusable(false);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBack.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBack.setBackground(new Color(25, 25, 112));
		btnBack.setBounds(303, 367, 283, 36);
		this.add(btnBack);
		
		txtpnShipStatus = new JTextPane();
		txtpnShipStatus.setEditable(false);
		txtpnShipStatus.setText("Ship Status");
		txtpnShipStatus.setForeground(Color.WHITE);
		txtpnShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnShipStatus.setBackground(new Color(25, 25, 112));
		txtpnShipStatus.setBounds(0, 80, 283, 174);
		this.add(txtpnShipStatus);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
			}
		});
	}
}
