package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.CrewMemberTypes.CrewMember;
import main.CrewMemberTypes.Human;

class HumanTest extends CrewMemberTest {

	CrewMember newHuman;
	
	@BeforeEach
	void setUp() throws Exception {
		super.setCrewMember(new Human(super.newShip));
		newHuman = super.crewMember;
	}


	@Test
	@Override
	void testEndDay() {
		Map <String, Integer> statusBefore = newHuman.getStatus();
		newHuman.endDay();
		Map <String, Integer> statusAfter = newHuman.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 1);
		assertTrue(statusBefore.equals(statusAfter));
		newHuman = new Human(newShip);
		newHuman.setHasSpacePlague(true);
		newHuman.endDay();
		statusAfter = newHuman.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 2);
		assertTrue(statusBefore.equals(statusAfter));
		newHuman = new Human(newShip);
		newShip.addShipShields(-5);
		newHuman.setHasSpacePlague(true);
		newHuman.endDay();
		statusAfter = newHuman.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 3);
		assertTrue(statusBefore.equals(statusAfter));
		newHuman = new Human(newShip);
		newHuman.setHasSpacePlague(true);
		newHuman.addEnergy(-20);
		newHuman.endDay();
		newHuman.addEnergy(20);
		statusAfter = newHuman.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 4);
		assertTrue(statusBefore.equals(statusAfter));
		newHuman = new Human(newShip);
		newHuman.setHasSpacePlague(true);
		newHuman.addNutrition(-20);
		newHuman.endDay();
		newHuman.addNutrition(20);
		statusAfter = newHuman.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 4);
		statusAfter.put("Energy", statusAfter.get("Energy") + 1);
		assertTrue(statusBefore.equals(statusAfter));
	}

	@Test
	@Override
	void testGetTypeInfo() {
		Map <String, String> typeInfo = newHuman.getTypeInfo();
		assertEquals(typeInfo.get("Type"), "Human");
		assertEquals(typeInfo.get("Strength"), "1 health restored at end of each day");
		assertEquals(typeInfo.get("Weakness"), "2 less maximum health (8 Health)");
	}

	
	@Test
	@Override
	void testGetMaxStats() {
		Map <String, Integer> maxStats = newHuman.getMaxStats();
		assertEquals((int) maxStats.get("Health"), 8);
		assertEquals((int) maxStats.get("Energy"), 10);
		assertEquals((int) maxStats.get("Nutrition"), 10);
	}

	
	@Test
	@Override
	void testToString() {
		String actualString = "Donald Trump, Human\nHealth: 8/8\nEnergy: 10/10\nNutrition: 10/10\nStrength: 1 health restored at end of each day\nWeakness: 2 less maximum health (8 Health)";
		assertEquals(actualString, newHuman.toString());
		newHuman = new Human(newShip, "Paul");
		newHuman.addHealth(-1);
		newHuman.addEnergy(-1);
		newHuman.addNutrition(-1);
		newHuman.setHasSpacePlague(true);
		actualString = "Paul, Human\nHealth: 7/8\nEnergy: 9/10\nNutrition: 9/10\nStrength: 1 health restored at end of each day\nWeakness: 2 less maximum health (8 Health)\nAfflicted by space plague";
		assertEquals(actualString, newHuman.toString());
	}
	
	@Test
	@Override
	void testGetName() {
		assertEquals(newHuman.getName(), "Donald Trump");
		newHuman = new Human(newShip, "Paul");
		assertEquals(newHuman.getName(), "Paul");
	}

	@Test
	@Override
	void testRepairShip() {
		newHuman.endDay();
		int initialShields = newShip.getShipShields();
		int initialEnergy = newHuman.getStatus().get("Energy");
		int initialNutrition = newHuman.getStatus().get("Nutrition");
		int numActions = newHuman.getActions();
		newHuman.repairShip();
		assertTrue(initialEnergy - newHuman.getStatus().get("Energy") == 1);
		assertTrue(initialNutrition - newHuman.getStatus().get("Nutrition") == 1);
		assertTrue(numActions - newHuman.getActions() ==  1);
		assertTrue(newShip.getShipShields() - initialShields == 2);
		newShip.addShipShields(-2);
		
	}

}
