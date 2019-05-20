package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

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
import main.InOutHandler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIVisitOutpost extends JPanel {

	
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;
	private InOutHandler inOut;
	/**
	 * Create the panel.
	 */
	public GUIVisitOutpost(NewGUIWindow guiWindow) {
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
		SpaceTitle lblTitle = new SpaceTitle("Outpost");
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		SpaceLabel lblImage = new SpaceLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/OUTPOST.png")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		super.add(lblImage);
		
		SpaceMessagePane messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		super.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		super.add(btnBack);
		
		SpaceButton btnViewShop = new SpaceButton("View Shop");
		btnViewShop.setBounds(0, 80, 283, 36);
		super.add(btnViewShop);
		
		SpaceButton btnViewInventory = new SpaceButton("View Inventory");
		btnViewInventory.setBounds(0, 126, 283, 36);
		super.add(btnViewInventory);
	
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Set Up");
				guiWindow.updatePane();
			}
		});
		
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Inventory");
				guiWindow.updatePane();
			}
		});
		
		btnViewShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Shop");
			}
		});
	}
}
