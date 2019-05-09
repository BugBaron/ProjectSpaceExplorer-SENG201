package main;

import java.util.HashMap;
import java.util.ArrayList;

import main.Consumable;
import main.CrewMemberTypes.CrewMember;


public class Ship {
	
	private String shipName;
	private ArrayList<CrewMember> crewMembers;
	private int money;
	private HashMap<Consumable, Integer> inventory;
	private int shipShields;
	private final int MAX_SHIP_SHIELDS = 10;
	private int score;
	
	public final Consumable SPACE_PLAGUE_CURE = new Consumable("Space Plague Cure");
	
	
	/**
	 * Class constructor for ship. Creates a ship with the default name 'The Milano'
	 */
	public Ship() {
		shipName = "The Milano";
		crewMembers = new ArrayList<CrewMember>();
		money = 100;
		shipShields = 5;
		score = 0;
		
		inventory = new HashMap<Consumable, Integer>();
	}
	
	
	/**
	 * Class constructor for ship with the ship name as the specified string
	 * @param name the name of the ship
	 */
	public Ship(String name) {
		shipName = name;
		crewMembers = new ArrayList<CrewMember>();
		money = 100;
		shipShields = 5;
		score = 0;
		
		inventory = new HashMap<Consumable, Integer>();
	}


	/**
	 * Gets the name of this ship
	 * @return the name of this ship
	 */
	public String getName() {
		return shipName;
	}
	
	
	/**
	 * Gets the amount of money that this ship is currently holding
	 * @return the current amount of money
	 */
	public int getMoney() {
		return money;
	}
	
	
	/**
	 * Adds money to this ship
	 * @param amount the amount of money to add, can be negative to take money away
	 */
	public void addMoney(int amount) {
		if (money + amount > 0) {
			money = money + amount;
		} else {
			money = 0;
		}
	}
	
	
	/**
	 * Gets a list containing all the crew members
	 * @return the list of crew members
	 */
	public ArrayList<CrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	
	/**
	 * Gets a map containing the items currently in the inventory of this ship, including their quantities
	 * @return a map from each consumable to their quantity in this ships inventory
	 */
	public HashMap<Consumable, Integer> getInventory() {
		return inventory;
	}
	
	
	/**
	 * Adds an item to this ships inventory
	 * @param item the item to add to the inventory
	 */
	public void addItem(Consumable item) {
		if (inventory.containsKey(item)) {
			inventory.put(item, inventory.get(item) + 1);
		} else {
			inventory.put(item, 1);
		}
	}
	
	
	/**
	 * Removes an item from this ships inventory
	 * @param item the item to be removed from the inventory
	 */
	public void removeItem(Consumable item) {
		if (inventory.containsKey(item)) {
			switch(inventory.get(item)) {
				case 1:	inventory.remove(item);	break;
				default: inventory.put(item, inventory.get(item) - 1);
			}
		}
	}
	
	
	/**
	 * Gets the shield level of the ship
	 * @return the shield level of the ship
	 */
	public int getShipShields() {
		return shipShields;
	}
	
	
	/**
	 * Adds ship shields to this ship
	 * @param amount the amount to increase the shields by, can be negative to decrease shields
	 */
	public void addShipShields(int amount) {
		if (amount + shipShields < 0) {
			shipShields = 0;
		} else if (amount + shipShields < MAX_SHIP_SHIELDS) {
			shipShields = shipShields + amount;
		} else {
			shipShields = MAX_SHIP_SHIELDS;
		}
		
	}


	/**
	 * Gets the score currently obtained by this ship
	 * @return the current score
	 */
	public int getScore() {
		return score;
	}
	
	
	/**
	 * Increases the score by a certain amount
	 * @param amount the amount to increase the score
	 */
	public void addScore(int amount) {
		score = score + amount;
	}

	
	/**
	 * A string representation of the ship including its name and shield level
	 * @return a string representation of the ship
	 */
	public String toString() {
		String returnString = shipName + "\n";
		returnString = returnString + "Shield level: " + shipShields + "/" + MAX_SHIP_SHIELDS;
		return returnString;
	}
}