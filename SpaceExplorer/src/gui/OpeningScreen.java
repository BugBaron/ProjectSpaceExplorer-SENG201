package gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceTitle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A screen that is shown when the game is first opened.
 * @author Daniel Harris and Rebbekah McKinnon
 *
 */
public class OpeningScreen extends JPanel {

	/** The window holding this panel. */
	private GUIWindow guiWindow;
	
	
	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public OpeningScreen(GUIWindow tempWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		guiWindow = tempWindow;
		initialize();
	}
	

	/**
	 * Initialize the panel contents.
	 */
	private void initialize() {
		
		SpaceButton btnCreateGame = new SpaceButton("Create New Game");
		btnCreateGame.setBounds(0, 300, 283, 36);
		add(btnCreateGame);
		
		SpaceButton btnExit = new SpaceButton("Exit");
		btnExit.setBounds(303, 300, 283, 36);
		add(btnExit);
		
		JLabel lblTitle = new JLabel("SPACE EXPLORER:");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 58));
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setBounds(0, 0, 586, 196);
		add(lblTitle);
		
		SpaceTitle lblSubTitle = new SpaceTitle("THE GAME");
		lblSubTitle.setBounds(185, 146, 216, 89);
		add(lblSubTitle);
		
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Set Up Screen");
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
