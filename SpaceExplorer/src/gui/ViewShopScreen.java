package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceLabel;
import gui.spaceWidgets.SpaceMessagePane;
import gui.spaceWidgets.SpaceTitle;
import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A screen to view and purchase items.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class ViewShopScreen extends JPanel {

	/**
	 * A message pane to display important information. It must be public to
	 * allow it to be updated.
	 */
	SpaceMessagePane messagePane;
	
	/** The window holding this panel. */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in. */
	private GameEnvironment gameEnvironment;
	/** The contents of the message pane. */
	private ArrayList<String> messagePaneContents;
	/** The object which is handling the input and output of the game environment. */
	private InOutHandler inOut;
	
	/** A label to display the money that the user has available. */
	private SpaceLabel lblMoney;
	/** A list of shop items which can be selected. */
	private JList<Consumable> listShopItems;
	/** A button to purchase the item. */
	private SpaceButton btnPurchaseItem;
	/** A text pane to display information about the item being purchased. */
	private JTextPane txtpnItemInfo;

	
	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public ViewShopScreen(GUIWindow tempWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		guiWindow = tempWindow;
		gameEnvironment = tempWindow.gameEnvironment;
		messagePaneContents = tempWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
	/**
	 * Updates the items displayed in the shop screen.
	 */
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
	
	
	/**
	 * Updates the description of the selected item in the shop screen.
	 */
	public void updateShopItem() {
		Object selection = listShopItems.getSelectedValue();
		String text = "Item info";
		if (selection instanceof Consumable) {
			Consumable item = (Consumable) selection;
			text = item.getName() + "\n"
					+ item.getDescription() + "\n"
					+ "Price: $" + item.getPrice() + "\n"
					+ "Quantity: " + gameEnvironment.getShop().get(item);
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
	 * Initialize the panel contents.
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Shop");
		lblTitle.setBounds(0, 0, 586, 60);
		add(lblTitle);
		
		txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setEditable(false);
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(303, 80, 283, 128);
		add(txtpnItemInfo);
		
		messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		add(messagePane.getScrollPane());
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		add(btnBack);
		
		btnPurchaseItem = new SpaceButton("Purchase item");
		btnPurchaseItem.setBounds(303, 218, 283, 36);
		add(btnPurchaseItem);
		
		listShopItems = new JList<Consumable>();
		listShopItems.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean hasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
				if (value instanceof Consumable) {
					Consumable item = (Consumable) value;
					String name = item.getName();
					setText(item.getName() + "," + " ".repeat(17 - name.length()) + "$"
							+ item.getPrice() + ", " + gameEnvironment.getShop().get(item));
				}
				
				return this;
			}
		});
		listShopItems.setBackground(new Color(25, 25, 112));
		listShopItems.setForeground(new Color(255, 255, 255));
		listShopItems.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), 
				new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		listShopItems.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		listShopItems.setVisibleRowCount(9);
		listShopItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listShopItems.setBounds(0, 80, 283, 277);
		add(listShopItems);	
		
		lblMoney = new SpaceLabel("Money: $100");
		lblMoney.setBounds(0, 367, 285, 36);
		add(lblMoney);
		
		btnPurchaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.purchaseItem(listShopItems.getSelectedValue());
				updateShopScreen();
				messagePaneContents.add((String) inOut.getOutput());
				guiWindow.updatePane();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Visit Outpost");
				guiWindow.updatePane();
			}
		});
		
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
