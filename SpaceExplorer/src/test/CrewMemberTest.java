package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import main.CrewMember;
import main.Ship;



abstract class CrewMemberTest {
	
	Ship newShip = new Ship();
	CrewMember crewMember;
	
	void setCrewMember(CrewMember newMember) {
		crewMember = newMember;
	}
	
	@Test
	abstract void testEndDay();

	@Test
	void testGetStatus() {
		assertTrue(crewMember.getStatus().equals(crewMember.getMaxStats()));
		HashMap <String, Integer> currentStats = crewMember.getStatus();
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
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
		System.out.println(crewMember.searchPlanet().toString());
	}

	@Test
	void testUseItem() {
		crewMember.setHasSpacePlague(true);
		newShip.getInventory().put(newShip.SPACE_PLAGUE_CURE, 2);
		System.out.println("---------- testUseItem START TEST ----------");
		int initialActions = crewMember.getActions();
		Object initialInventory = newShip.getInventory().clone();
		System.out.println("---------- testUseItem TEST USE ITEM TWICE ----------");
		assertTrue(crewMember.useItem());
		assertFalse(initialInventory.equals(newShip.getInventory().clone()));
		assertFalse(crewMember.getHasSpacePlague());
		initialActions--;
		assertEquals(crewMember.getActions(), initialActions);
		
		crewMember.setHasSpacePlague(true);
		initialInventory = newShip.getInventory().clone();
		assertTrue(crewMember.useItem());
		assertFalse(initialInventory.equals(newShip.getInventory().clone()));
		assertFalse(crewMember.getHasSpacePlague());
		initialActions--;
		assertEquals(crewMember.getActions(), initialActions);
		System.out.println("---------- testUseItem TEST NO ITEMS IN INVENTORY ----------");
		assertFalse(crewMember.useItem());
		System.out.println("---------- testUseItem TEST DONT USE ITEM ----------");
		newShip.getInventory().put(newShip.SPACE_PLAGUE_CURE, 2);
		initialInventory = newShip.getInventory();
		assertFalse(crewMember.useItem());
		assertTrue(initialInventory.equals(newShip.getInventory().clone()));
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
		CrewMember newMember = new main.CrewMemberTypes.Human(newShip, "Second Human");
		newShip.getCrewMembers().add(crewMember);
		newShip.getCrewMembers().add(newMember);
		System.out.println("---------- testPilotShip START TEST ----------");
		int initialActions = crewMember.getActions() + newMember.getActions();
		System.out.println("---------- testPilotShip TEST PILOT FROM Donald Trump ----------");
		assertTrue(crewMember.pilotShip());
		assertEquals(crewMember.getActions() + newMember.getActions(), initialActions - 2);
		System.out.println("---------- testPilotShip TEST PILOT FROM Second Human ----------");
		assertTrue(newMember.pilotShip());
		assertEquals(crewMember.getActions() + newMember.getActions(), initialActions - 4);
		System.out.println("---------- testPilotShip TEST NO OPTIONS ----------");
		assertFalse(crewMember.pilotShip());
		assertFalse(newMember.pilotShip());
		newMember.endDay();
		crewMember.endDay();
		System.out.println("---------- testPilotShip TEST DONT CHOOSE ANYONE ----------");
		assertFalse(crewMember.pilotShip());
		System.out.println("---------- testPilotShip END TEST ----------");
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
