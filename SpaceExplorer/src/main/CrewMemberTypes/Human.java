package main.CrewMemberTypes;

import java.util.HashMap;

import main.CrewMember;
import main.Ship;

public class Human extends CrewMember {

	
	/**
	 * Class constructor
	 * @param tempShip the ship to create this crew member for
	 */
	public Human(Ship tempShip) {
		super(tempShip);
		createDefaultHuman();
	}
		
	
	/**
	 * Class constructor which allows for a custom name for the human
	 * @param tempShip the ship to create this crew member for
	 * @param tempName what the name of the crew member should be
	 */
	public Human(Ship tempShip, String tempName) {
		super(tempShip);
		createDefaultHuman();
		super.name = tempName;
	}
	
	
	private void createDefaultHuman() {
		HashMap <String, Integer> maxStats = new HashMap <String, Integer>();
		maxStats.put("Health", 8);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 10);
		
		HashMap <String, String> typeInfo = new HashMap <String, String>();
		typeInfo.put("Type", "Human");
		typeInfo.put("Strength", "1 health restored at end of each day");
		typeInfo.put("Weakness", "2 less maximum health (8 Health)");

		super.MAX_STAT = maxStats;
		super.REPAIR_AMOUNT = 2;
		super.spacePlague = false;
		super.name = "Donald Trump";
		super.TYPE_INFO = typeInfo;
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
		if (super.ship.getShipShields() == 0) {
			super.addHealth(-1);
		}
	}


}
