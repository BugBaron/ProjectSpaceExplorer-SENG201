package main.CrewMemberTypes;

import java.util.HashMap;

import main.CrewMember;
import main.Ship;

public class Unicorn extends CrewMember {

	
	/**
	 * Class constructor
	 * @param tempShip the ship to create this crew member for
	 */
	public Unicorn(Ship tempShip) {
		super(tempShip);
		createDefaultUnicorn();
	}
		
	
	/**
	 * Class constructor which allows for a custom name for the unicorn
	 * @param tempShip the ship to create this crew member for
	 * @param tempName what the name of the crew member should be
	 */
	public Unicorn(Ship tempShip, String tempName) {
		super(tempShip);
		createDefaultUnicorn();
		super.name = tempName;
	}
	
	
	private void createDefaultUnicorn() {
		HashMap <String, Integer> maxStats = new HashMap <String, Integer>();
		maxStats.put("Health", 10);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 10);
		
		HashMap <String, String> typeInfo = new HashMap <String, String>();
		typeInfo.put("Type", "Unicorn");
		typeInfo.put("Strength", "2 energy restored at end of each day");
		typeInfo.put("Weakness", "Starts with the space plague");

		super.MAX_STAT = maxStats;
		super.name = "Rapidash";
		super.TYPE_INFO = typeInfo;
		super.spacePlague = true;
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
		if (super.ship.getShipShields() == 0) {
			super.addHealth(-1);
		}
		super.addEnergy(2);
	}


}