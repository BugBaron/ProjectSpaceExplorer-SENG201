package gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIOpeningScreen extends JPanel{

	private GUIWindow guiWindow;
	
	
	/**
	 * Creates the panel
	 * @param guiWindow the window to create this panel for
	 */
	public GUIOpeningScreen(GUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		initialize();
	}

	/**
	 * Initialize the panel contents
	 */
	private void initialize() {
		
		SpaceButton btnCreateGame = new SpaceButton("Create New Game");
		btnCreateGame.setBounds(0, 300, 283, 36);
		super.add(btnCreateGame);
		
		SpaceButton btnExit = new SpaceButton("Exit");
		btnExit.setBounds(303, 300, 283, 36);
		super.add(btnExit);
		
		JLabel lblTitle = new JLabel("SPACE EXPLORER:");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 58));
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setBounds(0, 0, 586, 196);
		super.add(lblTitle);
		
		SpaceTitle lblSubTitle = new SpaceTitle("THE GAME");
		lblSubTitle.setBounds(185, 146, 216, 89);
		super.add(lblSubTitle);
		
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
