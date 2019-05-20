package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import main.Consumable;
import main.GameEnvironment;
import main.InOutHandler;
import main.CrewMemberTypes.CrewMember;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class NewGUIWindow {

	JFrame frame;
	CardLayout layout;
	ArrayList<String> messagePaneContents = new ArrayList<String>();
	GameEnvironment gameEnvironment = new GameEnvironment();
	
	GUIMainScreen mainScreen = new GUIMainScreen(this);
	GUICrewMembersScreen crewMembersScreen = new GUICrewMembersScreen(this);
	GUIUseItemScreen useItemScreen = new GUIUseItemScreen(this);
	
	private InOutHandler inOut = gameEnvironment.getInOut();
	private HashMap<String, GUIScreen> screens = new HashMap<String, GUIScreen>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGUIWindow window = new NewGUIWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public NewGUIWindow() {
		gameEnvironment.createGame();
		initialize();
	}
	
	public void updateCrewMemberInfo() {
		Object item = crewMembersScreen.crewMemberSelection.getSelectedItem();
		if (item instanceof CrewMember) {
			CrewMember crewMember = (CrewMember) item;
			String memberText = crewMember.toString() + "\nActions: " + crewMember.getActions();
			memberText = memberText.substring(memberText.indexOf("\n") + 1);
			crewMembersScreen.crewMemberInfo.setText(memberText);
			useItemScreen.crewMemberInfo.setText(memberText);
		}
	}
	
	/**
	 * Update the text for the specified message pane
	 */
	public void updatePane() {
		String text = "";
		for (int i=0; i < messagePaneContents.size(); i++) {
			text = messagePaneContents.get(i) + "\n" + text;
		}
		mainScreen.messagePane.setText(text);
		crewMembersScreen.messagePane.setText(text);
		useItemScreen.messagePane.setText(text);
		pilotShipScreen.messagePane.setText(text);
		shipScreen.messagePane.setText(text);
		visitOutpostScreen.messagePane.setText(text);
		shopScreen.messagePane.setText(text);
		inventoryScreen.messagePane.setText(text);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		layout = new CardLayout(20, 20);
		frame.getContentPane().setLayout(layout);
		
		frame.getContentPane().add(mainScreen, "Main Screen");
		frame.getContentPane().add(crewMembersScreen, "Crew Members");
		frame.getContentPane().add(useItemScreen, "Use Item");
		
		JPanel pilotShip = new JPanel();
		pilotShip.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(pilotShip, "Pilot Ship");
		pilotShip.setLayout(null);

		JPanel shipStatus = new JPanel();
		shipStatus.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(shipStatus, "Ship Status");
		shipStatus.setLayout(null);

		JPanel visitOutpost = new JPanel();
		visitOutpost.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(visitOutpost, "Visit Outpost");
		visitOutpost.setLayout(null);
		
		JPanel shopScreen = new JPanel();
		shopScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(shopScreen, "Shop Screen");
		shopScreen.setLayout(null);

		JPanel inventoryScreen = new JPanel();
		inventoryScreen.setLayout(null);
		inventoryScreen.setBackground(new Color(25, 25, 112));
		frame.getContentPane().add(inventoryScreen, "Inventory Screen");
		
		// Ship Status Screen
		
		JLabel lblShipStatus = new JLabel("Ship Status");
		lblShipStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblShipStatus.setForeground(Color.YELLOW);
		lblShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblShipStatus.setBounds(0, 0, 586, 60);
		shipStatus.add(lblShipStatus);
		
		JLabel lblImageShipStatus = new JLabel("");
		lblImageShipStatus.setIcon(new ImageIcon(NewGUIWindow.class.getResource("/images/SPACE.PNG")));
		lblImageShipStatus.setVerticalAlignment(SwingConstants.TOP);
		lblImageShipStatus.setBounds(303, 80, 283, 174);
		shipStatus.add(lblImageShipStatus);
		
		JTextPane txtpnMessagePaneShipStatus = new JTextPane();
		txtpnMessagePaneShipStatus.setEditable(false);
		txtpnMessagePaneShipStatus.setText("Message pane");
		txtpnMessagePaneShipStatus.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneShipStatus.setBounds(303, 264, 283, 93);
		shipStatus.add(txtpnMessagePaneShipStatus);
		
		JButton btnBackShipStatus = new JButton("Back");
		btnBackShipStatus.setFocusable(false);
		btnBackShipStatus.setForeground(Color.WHITE);
		btnBackShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackShipStatus.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackShipStatus.setBackground(new Color(25, 25, 112));
		btnBackShipStatus.setBounds(303, 367, 283, 36);
		shipStatus.add(btnBackShipStatus);
		
		JTextPane txtpnShipStatus = new JTextPane();
		txtpnShipStatus.setEditable(false);
		txtpnShipStatus.setText("Ship Status");
		txtpnShipStatus.setForeground(Color.WHITE);
		txtpnShipStatus.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnShipStatus.setBackground(new Color(25, 25, 112));
		txtpnShipStatus.setBounds(0, 80, 283, 174);
		shipStatus.add(txtpnShipStatus);
		
		// Visit Outpost Screen
		
		JLabel lblVisitOutpostTitle = new JLabel("Outpost");
		lblVisitOutpostTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisitOutpostTitle.setForeground(Color.YELLOW);
		lblVisitOutpostTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblVisitOutpostTitle.setBounds(0, 0, 586, 60);
		visitOutpost.add(lblVisitOutpostTitle);
		
		JLabel lblImageVisitOutpost = new JLabel("");
		lblImageVisitOutpost.setIcon(new ImageIcon(NewGUIWindow.class.getResource("/images/OUTPOST.png")));
		lblImageVisitOutpost.setVerticalAlignment(SwingConstants.TOP);
		lblImageVisitOutpost.setBounds(303, 80, 283, 174);
		visitOutpost.add(lblImageVisitOutpost);
		
		JTextPane txtpnMessagePaneVisitOutpost = new JTextPane();
		txtpnMessagePaneVisitOutpost.setEditable(false);
		txtpnMessagePaneVisitOutpost.setText("Message pane");
		txtpnMessagePaneVisitOutpost.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneVisitOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneVisitOutpost.setBounds(303, 264, 283, 93);
		visitOutpost.add(txtpnMessagePaneVisitOutpost);
		
		JButton btnBackVisitOutpost = new JButton("Back");
		btnBackVisitOutpost.setFocusable(false);
		btnBackVisitOutpost.setForeground(Color.WHITE);
		btnBackVisitOutpost.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackVisitOutpost.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackVisitOutpost.setBackground(new Color(25, 25, 112));
		btnBackVisitOutpost.setBounds(303, 367, 283, 36);
		visitOutpost.add(btnBackVisitOutpost);
		
		JButton btnViewShop = new JButton("View Shop");
		btnViewShop.setFocusable(false);
		btnViewShop.setForeground(Color.WHITE);
		btnViewShop.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewShop.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewShop.setBackground(new Color(25, 25, 112));
		btnViewShop.setBounds(0, 80, 283, 36);
		visitOutpost.add(btnViewShop);
		
		JButton btnViewInventory = new JButton("View Inventory");
		btnViewInventory.setFocusable(false);
		btnViewInventory.setForeground(Color.WHITE);
		btnViewInventory.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnViewInventory.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnViewInventory.setBackground(new Color(25, 25, 112));
		btnViewInventory.setBounds(0, 126, 283, 36);
		visitOutpost.add(btnViewInventory);
		
		// Shop Screen
		
		JLabel lblShopTitle = new JLabel("Shop");
		lblShopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblShopTitle.setForeground(Color.YELLOW);
		lblShopTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblShopTitle.setBounds(0, 0, 586, 60);
		shopScreen.add(lblShopTitle);
		
		JTextPane txtpnItemInfoShopScreen = new JTextPane();
		txtpnItemInfoShopScreen.setText("Item info");
		txtpnItemInfoShopScreen.setForeground(Color.WHITE);
		txtpnItemInfoShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnItemInfoShopScreen.setEditable(false);
		txtpnItemInfoShopScreen.setBackground(new Color(25, 25, 112));
		txtpnItemInfoShopScreen.setBounds(303, 80, 283, 128);
		shopScreen.add(txtpnItemInfoShopScreen);
		
		JTextPane txtpnMessagePaneShopScreen = new JTextPane();
		txtpnMessagePaneShopScreen.setText("Message pane");
		txtpnMessagePaneShopScreen.setForeground(new Color(0, 0, 128));
		txtpnMessagePaneShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneShopScreen.setEditable(false);
		txtpnMessagePaneShopScreen.setBounds(303, 264, 283, 93);
		shopScreen.add(txtpnMessagePaneShopScreen);
		
		JButton btnBackShopScreen = new JButton("Back");
		btnBackShopScreen.setFocusable(false);
		btnBackShopScreen.setForeground(Color.WHITE);
		btnBackShopScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackShopScreen.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackShopScreen.setBackground(new Color(25, 25, 112));
		btnBackShopScreen.setBounds(303, 367, 283, 36);
		shopScreen.add(btnBackShopScreen);
		
		JButton btnPurchaseItem = new JButton("Purchase item");
		btnPurchaseItem.setFocusable(false);
		btnPurchaseItem.setForeground(Color.WHITE);
		btnPurchaseItem.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnPurchaseItem.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnPurchaseItem.setBackground(new Color(25, 25, 112));
		btnPurchaseItem.setBounds(303, 218, 283, 36);
		shopScreen.add(btnPurchaseItem);
		
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
		shopScreen.add(listShopItems);	
		
		JLabel lblMoney = new JLabel("Money: $100");
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		lblMoney.setBounds(0, 367, 285, 36);
		shopScreen.add(lblMoney);
		
		// Inventory Screen
		
		JTextPane txtpnMessagePaneInventoryScreen = new JTextPane();
		txtpnMessagePaneInventoryScreen.setEditable(false);
		txtpnMessagePaneInventoryScreen.setText("Message pane");
		txtpnMessagePaneInventoryScreen.setForeground(new Color(0, 0, 0));
		txtpnMessagePaneInventoryScreen.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		txtpnMessagePaneInventoryScreen.setBounds(303, 264, 283, 93);
		inventoryScreen.add(txtpnMessagePaneInventoryScreen);
		
		JButton btnBackInventoryScreen = new JButton("Back");
		btnBackInventoryScreen.setFocusable(false);
		btnBackInventoryScreen.setForeground(Color.WHITE);
		btnBackInventoryScreen.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnBackInventoryScreen.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnBackInventoryScreen.setBackground(new Color(25, 25, 112));
		btnBackInventoryScreen.setBounds(303, 367, 283, 36);
		inventoryScreen.add(btnBackInventoryScreen);
		
		JLabel lblInventoryTitle = new JLabel("Inventory");
		lblInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventoryTitle.setForeground(Color.YELLOW);
		lblInventoryTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblInventoryTitle.setBounds(0, 0, 586, 60);
		inventoryScreen.add(lblInventoryTitle);
		
		JLabel lblImageInventoryScreen = new JLabel("");
		lblImageInventoryScreen.setIcon(new ImageIcon(NewGUIWindow.class.getResource("/images/STORAGE.PNG")));
		lblImageInventoryScreen.setVerticalAlignment(SwingConstants.TOP);
		lblImageInventoryScreen.setBounds(303, 80, 283, 174);
		inventoryScreen.add(lblImageInventoryScreen);
		
		JTree treeInventoryContainers = new JTree();
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
		inventoryScreen.add(treeInventoryContainers);
		
		final class Methods {
			Methods() {};
			
			/** Shows the Main Screen and updates visible widgets */
			public final void gotoMainScreen() {
				layout.show(frame.getContentPane(), "Main Screen");
				updatePane(txtpnMessagePane);
				lblDayNumber.setText(gameEnvironment.getDayString());
			}
			
			/** Shows the Crew Member screen and updates visible widgets */
			public final void gotoCrewMember() {
				layout.show(frame.getContentPane(), "Crew Members");
				updatePane(txtpnMessagePaneCrewMembers);
				
				ArrayList<CrewMember> crewMembers = gameEnvironment.getCrewMembers();
				CrewMember[] newMembers = new CrewMember[crewMembers.size()];
				for (int i = 0; i < crewMembers.size(); i++) {
					newMembers[i] = crewMembers.get(i);
				}
				comboBoxCrewMemberSelection.setModel(new DefaultComboBoxModel<CrewMember>(newMembers));
			}
			
			/** Updates the visible widgets of the Crew Member screen */
			public final void updateCrewMembers() {
				Object item = comboBoxCrewMemberSelection.getSelectedItem();
				if (item instanceof CrewMember) {
					CrewMember crewMember = (CrewMember) item;
					int actions = crewMember.getActions();
					btnUseItem.setEnabled(actions > 0 && gameEnvironment.getInventory().size() > 0);
					btnSleep.setEnabled(actions > 0);
					btnRepairShip.setEnabled(actions > 0);
					btnSearchPlanet.setEnabled(actions > 0);
					btnPilotShip.setEnabled(actions > 0 && gameEnvironment.getAvailableMembers().size() > 1);
					String crewMemberInfo = crewMember.toString() + "\nActions: " + crewMember.getActions();
					crewMemberInfo = crewMemberInfo.substring(crewMemberInfo.indexOf("\n") + 1);
					txtpnCrewMemberInfo.setText(crewMemberInfo);
					txtpnCrewMemberInfoUseItem.setText(crewMemberInfo);
					txtpnCrewMemberInfoPilotShip.setText(crewMemberInfo);
				}
			}
			
			/** Updates the visible widgets of the Shop Screen */
			public final void updateShopScreen() {
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
			
			/** Updates the item info widget of the Shop Screen */
			public final void updateShopItem() {
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
				txtpnItemInfoShopScreen.setText(text);
			}
		}
		
		final Methods METHODS = new Methods();
		
		
		
		btnConfirmPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.pilotShip((CrewMember) comboBoxCrewMemberSelection.getSelectedItem(), 
						(CrewMember) comboBoxCrewMember2Selection.getSelectedItem());
				Object output = inOut.getOutput();
				while (output != null) {
					messagePaneContents.add((String) output);
					output = inOut.getOutput();
				}
				METHODS.gotoMainScreen();
			}
		});
		
		btnBackPilotShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoCrewMember();
			}
		});
		
		btnBackShipStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoMainScreen();
			}
		});
		
		btnViewShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.updateShopScreen();
				layout.show(frame.getContentPane(), "Shop Screen");
				updatePane(txtpnMessagePaneShopScreen);
			}
		});
		
		btnViewInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				treeInventoryContainers.setModel(new DefaultTreeModel(
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
				layout.show(frame.getContentPane(), "Inventory Screen");
				updatePane();
			}
		});
		
		btnBackVisitOutpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				METHODS.gotoMainScreen();
			}
		});
		
		btnPurchaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.purchaseItem(listShopItems.getSelectedValue());
				METHODS.updateShopScreen();
				messagePaneContents.add((String) inOut.getOutput());
				updatePane(txtpnMessagePaneShopScreen);
			}
		});
		
		btnBackShopScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(frame.getContentPane(), "Visit Outpost");
				updatePane(txtpnMessagePaneVisitOutpost);
			}
		});
		
		listShopItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Object item = listShopItems.getSelectedValue();
				if (item instanceof Consumable) {
					METHODS.updateShopItem();
				}
			}
		});
		
		btnBackInventoryScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(frame.getContentPane(), "Visit Outpost");
				updatePane(txtpnMessagePaneVisitOutpost);
			}
		});
		
		
		
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
