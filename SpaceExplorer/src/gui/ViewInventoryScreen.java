package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultTreeCellRenderer;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A screen to view the inventory of the ship.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class ViewInventoryScreen extends JPanel {

	/* 
	 * These variables have default visibility so they can be adjusted and/or
	 * used to update other widgets
	 */
	/** A message pane to display important information. */
	SpaceMessagePane messagePane;
	/** A tree to view the items in the inventory, their attributes and their quantity. */
	JTree treeInventoryContainers;
	
	/** The window holding this panel. */
	private GUIWindow guiWindow;
	
	
	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public ViewInventoryScreen(GUIWindow tempWindow) {
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
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		SpaceTitle lblTitle = new SpaceTitle("Inventory");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		SpaceLabel lblImage = new SpaceLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/STORAGE.PNG")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		add(lblImage);
		
		treeInventoryContainers = new JTree();
		treeInventoryContainers.setOpaque(false);
		treeInventoryContainers.setToggleClickCount(1);
		treeInventoryContainers.setForeground(new Color(255, 255, 255));
		treeInventoryContainers.setBackground(new Color(25, 25, 112));
		treeInventoryContainers.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		treeInventoryContainers.setRootVisible(false);
		DefaultTreeCellRenderer newRenderer = new DefaultTreeCellRenderer();
		newRenderer.setBorderSelectionColor(new Color(25, 25, 112));
		newRenderer.setLeafIcon(null);
		newRenderer.setOpenIcon(null);
		newRenderer.setClosedIcon(null);
		newRenderer.setBackgroundNonSelectionColor(new Color(25, 25, 112));
		newRenderer.setBackgroundSelectionColor(new Color(25, 25, 112));
		newRenderer.setTextNonSelectionColor(Color.WHITE);
		newRenderer.setTextSelectionColor(Color.WHITE);
		treeInventoryContainers.setCellRenderer(newRenderer);
		treeInventoryContainers.setBounds(0, 80, 285, 323);
		add(treeInventoryContainers);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Visit Outpost");
				guiWindow.updatePane();
			}
		});
	}
}
