package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIOpeningScreen extends JPanel{

	private NewGUIWindow guiWindow;
	
	/**
	 * Create the application.
	 */
	public GUIOpeningScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		SpaceButton btnCreateGame = new SpaceButton("Create New Game");
		btnCreateGame.setBounds(173, 305, 304, 70);
		super.add(btnCreateGame);
		
		SpaceButton btnExit = new SpaceButton("Exit");
		btnExit.setBounds(173, 386, 304, 70);
		super.add(btnExit);
		
		JLabel lblTitle = new JLabel("SPACE EXPLORER:");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 58));
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setBounds(31, 11, 596, 196);
		super.add(lblTitle);
		
		SpaceTitle lblSubTitle = new SpaceTitle("THE GAME");
		lblSubTitle.setBounds(210, 146, 216, 89);
		super.add(lblSubTitle);
		
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Set Up");
				guiWindow.updatePane();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
