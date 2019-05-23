package main.CrewMemberTypes;

import java.util.HashMap;

import main.Ship;

/**
 * A robot who has become a crew member.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class Robot extends CrewMember {

	/** The ship which this robot is on. */
	private Ship ship;
	
	
	/**
	 * Class constructor.
	 * @param tempShip the ship to create this crew member for
	 */
	public Robot(Ship tempShip) {
		super(tempShip);
		ship = tempShip;
		createDefaultRobot();
	}
	
	
	/**
	 * Class constructor which allows for a custom name for the robot.
	 * @param tempShip the ship to create this crew member for
	 * @param tempName what the name of the crew member should be
	 */
	public Robot(Ship tempShip, String tempName) {
		super(tempShip);
		ship = tempShip;
		createDefaultRobot();
		if (tempName.matches("[0-9]+")) {
			tempName = "Robot #" + tempName;
		}
		super.name = tempName;
	}
	
	
	/**
	 * Adjusts all default values to create a new robot.
	 */
	private void createDefaultRobot() {
		HashMap<String, Integer> maxStats = new HashMap<String, Integer>();
		maxStats.put("Health", 10);
		maxStats.put("Energy", 5);
		maxStats.put("Nutrition", 10);
		
		HashMap<String, String> typeInfo = new HashMap<String, String>();
		typeInfo.put("Type", "Robot");
		typeInfo.put("Strength", "2 nutrition restored at end of each day");
		typeInfo.put("Weakness", "5 less maximum energy (5 Energy)");

		super.MAX_STAT = maxStats;
		super.name = "Terminator";
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
		super.addNutrition(2);
	}


}
