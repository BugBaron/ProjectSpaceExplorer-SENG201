package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class GUIShipStatusScreen extends JPanel {
	
	SpaceMessagePane messagePane;
	JTextPane txtpnShipStatus;
	
	private GUIWindow guiWindow;

	/**
	 * Creates the panel
	 * @param guiWindow the window to create this panel for
	 */
	public GUIShipStatusScreen(GUIWindow guiWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		initialize();
	}

	/**
	 * Initialize the panel contents
	 */
	public void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Ship Status");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		add(lblImage);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		txtpnShipStatus = new JTextPane();
		txtpnShipStatus.setEditable(false);
		txtpnShipStatus.setText("Ship Status");
		txtpnShipStatus.setForeground(Color.WHITE);
		txtpnShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnShipStatus.setBackground(new Color(25, 25, 112));
		txtpnShipStatus.setBounds(0, 80, 283, 174);
		add(txtpnShipStatus);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
			}
		});
	}
}
