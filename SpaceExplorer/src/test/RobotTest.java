package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.CrewMember;
import main.CrewMemberTypes.Robot;
import test.CrewMemberTest;

class RobotTest extends CrewMemberTest {

	CrewMember newRobot;
	
	@BeforeEach
	void setUp() throws Exception {
		super.setCrewMember(new Robot(super.newShip));
		newRobot = super.crewMember;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Override
	void testEndDay() {
		Map <String, Integer> statusBefore = newRobot.getStatus();
		newRobot.endDay();
		Map <String, Integer> statusAfter = newRobot.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 2);
		assertTrue(statusBefore.equals(statusAfter));
		newRobot = new Robot(newShip);
		newRobot.setHasSpacePlague(true);
		newRobot.endDay();
		statusAfter = newRobot.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 3);
		assertTrue(statusBefore.equals(statusAfter));
		newRobot = new Robot(newShip);
		newShip.addShipShields(-5);
		newRobot.setHasSpacePlague(true);
		newRobot.endDay();
		statusAfter = newRobot.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 4);
		assertTrue(statusBefore.equals(statusAfter));
		newRobot = new Robot(newShip);
		newRobot.setHasSpacePlague(true);
		newRobot.addEnergy(-20);
		newRobot.endDay();
		newRobot.addEnergy(20);
		statusAfter = newRobot.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 5);
		assertTrue(statusBefore.equals(statusAfter));
		newRobot = new Robot(newShip);
		newRobot.setHasSpacePlague(true);
		newRobot.addNutrition(-20);
		newRobot.endDay();
		newRobot.addNutrition(20);
		statusAfter = newRobot.getStatus();
		statusAfter.put("Health", statusAfter.get("Health") + 5);
		statusAfter.put("Energy", statusAfter.get("Energy") + 1);
		assertTrue(statusBefore.equals(statusAfter));
	}

	@Test
	@Override
	void testGetTypeInfo() {
		Map <String, String> typeInfo = newRobot.getTypeInfo();
		assertEquals(typeInfo.get("Type"), "Robot");
		assertEquals(typeInfo.get("Strength"), "2 nutrition restored at end of each day");
		assertEquals(typeInfo.get("Weakness"), "5 less maximum energy (5 Energy)");
	}

	
	@Test
	@Override
	void testGetMaxStats() {
		Map <String, Integer> maxStats = newRobot.getMaxStats();
		assertTrue(maxStats.get("Health") == 10);
		assertTrue(maxStats.get("Energy") == 5);
		assertTrue(maxStats.get("Nutrition") == 10);
	}

	
	@Test
	@Override
	void testToString() {
		String actualString = "Terminator, Robot\nHealth: 10/10\nEnergy: 5/5\nNutrition: 10/10\nStrength: 2 nutrition restored at end of each day\nWeakness: 5 less maximum energy (5 Energy)";
		assertEquals(actualString, newRobot.toString());
		newRobot = new Robot(newShip, "Paul");
		newRobot.addHealth(-1);
		newRobot.addEnergy(-1);
		newRobot.addNutrition(-1);
		newRobot.setHasSpacePlague(true);
		actualString = "Paul, Robot\nHealth: 9/10\nEnergy: 4/5\nNutrition: 9/10\nStrength: 2 nutrition restored at end of each day\nWeakness: 5 less maximum energy (5 Energy)\nAfflicted by space plague";
		assertEquals(actualString, newRobot.toString());
	}
	
	@Test
	@Override
	void testGetName() {
		assertEquals(newRobot.getName(), "Terminator");
		newRobot = new Robot(newShip, "Paul");
		assertEquals(newRobot.getName(), "Paul");
	}

	@Test
	@Override
	void testRepairShip() {
		newRobot.endDay();
		int initialShields = newShip.getShipShields();
		int initialEnergy = newRobot.getStatus().get("Energy");
		int numActions = newRobot.getActions();
		newRobot.repairShip();
		assertTrue(initialEnergy - newRobot.getStatus().get("Energy") == 1);
		assertTrue(numActions - newRobot.getActions() ==  1);
		assertTrue(newShip.getShipShields() - initialShields == 2);
		newShip.addShipShields(-2);
		
	}

}
