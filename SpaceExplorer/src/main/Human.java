package main;

import java.util.HashMap;

public class Human extends CrewMember {

	
	public Human(Ship ship) {
		super(ship);
		
		HashMap <String, Integer> maxStats = new HashMap <String, Integer>();
		maxStats.put("Health", 8);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 10);
		
		HashMap <String, String> typeInfo = new HashMap <String, String>();
		typeInfo.put("Type", "Human");
		typeInfo.put("Strength", "1 health restored at end of each day");
		typeInfo.put("Weakness", "2 less maximum health (8 Health)");
		super.setInitialVariables(maxStats, 2, false, "Donald Trump", typeInfo);
	}
	
	public Human(Ship ship, String name) {
		super(ship);
		HashMap <String, Integer> maxStats = new HashMap <String, Integer>();
		maxStats.put("Health", 8);
		maxStats.put("Energy", 10);
		maxStats.put("Nutrition", 10);
		
		HashMap <String, String> typeInfo = new HashMap <String, String>();
		typeInfo.put("Type", "Human");
		typeInfo.put("Strength", "1 health restored at end of each day");
		typeInfo.put("Weakness", "2 less maximum health (8 Health)");
		super.setInitialVariables(maxStats, 2, false, name, typeInfo);
	}
	
	
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
		if (super.getShip().getShipShields() == 0) {
			super.addHealth(-1);
		}
	}


}
