package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultTreeCellRenderer;

import main.GameEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIViewInventory extends JPanel {

	SpaceMessagePane messagePane;
	JTree treeInventoryContainers;
	
	private GUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;
	/**
	 * Create the panel.
	 */
	public GUIViewInventory(GUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		super.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		super.add(btnBack);
		
		SpaceTitle lblTitle = new SpaceTitle("Inventory");
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		SpaceLabel lblImage = new SpaceLabel("");
		lblImage.setIcon(new ImageIcon(GUIWindow.class.getResource("/images/STORAGE.PNG")));
		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setBounds(303, 80, 283, 174);
		super.add(lblImage);
		
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
		super.add(treeInventoryContainers);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Visit Outpost");
				guiWindow.updatePane();
			}
		});
	}
}
