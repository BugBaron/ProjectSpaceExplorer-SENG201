package main;

import java.util.HashMap;
import java.util.Random;

import main.Ship;

public abstract class CrewMember {
	
	private Ship ship;
	private HashMap <String, Integer> status;
	private int numActions;
	
	// The probabilities are coded to bee relative
	private final int[] SEARCHING_PROBABILITIES = {1, 1, 1, 1, 1};
	
	// Variables which are modified upon being created by sub-classes
	// Variables which are in all-caps are not modified after the sub-class is constructed
	private HashMap <String, Integer> MAX_STAT;
	private int REPAIR_AMOUNT;
	private boolean spacePlague;
	private String name;
	private HashMap <String, String> TYPE_INFO;
	
	
	/**
	 * Initialize the class as default with a certain ship. Called by subclasses
	 */
	CrewMember(Ship currentShip) {
		ship = currentShip;
	}
	
	
	/**
	 * Does all things related to ending the day, such as losing health, energy, and
	 * other actions which are class specific
	 * Defined in the subclass
	 */
	public abstract void endDay();
	
	
	/**
	 * Retrieves the status of the crew member
	 * @return a map containing the "Health", "Nutrition" and "Energy" of the crew member
	 */
	public HashMap <String, Integer> getStatus() {
		return status;
	}
	
	
	/**
	 * Adds health to this crew member
	 * @param amount the amount to increase the health by, can be negative to decrease health
	 */
	public void addHealth(int amount) {
		if (amount + status.get("Health") < 0) {
			status.put("Health", 0);
			kill();
		} else if (amount + status.get("Health") < MAX_STAT.get("Health")) {
			status.put("Health", amount + status.get("Health"));
		} else {
			status.put("Health", MAX_STAT.get("Health"));
		}
	}
	
	
	/**
	 * Adds energy to this crew member
	 * @param amount the amount to increase the energy by, can be negative to decrease energy
	 */
	public void addEnergy(int amount) {
		if (amount + status.get("Energy") < 0) {
			status.put("Energy", 0);
		} else if (amount + status.get("Energy") < MAX_STAT.get("Energy")) {
			status.put("Energy", amount + status.get("Energy"));
		} else {
			status.put("Energy", MAX_STAT.get("Energy"));
		}
	}
	
	
	/**
	 * Adds nutrition to this crew member
	 * @param amount the amount to increase the nutrition by, can be negative to decrease nutrition
	 */
	public void addNutrition(int amount) {
		if (amount + status.get("Nutrition") < 0) {
			status.put("Nutrition", 0);
		} else if (amount + status.get("Nutrition") < MAX_STAT.get("Nutrition")) {
			status.put("Nutrition", amount + status.get("Nutrition"));
		} else {
			status.put("Nutrition", MAX_STAT.get("Nutrition"));
		}
	}
	
	
	/**
	 * Gets the number of remaining actions of the crew member
	 * @return the number of remaining actions
	 */
	public int getActions() {
		return numActions;
	}
	
	
	/**
	 * Searches a planet for spaceship parts, money, food or medical items.
	 * Adding the items to the inventory is handled by the GameEnvironment class which would call this
	 * @return a FindableItem representing what was found
	 */
	public FindableItem searchPlanet() {
		Random r = new Random();
		int totalSum = 0;
		for (int i: SEARCHING_PROBABILITIES) {
			totalSum = totalSum + i;
		}
		int randInt = r.nextInt(totalSum);
		int currentVar = 0;
		FindableItem[] findableList = {FindableItem.SHIP_PARTS, FindableItem.FOOD_ITEMS, 
				FindableItem.MEDICAL_ITEMS, FindableItem.MONEY, FindableItem.NONE};
		
		// Finds the item to return based on what the searching probabilities are
		for (int i = 0; i < findableList.length; i++) {
			currentVar = currentVar + SEARCHING_PROBABILITIES[i];
			if (randInt < currentVar) {
				return findableList[i];
			}
		}
		return FindableItem.NONE;
	}
	
	
	/**
	 * Finds an item to use in the inventory and uses it
	 * @return a boolean representing whether or not an item was used
	 */
	public boolean useItem() {
		HashMap<Consumable, Integer> inventory = ship.getInventory();
		
		Object[] keys = inventory.keySet().toArray();
		
		int i;
		int size = inventory.size();
		for (i = 0; i < size; i++) {
			Consumable item = (Consumable) keys[i];
			String classification = item.getClassification();
			String name = item.getName();
			String description = item.getDescription();
			System.out.println((i + 1) + ") " + classification + " item: " + name + ", " + description + ", Quantity: " + inventory.get(item));
		}
		System.out.println((i + 1) + ") Back to Crew Member Actions");
		System.out.flush();

		int choice = ship.collectInt(0, i + 1);
		
		if (choice < (i + 1)) {
			Consumable item = (Consumable) keys[choice - 1];
			
			item.useItem(this);
			
			inventory.put(item, inventory.get(item) - 1);
			if (inventory.get(item) == 0) {
				inventory.remove(item);
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	/** 
	 * Makes the crew member sleep and gain 4 energy
	 */
	public void sleep() {
		--numActions;
		addEnergy(4);
	}
	
	
	/**
	 * Repairs the ship by "REPAIR_AMOUNT"
	 */
	public void repairShip() {
		--numActions;
		addEnergy(-1);
		ship.addShipShields(REPAIR_AMOUNT);
	}
	
	
	/**
	 * Finds another crew member and pilots the ship to another planet with them
	 * 
	 * Incomplete
	 */
	public void pilotShip() {
		return;
	}
	
	
	/**
	 * Gets the crew members name
	 * @return the crew members name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Finds if the crew member has the space plague
	 * @return a boolean whether or not they have the space plague
	 */
	public boolean getHasSpacePlague() {
		return spacePlague;
	}
	
	
	/**
	 * Sets whether or not the crew member has the space plague
	 * @param value a boolean to determine whether or not they have the space plague
	 */
	public void setHasSpacePlague(boolean value) {
		spacePlague = value;
	}
	
	
	/** 
	 * Gets a map to describe the type of crew member this is
	 * @return a map containing the crew members "Type", "Strength" and "Weakness"
	 */
	public HashMap <String, String> getTypeInfo() {
		return TYPE_INFO;
	}
	
	
	/**
	 * Gets a map to describe the maximum stats of the crew member
	 * @return a map containing the crew members maximum "Health", "Energy" and "Nutrition"
	 */
	public HashMap <String, Integer> getMaxStats() {
		return MAX_STAT;
	}
	
	
	/**
	 * Kills this crew member
	 * 
	 * Incomplete
	 */
	private void kill() {
		ship.getCrewMembers().remove(this);
	}
	
	
	/**
	 * Gets the ship and returns it
	 * @return the ship that this crew member is on
	 */
	Ship getShip() {
		return ship;
	}
	
	
	/**
	 * Gets a string representation for the crew member
	 * @return a string representing the class
	 */
	public String toString() {
		String returnString = name + ", " + TYPE_INFO.get("Type") + "\n";
		returnString = returnString + "Health: " + status.get("Health") + "/" + MAX_STAT.get("Health") + "\n";
		returnString = returnString + "Energy: " + status.get("Energy") + "/" + MAX_STAT.get("Energy") + "\n";
		returnString = returnString + "Nutrition: " + status.get("Nutrition") + "/" + MAX_STAT.get("Nutrition") + "\n";
		returnString = returnString + "Strength: " + TYPE_INFO.get("Strength") + "\n";
		returnString = returnString + "Weakness: " + TYPE_INFO.get("Weakness");
		if (spacePlague) {
			returnString = returnString + "\nAfflicted by space plague";
		}
		return returnString;
	}
	
	
	/**
	 * Sets the variables that the class should start with from the subclass
	 * @param maxStat a map containing the maximum "Health", "Energy" and "Nutrition" of this crew member
	 * @param repairAmount an integer representing how much the ship is repaired by this crew member
	 * @param hasPlague a boolean representing whether the crew member starts with the space plague
	 * @param name a string containing the crew members name
	 * @param typeInfo a map containing the "Type", "Strength" and "Weakness" of the crew member
	 */
	void setInitialVariables(HashMap <String, Integer> maxStat, int repairAmount, boolean hasPlague, String newName, HashMap <String, String> typeInfo) {
		MAX_STAT = maxStat;
		REPAIR_AMOUNT = repairAmount;
		spacePlague = hasPlague;
		name = newName;
		TYPE_INFO = typeInfo;
		
		status = new HashMap <String, Integer>();
		status.put("Health", MAX_STAT.get("Health"));
		status.put("Energy", MAX_STAT.get("Energy"));
		status.put("Nutrition", MAX_STAT.get("Nutrition"));
		numActions = 0;
	}
	
	
	/**
	 * Sets the number of actions available for this crew member
	 * @param actions the number of actions the crew member should have
	 */
	void setActions(int actions) {
		numActions = actions;
	}
	

}
