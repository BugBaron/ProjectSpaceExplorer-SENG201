package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.CrewMember;
import main.CrewMemberTypes.Cyborg;

class CyborgTest extends CrewMemberTest {

	CrewMember newCyborg;
	
	@BeforeEach
	void setUp() throws Exception {
		super.setCrewMember(new Cyborg(super.newShip));
		newCyborg = super.crewMember;
	}

	@Test
	@Override
	void testEndDay() {
		Map <String, Integer> statusBefore = newCyborg.getStatus();
		newCyborg.endDay();
		Map <String, Integer> statusAfter = newCyborg.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 2);
		assertTrue(statusBefore.equals(statusAfter));
		newCyborg = new Cyborg(newShip);
		newCyborg.setHasSpacePlague(true);
		newCyborg.endDay();
		statusAfter = newCyborg.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 3);
		assertTrue(statusBefore.equals(statusAfter));
		newCyborg = new Cyborg(newShip);
		newShip.addShipShields(-5);
		newCyborg.setHasSpacePlague(true);
		newCyborg.endDay();
		statusAfter = newCyborg.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 4);
		assertTrue(statusBefore.equals(statusAfter));
		newCyborg = new Cyborg(newShip);
		newCyborg.setHasSpacePlague(true);
		newCyborg.addEnergy(-20);
		newCyborg.endDay();
		newCyborg.addEnergy(20);
		statusAfter = newCyborg.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 5);
		assertTrue(statusBefore.equals(statusAfter));
		newCyborg = new Cyborg(newShip);
		newCyborg.setHasSpacePlague(true);
		newCyborg.addNutrition(-20);
		newCyborg.endDay();
		newCyborg.addNutrition(20);
		statusAfter = newCyborg.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 5);
		statusAfter.put("Energy", statusAfter.get("Energy") + 1);
		assertTrue(statusBefore.equals(statusAfter));
	}

	@Test
	@Override
	void testGetTypeInfo() {
		Map <String, String> typeInfo = newCyborg.getTypeInfo();
		assertEquals(typeInfo.get("Type"), "Cyborg");
		assertEquals(typeInfo.get("Strength"), "40% chance of finding a part when searching a planet");
		assertEquals(typeInfo.get("Weakness"), "10% chance to find food, medical supplies or money (10% for each)");
	}

	
	@Test
	@Override
	void testGetMaxStats() {
		Map <String, Integer> maxStats = newCyborg.getMaxStats();
		assertEquals((int) maxStats.get("Health"), 10);
		assertEquals((int) maxStats.get("Energy"), 10);
		assertEquals((int) maxStats.get("Nutrition"), 10);
	}

	
	@Test
	@Override
	void testToString() {
		String actualString = "Elon Musk, Cyborg\nHealth: 10/10\nEnergy: 10/10\nNutrition: 10/10\nStrength: 40% chance of finding a part when searching a planet\nWeakness: 10% chance to find food, medical supplies or money (10% for each)";
		assertEquals(actualString, newCyborg.toString());
		newCyborg = new Cyborg(newShip, "Paul");
		newCyborg.addHealth(-1);
		newCyborg.addEnergy(-1);
		newCyborg.addNutrition(-1);
		newCyborg.setHasSpacePlague(true);
		actualString = "Paul, Cyborg\nHealth: 9/10\nEnergy: 9/10\nNutrition: 9/10\nStrength: 40% chance of finding a part when searching a planet\nWeakness: 10% chance to find food, medical supplies or money (10% for each)\nAfflicted by space plague";
		assertEquals(actualString, newCyborg.toString());
	}
	
	@Test
	@Override
	void testGetName() {
		assertEquals(newCyborg.getName(), "Elon Musk");
		newCyborg = new Cyborg(newShip, "Paul");
		assertEquals(newCyborg.getName(), "Paul");
	}

	@Test
	@Override
	void testRepairShip() {
		newCyborg.endDay();
		int initialShields = newShip.getShipShields();
		int initialEnergy = newCyborg.getStatus().get("Energy");
		int initialNutrition = newCyborg.getStatus().get("Nutrition");
		int numActions = newCyborg.getActions();
		newCyborg.repairShip();
		assertTrue(initialEnergy - newCyborg.getStatus().get("Energy") == 1);
		assertTrue(initialNutrition - newCyborg.getStatus().get("Nutrition") == 1);
		assertTrue(numActions - newCyborg.getActions() ==  1);
		assertTrue(newShip.getShipShields() - initialShields == 2);
		newShip.addShipShields(-2);
		
	}

}
