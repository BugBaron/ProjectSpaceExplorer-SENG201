package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

public class tempGUIViewShop extends JPanel {
	
	SpaceMessagePane messagePane;
	
	private InOutHandler inOut;
	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;
	
	private SpaceLabel lblMoney;
	private JList<Consumable> listShopItems;
	private SpaceButton btnPurchaseItem;
	private JTextPane txtpnItemInfo;

	public void updateShopScreen() {
		lblMoney.setText("Money: $" + gameEnvironment.getMoney());
		Consumable initialSelection = listShopItems.getSelectedValue();
		DefaultListModel<Consumable> list = new DefaultListModel<Consumable>();
		list.addAll(gameEnvironment.getShop().getKeys());
		listShopItems.setModel(list);
		if (gameEnvironment.getShop().getKeys().contains(initialSelection)) {
			listShopItems.setSelectedValue(initialSelection, false);
		}
		updateShopItem();
	}
	
	public void updateShopItem() {
		Object selection = listShopItems.getSelectedValue();
		String text = "Item info";
		if (selection instanceof Consumable) {
			Consumable item = (Consumable) selection;
			text = item.getName() + "\n" + 
					item.getDescription() + "\n" +
					"Price: $" + item.getPrice() + "\n" + 
					"Quantity: " + gameEnvironment.getShop().get(item);
			if (gameEnvironment.getMoney() < item.getPrice()) {
				btnPurchaseItem.setEnabled(false);
				text = text + "\nYou do not have enough money to purchase this item";
			} else {
				btnPurchaseItem.setEnabled(true);
			}
		} else {
			btnPurchaseItem.setEnabled(false);
		}
		txtpnItemInfo.setText(text);
	}
	
	/**
	 * Create the application.
	 */
	public tempGUIViewShop(NewGUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		super.setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		JList<Consumable> listShopItems = new JList<Consumable>();
		listShopItems.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof Consumable) {
					Consumable item = (Consumable) value;
					String name = item.getName();
					setText(item.getName() + "," + " ".repeat(17 - name.length()) + "$" + 
							item.getPrice() + ", " + gameEnvironment.getShop().get(item));
				}
				
				return this;	
			}
		});
		listShopItems.setBackground(new Color(25, 25, 112));
		listShopItems.setForeground(new Color(255, 255, 255));
		listShopItems.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		listShopItems.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		listShopItems.setVisibleRowCount(9);
		listShopItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listShopItems.setBounds(0, 80, 283, 277);
		this.add(listShopItems);
		
		listShopItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Object item = listShopItems.getSelectedValue();
				if (item instanceof Consumable) {
					updateShopItem();
				}
			}
		});
	}
}
