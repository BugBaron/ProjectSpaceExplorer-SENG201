package main;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import main.Consumable;


public class Ship {
	
	private String shipName;
	private ArrayList<CrewMember> crewMembers;
	private int money;
	private HashMap<Consumable, Integer> inventory;
	private int shipShields;
	private final int MAX_SHIP_SHIELDS = 10;
	private int score;
	private Scanner in;
	
	public final Consumable SPACE_PLAGUE_CURE = new Consumable("Space Plague Cure");
	
	
	public Ship() {
		shipName = "The Milano";
		crewMembers = new ArrayList<CrewMember>();
		money = 100;
		shipShields = 5;
		score = 0;
		in = new Scanner(System.in);
		
		inventory = new HashMap<Consumable, Integer>();
		inventory.put(SPACE_PLAGUE_CURE, 2);
	}
	
	public Ship(String name) {
		shipName = name;
		crewMembers = new ArrayList<CrewMember>();
		money = 100;
		shipShields = 5;
		score = 0;
		in = new Scanner(System.in);
		
		inventory = new HashMap<Consumable, Integer>();
		inventory.put(SPACE_PLAGUE_CURE, 2);
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
	 * Collects an integer input for a general menu selection
	 * @return an integer received from the user
	 */
	public int collectInt(int minValue, int maxValue) {
		int choice = 0;
		while (choice == 0) {
			try {
				choice = in.nextInt();
				
				// If the choice is not in the required range
				if (!(choice > minValue - 1 && choice < maxValue + 1)) {
					System.out.println("Please input a number between " + minValue + " and " + maxValue);
					choice = 0;
				}
			// If the choice is not an integer
			} catch (InputMismatchException e) {
				System.out.println("Please input a number to select an option");
				choice = 0;
				in.next();
			}
		}
		return choice;
	}
	
	/**
	 * Collects a string input for a general user interaction
	 * @return a string received from the user
	 */
	public String collectString() {
		return in.nextLine();
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
