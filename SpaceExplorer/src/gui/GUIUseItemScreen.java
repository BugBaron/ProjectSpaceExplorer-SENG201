package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import main.Consumable;
import main.GameEnvironment;
import main.CrewMemberTypes.CrewMember;

public class GUIUseItemScreen implements GUIScreen{
	
	JPanel panel;
	SpaceMessagePane messagePane;
	JComboBox<CrewMember> crewMemberSelection;
	JTextPane crewMemberInfo;
	
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GUIUseItemScreen(GameEnvironment environment) {
		gameEnvironment = environment;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		panel = new JPanel();
		panel.setBackground(new Color(25, 25, 112));
		panel.setLayout(null);
		
		SpaceTitle lblTitle = new SpaceTitle("Use Item");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblTitle.setBounds(0, 0, 586, 60);
		panel.add(lblTitle);
		
		messagePane = new SpaceMessagePane();
		messagePane.setEditable(false);
		messagePane.setText("Message pane");
		messagePane.setForeground(new Color(0, 0, 128));
		messagePane.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		messagePane.setBounds(303, 264, 283, 93);
		panel.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setFocusable(false);
		btnBack.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(25, 25, 112));
		btnBack.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBack.setBounds(303, 367, 283, 36);
		panel.add(btnBack);
		
		SpaceLabel lblItemSelector = new SpaceLabel("Item:");
		lblItemSelector.setForeground(Color.WHITE);
		lblItemSelector.setFont(new Font("MS Gothic", Font.PLAIN, 20));
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
		
		SpaceButton btnConfirmUseItem = new SpaceButton("Use item");
		btnConfirmUseItem.setFocusable(false);
		btnConfirmUseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnConfirmUseItem.setForeground(Color.WHITE);
		btnConfirmUseItem.setBackground(new Color(25, 25, 112));
		btnConfirmUseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
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
