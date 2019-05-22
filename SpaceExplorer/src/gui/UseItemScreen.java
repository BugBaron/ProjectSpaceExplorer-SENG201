package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;
import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

/**
 * A screen to make a crew member use an item
 * @author Daniel Harris and Rebekah McKinnon
 *
 */
public class UseItemScreen extends JPanel {
	
	/* 
	 * These variables have default visibility so they can be adjusted and/or 
	 * used to update other widgets 
	 */
	/** A message pane to display important information */
	SpaceMessagePane messagePane;
	/** A text pane to display information about the selected crew member */
	JTextPane crewMemberInfo;
	JComboBox<Consumable> itemSelection;
	
	/** The window holding this panel */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in */
	private GameEnvironment gameEnvironment;
	/** The contents of the message pane */
	private ArrayList<String> messagePaneContents;
	/** The object which is handling the input and output of the game environment */
	private InOutHandler inOut;
	
	/** A text pane to display the selected item info */
	private JTextPane txtpnItemInfo;

	
	/**
	 * Creates the panel
	 * @param guiWindow the window to create this panel for
	 */
	public UseItemScreen(GUIWindow guiWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
	/**
	 * Updates the item info pane to show what is selected
	 */
	public void updateItemInfo() {
		Object item = itemSelection.getSelectedItem();
		if (item instanceof Consumable) {
			Consumable newItem = (Consumable) item;
			String itemInfo = newItem.getName() + "\n" + newItem.getClassification() + " item\n" + 
					newItem.getDescription() + "\nQuantity: " + gameEnvironment.getInventory().get(newItem);
			txtpnItemInfo.setText(itemInfo);
		}
	}

	
	/**
	 * Initialize the panel contents
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Use Item");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		SpaceLabel lblItemSelector = new SpaceLabel("Item:");
		lblItemSelector.setBounds(0, 80, 50, 36);
		add(lblItemSelector);
		
		itemSelection = new JComboBox<Consumable>();
		itemSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		itemSelection.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof Consumable) {
					Consumable item = (Consumable) value;
					setText(item.getName() + ", Quantity: " + gameEnvironment.getInventory().get(item));
				}
				
				return this;	
			}
		});
		itemSelection.setBounds(60, 80, 526, 36);
		add(itemSelection);
		
		SpaceButton btnConfirmUseItem = new SpaceButton("Use item");;
		btnConfirmUseItem.setBounds(0, 264, 283, 36);
		add(btnConfirmUseItem);
		
		crewMemberInfo = new JTextPane();
		crewMemberInfo.setEditable(false);
		crewMemberInfo.setText("Crew member info");
		crewMemberInfo.setForeground(Color.WHITE);
		crewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMemberInfo.setBackground(new Color(25, 25, 112));
		crewMemberInfo.setBounds(303, 126, 283, 138);
		add(crewMemberInfo);
		
		txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setEditable(false);
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(0, 126, 283, 128);
		add(txtpnItemInfo);
		
		itemSelection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object item = itemSelection.getSelectedItem();
				if (item instanceof Consumable) {
					Consumable newItem = (Consumable) item;
					String itemInfo = newItem.getName() + "\n" + newItem.getClassification() + " item\n" + 
							newItem.getDescription() + "\nQuantity: " + gameEnvironment.getInventory().get(newItem);
					txtpnItemInfo.setText(itemInfo);
				}
			}
		});
		
		btnConfirmUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.useItem((CrewMember) guiWindow.crewMembersScreen.crewMemberSelection.getSelectedItem(), 
						(Consumable) itemSelection.getSelectedItem());
				messagePaneContents.add((String) inOut.getOutput());
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Screen");
				guiWindow.updatePane();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Crew Members");
				guiWindow.updatePane();
			}
		});
	}
}
