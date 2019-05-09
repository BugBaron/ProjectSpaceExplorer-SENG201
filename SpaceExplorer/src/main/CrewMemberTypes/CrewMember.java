package main.CrewMemberTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import main.Consumable;
import main.FindableItem;
import main.Ship;

public abstract class CrewMember {
	
	Ship ship;
	private HashMap <String, Integer> status;
	private int numActions;
	
	// Variables which are modified upon being created by sub-classes
	// All-caps variables are not modified after the sub-class is constructed
	HashMap <String, Integer> MAX_STAT;
	int REPAIR_AMOUNT = 2;
	boolean spacePlague = false;
	String name;
	HashMap <String, String> TYPE_INFO;
	// The probabilities are coded to be relative
	int[] SEARCHING_PROBABILITIES = {1, 1, 1, 1, 1};
	
	
	/**
	 * Initialize the class as default with a certain ship. Called by subclasses
	 */
	CrewMember(Ship currentShip) {
		ship = currentShip;
		status = new HashMap <String, Integer>();
		numActions = 2;
	}
	
	
	/**
	 * Does all things related to ending the day, such as losing health, 
	 * energy, and other actions which are class specific.
	 * Defined in the subclass
	 */
	public abstract void endDay();
	
	
	/**
	 * Retrieves the status of the crew member
	 * @return a map containing the "Health", "Nutrition" and "Energy" of the 
	 * crew member
	 */
	public HashMap <String, Integer> getStatus() {
		return status;
	}
	
	
	/**
	 * A helper function for addHealth, addEnergy and addNutrition
	 * @param amount the amount to add. Can be negative to decrease the stat
	 */
	private void addStats(int amount, String stat) {
		int newStat = amount + status.get(stat);
		if (newStat <= 0) {
			// Here the attribute has been reduced to or beyond zero
			status.put(stat, 0);
			if (stat == "Health") {
				kill();
			}
		} else if (newStat < MAX_STAT.get(stat)) {
			// The general case
			status.put(stat, newStat);
		} else {
			// Here the attribute has been increased above the maximum
			status.put(stat, MAX_STAT.get(stat));
		}
	}
	
	/**
	 * Adds health to this crew member
	 * @param amount the amount to increase the health by, can be negative to decrease health
	 */
	public void addHealth(int amount) {
		addStats(amount, "Health");
	}
	
	
	/**
	 * Adds energy to this crew member
	 * @param amount the amount to increase the energy by, can be negative to decrease energy
	 */
	public void addEnergy(int amount) {
		addStats(amount, "Energy");
	}
	
	
	/**
	 * Adds nutrition to this crew member
	 * @param amount the amount to increase the nutrition by, can be negative to decrease nutrition
	 */
	public void addNutrition(int amount) {
		addStats(amount, "Nutrition");
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
		// List of possibilities that are available
		FindableItem[] findableList = {FindableItem.SHIP_PARTS, 
				FindableItem.FOOD_ITEMS, FindableItem.MEDICAL_ITEMS, 
				FindableItem.MONEY, FindableItem.NONE};
		
		completeAction();
		
		// Finds the item to return based on what the searching probabilities are
		for (int i = 0; i < findableList.length; i++) {
			currentVar = currentVar + SEARCHING_PROBABILITIES[i];
			/* If the random variable is between the current integer of 
			 * SEARCHING_PROBABILITIES and the previous one then return the
			 * relevant FindableItem
			 */
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
		// Converts the inventory keys to an array
		Object[] keys = inventory.keySet().toArray();
		
		// Returns to previous menu if there are no items in the inventory
		int size = inventory.size();
		if (size == 0) {
			System.out.println("No items in the ship inventory!");
			System.out.println("Returning to Crew Member Actions...");
			return false;
		}
		
		// Displays all the items in the inventory as options
		int i;
		for (i = 0; i < size; i++) {
			Consumable item = (Consumable) keys[i];
			String classification = item.getClassification();
			String name = item.getName();
			String description = item.getDescription();
			System.out.println((i + 1) + ") " + classification + " item: " + name + ", " + description + ", Quantity: " + inventory.get(item));
		}

		System.out.println((i + 1) + ") Back to Crew Member Actions");
		System.out.flush();

		// Collects the user input
		int choice = ship.collectInt(1, i + 1);
		
		// Uses the selected item and reduces the quantity of that item in the inventory
		if (choice < (i + 1)) {
			Consumable item = (Consumable) keys[choice - 1];
			
			item.useItem(this);
			
			inventory.put(item, inventory.get(item) - 1);
			if (inventory.get(item) == 0) {
				inventory.remove(item);
			}
			
			completeAction();
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
		addNutrition(-1);
	}
	
	
	/**
	 * Repairs the ship by "REPAIR_AMOUNT"
	 */
	public void repairShip() {
		completeAction();
		ship.addShipShields(REPAIR_AMOUNT);
	}
	
	
	/**
	 * Finds another crew member and pilots the ship to another planet with them
	 */
	public boolean pilotShip() {
		ArrayList<CrewMember> crewMembers = ship.getCrewMembers();
		
		// This part displays options for all other crew members that have actions remaining
		ArrayList<CrewMember> membersWithActions = new ArrayList<CrewMember>();
		for (int i = 0; i < crewMembers.size(); i++) {
			CrewMember person = crewMembers.get(i);
			
			// Ensure that only other crew members that have actions are shown
			if (person.getActions() > 0 && this != person) {
				membersWithActions.add(person);
				int index = membersWithActions.size();
				System.out.println(index + ") " + person.getName() + ", " + person.TYPE_INFO.get("Type"));
			}
		}
		
		int index = membersWithActions.size();
		// Returns to the previous menu if there are no other crew members to pilot the ship with
		if (index == 0) {
			System.out.println("No other crew members have any remaining actions!");
			System.out.println("Returning to Crew Member Actions...");
			return false;
		}
		
		System.out.println((index + 1) + ") Back to Crew Member Actions");
		System.out.flush();

		// Collects the user input
		int choice = ship.collectInt(1, index + 1);
		
		// Completes the action for this crew member and the other chosen one
		if (choice < (index + 1)) {
			CrewMember person = membersWithActions.get(choice - 1);
			person.completeAction();
			completeAction();
			return true;
		} else {
			return false;
		}
	}
	
	public void completeAction() {
		numActions--;
		addEnergy(-1);
		addNutrition(-1);
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
	 * Sets the number of actions available for this crew member
	 * @param actions the number of actions the crew member should have
	 */
	void setActions(int actions) {
		numActions = actions;
	}
	

}