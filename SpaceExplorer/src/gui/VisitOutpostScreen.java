package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;
import main.Consumable;
import main.GameEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A screen to either view the shop or the inventory of the ship.
 * @author Daniel Harris and Rebekah McKinnon
 *
 */
public class VisitOutpostScreen extends JPanel {

	/** 
	 * A message pane to display important information. It must be public to
	 * allow it to be updated.
	 */
	SpaceMessagePane messagePane;
	
	/** The window holding this panel. */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in. */
	private GameEnvironment gameEnvironment;

	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public VisitOutpostScreen(GUIWindow tempWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		guiWindow = tempWindow;
		gameEnvironment = tempWindow.gameEnvironment;
		initialize();
	}
	
	
	/**
	 * Initialize the panel contents.
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Outpost");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/OUTPOST.png")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		add(lblImage);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		SpaceButton btnViewShop = new SpaceButton("View Shop");
		btnViewShop.setBounds(0, 80, 283, 36);
		add(btnViewShop);
		
		SpaceButton btnViewInventory = new SpaceButton("View Inventory");
		btnViewInventory.setBounds(0, 126, 283, 36);
		add(btnViewInventory);
	
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
			}
		});
		
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.inventoryScreen.treeInventoryContainers.setModel(new DefaultTreeModel(
					new DefaultMutableTreeNode("Inventory") {{
						DefaultMutableTreeNode node;
						for (Consumable item : gameEnvironment.getInventory().getKeys()) {
							node = new DefaultMutableTreeNode(item.getName());
							node.add(new DefaultMutableTreeNode(item.getClassification() + " item"));
							if (item.getName() == "Coffee") {
								String[] desc = item.getDescription().split("\n");
								node.add(new DefaultMutableTreeNode(desc[0]));
								node.add(new DefaultMutableTreeNode(desc[1]));
							} else {
								node.add(new DefaultMutableTreeNode(item.getDescription()));
							}
							node.add(new DefaultMutableTreeNode("Quantity: " + gameEnvironment.getInventory().get(item)));
							add(node);
						}
						
					}}
				));
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Inventory Screen");
				guiWindow.updatePane();
			}
		});
		
		btnViewShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.shopScreen.updateShopScreen();
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Shop Screen");
			}
		});
	}
}
