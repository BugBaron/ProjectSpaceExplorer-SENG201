package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.CrewMember;
import main.CrewMemberTypes.Alien;

class AlienTest extends CrewMemberTest {

	CrewMember newAlien;
	
	@BeforeEach
	void setUp() throws Exception {
		super.setCrewMember(new Alien(super.newShip));
		newAlien = super.crewMember;
	}

	@Test
	@Override
	void testEndDay() {
		Map <String, Integer> statusBefore = newAlien.getStatus();
		newAlien.endDay();
		Map <String, Integer> statusAfter = newAlien.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 2);
		assertTrue(statusBefore.equals(statusAfter));
		newAlien = new Alien(newShip);
		newAlien.setHasSpacePlague(true);
		newAlien.endDay();
		statusAfter = newAlien.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 3);
		assertTrue(statusBefore.equals(statusAfter));
		newAlien = new Alien(newShip);
		newShip.addShipShields(-5);
		newAlien.setHasSpacePlague(true);
		newAlien.endDay();
		statusAfter = newAlien.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 4);
		assertTrue(statusBefore.equals(statusAfter));
		newAlien = new Alien(newShip);
		newAlien.setHasSpacePlague(true);
		newAlien.addEnergy(-20);
		newAlien.endDay();
		newAlien.addEnergy(20);
		statusAfter = newAlien.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 5);
		assertTrue(statusBefore.equals(statusAfter));
		newAlien = new Alien(newShip);
		newAlien.setHasSpacePlague(true);
		newAlien.addNutrition(-20);
		newAlien.endDay();
		newAlien.addNutrition(20);
		statusAfter = newAlien.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 5);
		statusAfter.put("Energy", statusAfter.get("Energy") + 1);
		assertTrue(statusBefore.equals(statusAfter));
	}

	@Test
	@Override
	void testGetTypeInfo() {
		Map <String, String> typeInfo = newAlien.getTypeInfo();
		assertEquals(typeInfo.get("Type"), "Alien");
		assertEquals(typeInfo.get("Strength"), "2 more maximum health (12 Health)");
		assertEquals(typeInfo.get("Weakness"), "2 less maximum nutrition (8 Nutrition)");
	}

	
	@Test
	@Override
	void testGetMaxStats() {
		Map <String, Integer> maxStats = newAlien.getMaxStats();
		assertEquals((int) maxStats.get("Health"), 12);
		assertEquals((int) maxStats.get("Energy"), 10);
		assertEquals((int) maxStats.get("Nutrition"), 8);
	}

	
	@Test
	@Override
	void testToString() {
		String actualString = "Steve Jobs, Alien\nHealth: 12/12\nEnergy: 10/10\nNutrition: 8/8\nStrength: 2 more maximum health (12 Health)\nWeakness: 2 less maximum nutrition (8 Nutrition)";
		assertEquals(actualString, newAlien.toString());
		newAlien = new Alien(newShip, "Paul");
		newAlien.addHealth(-1);
		newAlien.addEnergy(-1);
		newAlien.addNutrition(-1);
		newAlien.setHasSpacePlague(true);
		actualString = "Paul, Alien\nHealth: 11/12\nEnergy: 9/10\nNutrition: 7/8\nStrength: 2 more maximum health (12 Health)\nWeakness: 2 less maximum nutrition (8 Nutrition)\nAfflicted by space plague";
		assertEquals(actualString, newAlien.toString());
	}
	
	@Test
	@Override
	void testGetName() {
		assertEquals(newAlien.getName(), "Steve Jobs");
		newAlien = new Alien(newShip, "Paul");
		assertEquals(newAlien.getName(), "Paul");
	}

	@Test
	@Override
	void testRepairShip() {
		newAlien.endDay();
		int initialShields = newShip.getShipShields();
		int initialEnergy = newAlien.getStatus().get("Energy");
		int initialNutrition = newAlien.getStatus().get("Nutrition");
		int numActions = newAlien.getActions();
		newAlien.repairShip();
		assertTrue(initialEnergy - newAlien.getStatus().get("Energy") == 1);
		assertTrue(initialNutrition - newAlien.getStatus().get("Nutrition") == 1);
		assertTrue(numActions - newAlien.getActions() ==  1);
		assertTrue(newShip.getShipShields() - initialShields == 2);
		newShip.addShipShields(-2);
		
	}

}
