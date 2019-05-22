package main.CrewMemberTypes;

import java.util.HashMap;
import main.Ship;

/**
 * A member of the crew stranded on the ship
 * @author Daniel Harris and Rebekah McKinnon
 */
public abstract class CrewMember {
	
	/** The ship that this crew member is on */
	private Ship ship;
	/** 
	 * The status of this crew member, stored in a Map, from strings (Health, Energy and Nutrition)
	 * to the relevant integer value of the status value
	 */
	private HashMap <String, Integer> status;
	/** The number of actions that this crew member has available */
	private int numActions;
	
	// Variables which are modified in sub-class constructors
	// All-caps variables are not modified after the sub-class is constructed
	/** Whether or not this member has the space plague */
	boolean spacePlague = false;
	/** The name of this crew member */
	String name;
	
	/** The maximum value of each status value. Stored identically to the status variable */
	HashMap <String, Integer> MAX_STAT;
	/** The amount by which this crew member can repair the ship */
	int REPAIR_AMOUNT = 2;
	/** 
	 * Descriptions of the type of crew member this is. A map from strings (Type, Strength, Weakness)
	 * to the relevant description
	 */
	HashMap <String, String> TYPE_INFO;
	/** The percentage chance of finding a part, food item, medical item, money and nothing, respectively */
	int[] SEARCHING_PROBABILITIES = {20, 20, 20, 20, 20};
	
	
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
			} else if (newStat < 0) {
				// Removes score by how much below zero the stat goes to
				ship.addScore(newStat);
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
	 * Gets a list containing the probabilities of finding each
	 * kind of item when searching a planet
	 * @return a list of probabilities
	 */
	public int[] getSearchingProbabilities() {
		return SEARCHING_PROBABILITIES;
	}
	
	
	/**
	 * Gets the amount by which this crew member repairs the ship by
	 * @return the repair amount
	 */
	public int getRepairAmount() {
		return REPAIR_AMOUNT;
	}

	
	/**
	 * Completes an action for the crew member
	 */
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
		returnString = returnString + "Health:    " + status.get("Health") + "/" + MAX_STAT.get("Health") + "\n";
		returnString = returnString + "Energy:    " + status.get("Energy") + "/" + MAX_STAT.get("Energy") + "\n";
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
