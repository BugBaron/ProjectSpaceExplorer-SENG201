package main.CrewMemberTypes;

import java.util.HashMap;

import main.Ship;

/**
 * An alien who has become a crew member.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class Alien extends CrewMember {

	/** The ship which this alien is on. */
	private Ship ship;
	
	
	/**
	 * Class constructor.
	 * @param tempShip the ship to create this crew member for
	 */
	public Alien(Ship tempShip) {
		super(tempShip);
		ship = tempShip;
		createDefaultAlien();
	}
		
	
	/**
	 * Class constructor which allows for a custom name for the alien.
	 * @param tempShip the ship to create this crew member for
	 * @param tempName what the name of the crew member should be
	 */
	public Alien(Ship tempShip, String tempName) {
		super(tempShip);
		ship = tempShip;
		createDefaultAlien();
		if (tempName.matches("[0-9]+")) {
			tempName = "Alien #" + tempName;
		}
		super.name = tempName;
	}
	
	
	/**
	 * Adjusts all default values to create a new alien.
	 */
	private void createDefaultAlien() {
		HashMap<String, Integer> maxStats = new HashMap<String, Integer>();
		maxStats.put("Health", 12);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 8);
		
		HashMap<String, String> typeInfo = new HashMap<String, String>();
		typeInfo.put("Type", "Alien");
		typeInfo.put("Strength", "2 more maximum health (12 Health)");
		typeInfo.put("Weakness", "2 less maximum nutrition (8 Nutrition)");

		super.MAX_STAT = maxStats;
		super.name = "Steve Jobs";
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
