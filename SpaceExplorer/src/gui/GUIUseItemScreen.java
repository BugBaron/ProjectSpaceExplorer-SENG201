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

import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

public class GUIUseItemScreen extends JPanel {
	
	SpaceMessagePane messagePane;
	JTextPane crewMemberInfo;
	JComboBox<Consumable> itemSelection;
	
	private JTextPane txtpnItemInfo;
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;
	private InOutHandler inOut;

	/**
	 * Create the application.
	 */
	public GUIUseItemScreen(NewGUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		super.setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Use Item");
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		super.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		super.add(btnBack);
		
		SpaceLabel lblItemSelector = new SpaceLabel("Item:");
		lblItemSelector.setBounds(0, 80, 50, 36);
		super.add(lblItemSelector);
		
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
		super.add(itemSelection);
		
		SpaceButton btnConfirmUseItem = new SpaceButton("Use item");;
		btnConfirmUseItem.setBounds(0, 264, 283, 36);
		super.add(btnConfirmUseItem);
		
		crewMemberInfo = new JTextPane();
		crewMemberInfo.setText("Crew member info");
		crewMemberInfo.setForeground(Color.WHITE);
		crewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMemberInfo.setBackground(new Color(25, 25, 112));
		crewMemberInfo.setBounds(303, 126, 283, 138);
		super.add(crewMemberInfo);
		
		txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(0, 126, 283, 128);
		super.add(txtpnItemInfo);
		
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
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Main Menu");
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