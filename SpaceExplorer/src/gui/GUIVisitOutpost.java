package gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import main.Consumable;
import main.GameEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIVisitOutpost extends JPanel {

	SpaceMessagePane messagePane;
	
	private GameEnvironment gameEnvironment;
	
	private GUIWindow guiWindow;
	/**
	 * Create the panel.
	 */
	public GUIVisitOutpost(GUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Outpost");
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/OUTPOST.png")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		super.add(lblImage);
		
		messagePane = new SpaceMessagePane();
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
								node.add(new DefaultMutableTreeNode(item.getDescription()));
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
