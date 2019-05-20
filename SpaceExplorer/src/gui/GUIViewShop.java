package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIViewShop extends JPanel {

	private NewGUIWindow guiWindow;
	private ArrayList<String> messagePaneContents;
	private GameEnvironment gameEnvironment;
	private InOutHandler inOut;
	/**
	 * Create the panel.
	 */
	public GUIViewShop(NewGUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		messagePaneContents = guiWindow.messagePaneContents;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		SpaceTitle lblTitle = new SpaceTitle("Shop");
		lblTitle.setBounds(0, 0, 586, 60);
		super.add(lblTitle);
		
		JTextPane txtpnItemInfo = new JTextPane();
		txtpnItemInfo.setText("Item info");
		txtpnItemInfo.setForeground(Color.WHITE);
		txtpnItemInfo.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfo.setEditable(false);
		txtpnItemInfo.setBackground(new Color(25, 25, 112));
		txtpnItemInfo.setBounds(303, 80, 283, 128);
		super.add(txtpnItemInfo);
		
		SpaceMessagePane messagePane = new SpaceMessagePane();
		messagePane.setBounds(303, 264, 283, 93);
		super.add(messagePane);
		
		SpaceButton btnBack = new SpaceButton("Back");
		btnBack.setBounds(303, 367, 283, 36);
		super.add(btnBack);
		
		SpaceButton btnPurchaseItem = new SpaceButton("Purchase item");
		btnPurchaseItem.setBounds(303, 218, 283, 36);
		super.add(btnPurchaseItem);
		
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
		super.add(listShopItems);	
		
		SpaceLabel lblMoney = new SpaceLabel("Money: $100");
		lblMoney.setBounds(0, 367, 285, 36);
		super.add(lblMoney);
		
		btnPurchaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO ACTION FOR THIS BUTTON
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Visit Outpost");
				guiWindow.updatePane();
			}
		});
	}

}
