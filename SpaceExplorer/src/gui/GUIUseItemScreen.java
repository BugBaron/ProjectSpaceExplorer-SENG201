package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import main.Consumable;
import main.GameEnvironment;
import main.CrewMemberTypes.CrewMember;

public class GUIUseItemScreen implements GUIScreen{
	
	JPanel panel;
	SpaceMessagePane messagePane;
	JTextPane crewMemberInfo;
	
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GUIUseItemScreen(NewGUIWindow guiWindow) {
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setLayout(null);
		
		SpaceTitle lblTitle = new SpaceTitle("Use Item");
		lblTitle.setBounds(0, 0, 586, 60);
		panel.add(lblTitle);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		panel.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		panel.add(btnBack);
		
		SpaceLabel lblItemSelector = new SpaceLabel("Item:");
		lblItemSelector.setBounds(0, 80, 50, 36);
		panel.add(lblItemSelector);
		
		JComboBox<Consumable> comboBoxItemSelection = new JComboBox<Consumable>();
		comboBoxItemSelection.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		comboBoxItemSelection.setRenderer(new BasicComboBoxRenderer() {
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
		comboBoxItemSelection.setBounds(60, 80, 526, 36);
		panel.add(comboBoxItemSelection);
		
		SpaceButton btnConfirmUseItem = new SpaceButton("Use item");;
		btnConfirmUseItem.setBounds(0, 264, 283, 36);
		panel.add(btnConfirmUseItem);
		
		crewMemberInfo = new JTextPane();
		crewMemberInfo.setText("Crew member info");
		crewMemberInfo.setForeground(Color.WHITE);
		crewMemberInfo.setFont(new Font("MS Gothic", Font.PLAIN, 12));
		crewMemberInfo.setBackground(new Color(25, 25, 112));
		crewMemberInfo.setBounds(303, 126, 283, 138);
		panel.add(crewMemberInfo);
		
		JTextPane txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(0, 126, 283, 128);
		panel.add(txtpnItemInfo);
	}
}
