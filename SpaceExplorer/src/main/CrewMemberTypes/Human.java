package main.CrewMemberTypes;

import java.util.HashMap;

import main.Ship;

/**
 * A human who has become a crew member.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class Human extends CrewMember {

	/** The ship which this human is on. */
	private Ship ship;
	
	
	/**
	 * Class constructor.
	 * @param tempShip the ship to create this crew member for
	 */
	public Human(Ship tempShip) {
		super(tempShip);
		ship = tempShip;
		createDefaultHuman();
	}
	
	
	/**
	 * Class constructor which allows for a custom name for the human.
	 * @param tempShip the ship to create this crew member for
	 * @param tempName what the name of the crew member should be
	 */
	public Human(Ship tempShip, String tempName) {
		super(tempShip);
		ship = tempShip;
		createDefaultHuman();
		if (tempName.matches("[0-9]+")) {
			tempName = "Human #" + tempName;
		}
		super.name = tempName;
	}
	
	
	/**
	 * Adjusts all default values to create a new human.
	 */
	private void createDefaultHuman() {
		HashMap<String, Integer> maxStats = new HashMap<String, Integer>();
		maxStats.put("Health", 8);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 10);
		
		HashMap<String, String> typeInfo = new HashMap<String, String>();
		typeInfo.put("Type", "Human");
		typeInfo.put("Strength", "1 health restored at end of each day");
		typeInfo.put("Weakness", "2 less maximum health (8 Health)");

		super.MAX_STAT = maxStats;
		String name = "Donald Trump";
		super.name = name;
		super.TYPE_INFO = typeInfo;
		super.getStatus().put("Health", maxStats.get("Health"));
		super.getStatus().put("Energy", maxStats.get("Energy"));
		super.getStatus().put("Nutrition", maxStats.get("Nutrition"));
	}
	
	
	/**
	 * Completes all character related processes related to ending the day,
	 * such as losing health naturally or from having the plague.
	 */
	@Override
	public void endDay() {
		super.addHealth(-1);
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
