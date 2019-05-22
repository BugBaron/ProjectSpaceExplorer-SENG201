package main.CrewMemberTypes;

import java.util.HashMap;

import main.Ship;

/**
 * A cyborg who has become a crew member
 * @author Daniel Harris and Rebekah McKinnon
 */
public class Cyborg extends CrewMember {

	/** The ship which this cyborg is on */
	Ship ship;
	
	
	/**
	 * Class constructor
	 * @param tempShip the ship to create this crew member for
	 */
	public Cyborg(Ship tempShip) {
		super(tempShip);
		ship = tempShip;
		createDefaultCyborg();
	}
		
	
	/**
	 * Class constructor which allows for a custom name for the cyborg
	 * @param tempShip the ship to create this crew member for
	 * @param tempName what the name of the crew member should be
	 */
	public Cyborg(Ship tempShip, String tempName) {
		super(tempShip);
		ship = tempShip;
		createDefaultCyborg();
		if (tempName.matches("[0-9]+")) {
			tempName = "Cyborg #" + tempName; 
		}
		super.name = tempName;
	}
	

	/**
	 * Adjusts all default values to create a new cyborg
	 */
	private void createDefaultCyborg() {
		HashMap <String, Integer> maxStats = new HashMap <String, Integer>();
		maxStats.put("Health", 10);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 10);
		
		HashMap <String, String> typeInfo = new HashMap <String, String>();
		typeInfo.put("Type", "Cyborg");
		typeInfo.put("Strength", "40% chance of finding a part when searching a planet");
		typeInfo.put("Weakness", "10% chance to find food, medical supplies or money (10% for each)");

		super.MAX_STAT = maxStats;
		super.name = "Elon Musk";
		super.TYPE_INFO = typeInfo;
		int[] newProbabilities = {4, 1, 1, 1, 3};
		super.SEARCHING_PROBABILITIES = newProbabilities;
		
		super.getStatus().put("Health", maxStats.get("Health"));
		super.getStatus().put("Energy", maxStats.get("Energy"));
		super.getStatus().put("Nutrition", maxStats.get("Nutrition"));
	}
	
	
	/**
	 * Completes all character related processes related to ending the day, 
	 * such as losing health naturally or from having the plague
	 */
	@Override
	public void endDay() {
		super.addHealth(-2);
		super.setActions(2);
		if (super.getStatus().get("Energy") == 0) {
			super.addHealth(-1);
		}
		if (super.getStatus().get("Nutrition") == 0) {
			super.addHealth(-1);
			super.addEnergy(-1);
		}
		if (super.getHasSpacePlague()) {
			super.addHealth(-1);
		}
		if (ship.getShipShields() == 0) {
			super.addHealth(-1);
		}
	}


}
