package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import main.CrewMember;
import main.Ship;
import main.Consumable;

abstract class CrewMemberTest {
	
	static Ship newShip = new Ship();
	CrewMember crewMember;
	
	void setCrewMember(CrewMember newMember) {
		crewMember = newMember;
	}
	
	@Test
	abstract void testEndDay();

	@Test
	void testGetStatus() {
		assertTrue(crewMember.getStatus().equals(crewMember.getMaxStats()));
		Map <String, Integer> currentStats = crewMember.getStatus();
		crewMember.addHealth(-1);
		crewMember.addEnergy(-1);
		crewMember.addNutrition(-1);
		currentStats.put("Health", currentStats.get("Health") - 1);
		currentStats.put("Energy", currentStats.get("Energy") - 1);
		currentStats.put("Nutrition", currentStats.get("Nutrition") - 1);
		assertTrue(crewMember.getStatus().equals(currentStats));
	}

	@Test
	void testAddHealth() {
		crewMember.addHealth(-20);
		assertTrue(crewMember.getStatus().get("Health") == 0);
		crewMember.addHealth(4);
		assertTrue(crewMember.getStatus().get("Health") == 4);
		crewMember.addHealth(-2);
		assertTrue(crewMember.getStatus().get("Health") == 2);
		crewMember.addHealth(20);
		assertTrue(crewMember.getStatus().get("Health") == crewMember.getMaxStats().get("Health"));
	}

	@Test
	void testAddEnergy() {
		crewMember.addEnergy(-20);
		assertTrue(crewMember.getStatus().get("Energy") == 0);
		crewMember.addEnergy(4);
		assertTrue(crewMember.getStatus().get("Energy") == 4);
		crewMember.addEnergy(-2);
		assertTrue(crewMember.getStatus().get("Energy") == 2);
		crewMember.addEnergy(20);
		assertTrue(crewMember.getStatus().get("Energy") == crewMember.getMaxStats().get("Energy"));
	}

	@Test
	void testAddNutrition() {
		crewMember.addNutrition(-20);
		assertTrue(crewMember.getStatus().get("Nutrition") == 0);
		crewMember.addNutrition(4);
		assertTrue(crewMember.getStatus().get("Nutrition") == 4);
		crewMember.addNutrition(-2);
		assertTrue(crewMember.getStatus().get("Nutrition") == 2);
		crewMember.addNutrition(20);
		assertTrue(crewMember.getStatus().get("Nutrition") == crewMember.getMaxStats().get("Nutrition"));
	}

	@Test
	void testGetActions() {
		crewMember.endDay();
		assertTrue(crewMember.getActions() == 2);
		crewMember.sleep();
		assertTrue(crewMember.getActions() == 1);
	}

	@Test
	void testSearchPlanet() {
		fail("Not yet implemented");
	}

	@Test
	void testUseItem() {
		crewMember.setHasSpacePlague(true);
		newShip.getInventory().put(newShip.SPACE_PLAGUE_CURE, 2);
		System.out.println("---------- testUseItem START TEST ----------");
		Object initialInventory = newShip.getInventory().clone();
		System.out.println("---------- testUseItem TEST USE ITEM TWICE ----------");
		crewMember.useItem();
		assertFalse(initialInventory.equals(newShip.getInventory().clone()));
		assertFalse(crewMember.getHasSpacePlague());
		
		crewMember.setHasSpacePlague(true);
		initialInventory = newShip.getInventory().clone();
		crewMember.useItem();
		assertFalse(initialInventory.equals(newShip.getInventory().clone()));
		assertFalse(crewMember.getHasSpacePlague());
		newShip.in.close();
		//System.out.println("---------- testUseItem TEST DONT USE ITEM ----------");
		newShip.getInventory().put(newShip.SPACE_PLAGUE_CURE, 2);
		/*crewMember.useItem();
		assertTrue(initialInventory.equals(newShip.getInventory().clone()));*/
		System.out.println("---------- testUseItem END TEST ----------");
	}

	@Test
	void testSleep() {
		crewMember.endDay();
		crewMember.addEnergy(-5);
		int initialEnergy = crewMember.getStatus().get("Energy");
		int numActions = crewMember.getActions();
		crewMember.sleep();
		assertTrue(crewMember.getStatus().get("Energy") - initialEnergy == 4);
		assertTrue(numActions - crewMember.getActions() ==  1);
	}

	@Test
	abstract void testRepairShip();

	@Test
	void testPilotShip() {
		fail("Not yet implemented");
	}

	@Test
	abstract void testGetName();

	@Test
	void testGetOrSetHasSpacePlague() {
		crewMember.setHasSpacePlague(false);
		assertEquals(crewMember.getHasSpacePlague(), false);
		crewMember.setHasSpacePlague(true);
		assertEquals(crewMember.getHasSpacePlague(), true);
	}

	@Test
	abstract void testGetTypeInfo();

	@Test
	abstract void testGetMaxStats();;

	@Test
	abstract void testToString();

}
