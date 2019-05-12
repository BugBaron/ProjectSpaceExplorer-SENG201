package main;

import java.util.ArrayList;

import main.CrewMemberTypes.CrewMember;

public class Ship {
	
	private String shipName;
	private ArrayList<CrewMember> crewMembers;
	private int money;
	private int shipShields;
	private final int MAX_SHIP_SHIELDS = 10;
	
	/** This is the total score excluding what was earned today */
	private int totalScore;
	/** This is the daily score */
	private int dailyScore;
	
	
	/**
	 * Class constructor for ship. Creates a ship with the default name 'The Milano'
	 */
	public Ship() {
		shipName = "The Milano";
		crewMembers = new ArrayList<CrewMember>();
		money = 100;
		shipShields = 5;
		totalScore = 0;
		dailyScore = 0;
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
		totalScore = 0;
		dailyScore = 0;
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
	 * Gets the score obtained by this ship today and resets it to zero for a new day
	 * @return the current score
	 */
	public int getDailyScore() {
		int returnScore = dailyScore;
		totalScore = totalScore + dailyScore;
		dailyScore = 0;
		return returnScore;
	}
	
	
	/**
	 * Gets the total score currently obtained by this ship
	 * @return the total score
	 */
	public int getTotalScore() {
		return totalScore + dailyScore;
	}
	
	
	/**
	 * Increases the score by a certain amount
	 * @param amount the amount to increase the score
	 */
	public void addScore(int amount) {
		dailyScore = dailyScore + amount;
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