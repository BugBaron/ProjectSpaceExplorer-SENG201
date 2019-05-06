package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import main.Ship;
import main.CrewMemberTypes.CrewMember;

class CrewMemberParameterizedTest {
	
	private static Ship newShip;
	private static ArrayList<ArrayList<Object>> testCases;
	
	@BeforeAll
	private static void setUpBeforeAll() throws FileNotFoundException {
		newShip = new Ship();
		newShip.setScanner(new Scanner(new File("src/testInput")));
		testCases = new ArrayList<ArrayList<Object>>(12);
		testCases.add(CrewMemberTypeParams.getHuman(newShip, ""));
		testCases.add(CrewMemberTypeParams.getHuman(newShip, "Named Human"));
		testCases.add(CrewMemberTypeParams.getRobot(newShip, ""));
		testCases.add(CrewMemberTypeParams.getRobot(newShip, "Named Robot"));
		testCases.add(CrewMemberTypeParams.getCyborg(newShip, ""));
		testCases.add(CrewMemberTypeParams.getCyborg(newShip, "Named Cyborg"));
		testCases.add(CrewMemberTypeParams.getAlien(newShip, ""));
		testCases.add(CrewMemberTypeParams.getAlien(newShip, "Named Alien"));
		testCases.add(CrewMemberTypeParams.getLizard(newShip, ""));
		testCases.add(CrewMemberTypeParams.getLizard(newShip, "Named Lizard"));
		testCases.add(CrewMemberTypeParams.getUnicorn(newShip, ""));
		testCases.add(CrewMemberTypeParams.getUnicorn(newShip, "Named Unicorn"));
	}
	
	@AfterAll
	private static void tearDownAfterAll() {
		System.setIn(System.in);
	}

	
	@ParameterizedTest(name = "Test {index}: {4}")
	@MethodSource("testEndDay")
	void testEndDay(CrewMember crewMember, int healthRegen, int energyRegen, int nutritionRegen, String name) {
		HashMap<String, Integer> statusBefore = new HashMap<String, Integer>();
		HashMap<String, Integer> statusAfter = new HashMap<String, Integer>();
		crewMember.setHasSpacePlague(false);
		statusBefore.putAll(crewMember.getStatus());
		crewMember.endDay();
		statusAfter.putAll(crewMember.getStatus());
		statusAfter.put("Health", statusAfter.get("Health") + 2 - healthRegen);
		assertTrue(statusBefore.equals(statusAfter));
		crewMember.setHasSpacePlague(true);
		crewMember.addHealth(2);
		crewMember.endDay();
		statusAfter.putAll(crewMember.getStatus());
		statusAfter.put("Health", statusAfter.get("Health") + 3 - healthRegen);
		assertTrue(statusBefore.equals(statusAfter));
		newShip.addShipShields(-5);
		crewMember.addHealth(3);
		crewMember.endDay();
		statusAfter.putAll(crewMember.getStatus());
		statusAfter.put("Health", statusAfter.get("Health") + 4 - healthRegen);
		assertTrue(statusBefore.equals(statusAfter));
		crewMember.addHealth(4);
		crewMember.addEnergy(-20);
		crewMember.endDay();
		statusAfter.putAll(crewMember.getStatus());
		statusAfter.put("Health", statusAfter.get("Health") + 5 - healthRegen);
		assertEquals((int) statusAfter.get("Energy"), energyRegen);
		assertEquals(statusBefore.get("Health"), statusAfter.get("Health"));
		crewMember.addEnergy(20);
		crewMember.addHealth(5);
		crewMember.addNutrition(-20);
		crewMember.endDay();
		statusAfter.putAll(crewMember.getStatus());
		assertEquals((int) statusAfter.get("Nutrition"), nutritionRegen);
		statusAfter.put("Health", statusAfter.get("Health") + 5 - healthRegen);
		crewMember.addNutrition(20);
		if (energyRegen < 1) {
			statusAfter.put("Energy", statusAfter.get("Energy") + 1);
		}
		assertEquals(statusBefore.get("Health"), statusAfter.get("Health"));
		assertEquals(statusBefore.get("Energy"), statusAfter.get("Energy"));
		newShip.addShipShields(5);
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetStatus(CrewMember crewMember, String name) {
		assertTrue(crewMember.getStatus().equals(crewMember.getMaxStats()));
		HashMap <String, Integer> currentStats = new HashMap <String, Integer>();
		currentStats.putAll(crewMember.getStatus());
		crewMember.addHealth(-1);
		crewMember.addEnergy(-1);
		crewMember.addNutrition(-1);
		currentStats.put("Health", currentStats.get("Health") - 1);
		currentStats.put("Energy", currentStats.get("Energy") - 1);
		currentStats.put("Nutrition", currentStats.get("Nutrition") - 1);
		assertTrue(crewMember.getStatus().equals(currentStats));
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testAddHealth(CrewMember crewMember, String name) {
		crewMember.addHealth(-20);
		assertEquals((int)crewMember.getStatus().get("Health"), 0);
		crewMember.addHealth(4);
		assertEquals((int)crewMember.getStatus().get("Health"), 4);
		crewMember.addHealth(-2);
		assertEquals((int)crewMember.getStatus().get("Health"), 2);
		crewMember.addHealth(20);
		assertEquals(crewMember.getStatus().get("Health"), crewMember.getMaxStats().get("Health"));
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testAddEnergy(CrewMember crewMember, String name) {
		crewMember.addEnergy(-20);
		assertEquals((int) crewMember.getStatus().get("Energy"), 0);
		crewMember.addEnergy(4);
		assertEquals((int) crewMember.getStatus().get("Energy"), 4);
		crewMember.addEnergy(-2);
		assertEquals((int) crewMember.getStatus().get("Energy"), 2);
		crewMember.addEnergy(20);
		assertEquals(crewMember.getStatus().get("Energy"), crewMember.getMaxStats().get("Energy"));
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testAddNutrition(CrewMember crewMember, String name) {
		crewMember.addNutrition(-20);
		assertEquals((int) crewMember.getStatus().get("Nutrition"), 0);
		crewMember.addNutrition(4);
		assertEquals((int) crewMember.getStatus().get("Nutrition"), 4);
		crewMember.addNutrition(-2);
		assertEquals((int) crewMember.getStatus().get("Nutrition"), 2);
		crewMember.addNutrition(20);
		assertEquals(crewMember.getStatus().get("Nutrition"), crewMember.getMaxStats().get("Nutrition"));
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetActions(CrewMember crewMember, String name) {
		assertEquals((int)crewMember.getActions(), 2);
		crewMember.sleep();
		assertEquals((int)crewMember.getActions(), 1);
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testSearchPlanet(CrewMember crewMember, String name) {
		for (int i = 0; i < 10; i++) {
			int initialEnergy = crewMember.getStatus().get("Energy");
			int initialNutrition = crewMember.getStatus().get("Nutrition");
			int initialActions = crewMember.getActions();
			System.out.println(crewMember.searchPlanet().toString());
			assertEquals(initialEnergy - crewMember.getStatus().get("Energy"), 1);
			assertEquals(initialNutrition - crewMember.getStatus().get("Nutrition"), 1);
			assertEquals(initialActions - crewMember.getActions(), 1);
			crewMember.endDay();
			crewMember.getStatus().putAll(crewMember.getMaxStats());
		}
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testUseItem(CrewMember crewMember, String name) {
		crewMember.setHasSpacePlague(true);
		newShip.getInventory().put(newShip.SPACE_PLAGUE_CURE, 2);
		System.out.println("---------- testUseItem START TEST ----------");
		int initialActions = crewMember.getActions();
		int initialEnergy = crewMember.getStatus().get("Energy");
		int initialNutrition = crewMember.getStatus().get("Nutrition");
		Object initialInventory = newShip.getInventory().clone();
		System.out.println("---------- testUseItem TEST USE ITEM TWICE ----------");
		assertTrue(crewMember.useItem());
		assertFalse(initialInventory.equals(newShip.getInventory()));
		assertFalse(crewMember.getHasSpacePlague());
		assertEquals(initialActions - crewMember.getActions(), 1);
		assertEquals(initialEnergy - crewMember.getStatus().get("Energy"), 1);
		assertEquals(initialNutrition - crewMember.getStatus().get("Nutrition"), 1);
		
		crewMember.setHasSpacePlague(true);
		initialInventory = newShip.getInventory().clone();
		assertTrue(crewMember.useItem());
		assertFalse(initialInventory.equals(newShip.getInventory().clone()));
		assertFalse(crewMember.getHasSpacePlague());
		assertEquals(initialActions - crewMember.getActions(), 2);
		System.out.println("---------- testUseItem TEST NO ITEMS IN INVENTORY ----------");
		assertFalse(crewMember.useItem());
		System.out.println("---------- testUseItem TEST DONT USE ITEM ----------");
		newShip.getInventory().put(newShip.SPACE_PLAGUE_CURE, 2);
		initialInventory = newShip.getInventory();
		assertFalse(crewMember.useItem());
		assertTrue(initialInventory.equals(newShip.getInventory().clone()));
		assertEquals(initialActions - crewMember.getActions(), 2);
		System.out.println("---------- testUseItem END TEST ----------");
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testSleep(CrewMember crewMember, String name) {
		crewMember.addEnergy(-5);
		int initialEnergy = crewMember.getStatus().get("Energy");
		int initialNutrition = crewMember.getStatus().get("Nutrition");
		int initialActions = crewMember.getActions();
		crewMember.sleep();
		assertEquals(crewMember.getStatus().get("Energy") - initialEnergy, 4);
		assertEquals(initialNutrition - crewMember.getStatus().get("Nutrition"), 1);
		assertEquals(initialActions - crewMember.getActions(), 1);
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {2}")
	@MethodSource("testRepairShip")
	void testRepairShip(CrewMember crewMember, int repairAmount, String name) {
		int initialShields = newShip.getShipShields();
		int initialEnergy = crewMember.getStatus().get("Energy");
		int initialNutrition = crewMember.getStatus().get("Nutrition");
		int initialActions = crewMember.getActions();
		crewMember.repairShip();
		assertEquals(initialEnergy - crewMember.getStatus().get("Energy"), 1);
		assertEquals(initialNutrition - crewMember.getStatus().get("Nutrition"), 1);
		assertEquals(initialActions - crewMember.getActions(), 1);
		assertEquals(newShip.getShipShields() - initialShields, repairAmount);
		newShip.addShipShields(-repairAmount);
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testPilotShip(CrewMember crewMember, String name) {
		CrewMember newMember = new main.CrewMemberTypes.Human(newShip, "Second Human");
		newShip.getCrewMembers().add(crewMember);
		newShip.getCrewMembers().add(newMember);
		System.out.println("---------- testPilotShip START TEST ----------");
		int initialEnergy = crewMember.getStatus().get("Energy") + newMember.getStatus().get("Energy");
		int initialNutrition = crewMember.getStatus().get("Nutrition") + newMember.getStatus().get("Nutrition");
		int initialActions = crewMember.getActions() + newMember.getActions();
		System.out.println("---------- testPilotShip TEST PILOT FROM " + crewMember.getName() + " ----------");
		assertTrue(crewMember.pilotShip());
		int finalEnergy = crewMember.getStatus().get("Energy") + newMember.getStatus().get("Energy");
		int finalNutrition = crewMember.getStatus().get("Nutrition") + newMember.getStatus().get("Nutrition");
		assertEquals(crewMember.getActions() + newMember.getActions(), initialActions - 2);
		assertEquals(finalEnergy, initialEnergy - 2);
		assertEquals(finalNutrition, initialNutrition - 2);
		System.out.println("---------- testPilotShip TEST PILOT FROM Second Human ----------");
		assertTrue(newMember.pilotShip());
		assertEquals(crewMember.getActions() + newMember.getActions(), initialActions - 4);
		System.out.println("---------- testPilotShip TEST NO OPTIONS ----------");
		assertFalse(crewMember.pilotShip());
		assertFalse(newMember.pilotShip());
		newMember.endDay();
		crewMember.endDay();
		System.out.println("---------- testPilotShip TEST DONT CHOOSE ANYONE ----------");
		initialEnergy = crewMember.getStatus().get("Energy") + newMember.getStatus().get("Energy");
		initialNutrition = crewMember.getStatus().get("Nutrition") + newMember.getStatus().get("Nutrition");
		initialActions = crewMember.getActions() + newMember.getActions();
		assertFalse(crewMember.pilotShip());
		finalEnergy = crewMember.getStatus().get("Energy") + newMember.getStatus().get("Energy");
		finalNutrition = crewMember.getStatus().get("Nutrition") + newMember.getStatus().get("Nutrition");
		assertEquals(crewMember.getActions() + newMember.getActions(), initialActions);
		assertEquals(finalEnergy, initialEnergy);
		assertEquals(finalNutrition, initialNutrition);
		System.out.println("---------- testPilotShip END TEST ----------");
		newShip.getCrewMembers().remove(crewMember);
		newShip.getCrewMembers().remove(newMember);
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetName(CrewMember crewMember, String name) {
		assertEquals(crewMember.getName(), name);
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetOrSetHasSpacePlague(CrewMember crewMember, String name) {
		crewMember.setHasSpacePlague(false);
		assertEquals(crewMember.getHasSpacePlague(), false);
		crewMember.setHasSpacePlague(true);
		assertEquals(crewMember.getHasSpacePlague(), true);
		crewMember.setHasSpacePlague(false);
		assertEquals(crewMember.getHasSpacePlague(), false);
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testGetTypeInfo")
	void testGetTypeInfo(CrewMember crewMember, String name, String type, String strength, String weakness) {
		Map <String, String> typeInfo = crewMember.getTypeInfo();
		assertEquals(typeInfo.get("Type"), type);
		assertEquals(typeInfo.get("Strength"), strength);
		assertEquals(typeInfo.get("Weakness"), weakness);
	}

	
	@ParameterizedTest(name = "Test {index}: {4}")
	@MethodSource("testGetMaxStats")
	void testGetMaxStats(CrewMember crewMember, int maxHealth, int maxEnergy, int maxNutrition, String name) {
		Map <String, Integer> maxStats = crewMember.getMaxStats();
		assertEquals((int) maxStats.get("Health"), maxHealth);
		assertEquals((int) maxStats.get("Energy"), maxEnergy);
		assertEquals((int) maxStats.get("Nutrition"), maxNutrition);
	}

	
	@ParameterizedTest(name = "Test {index}: {4}")
	@MethodSource("testToString")
	void testToString(CrewMember crewMember, int maxHealth, int maxEnergy, int maxNutrition, String name, String type, String strength, String weakness) {
		String actualString = name + ", " + type + "\nHealth: " + maxHealth + "/" + maxHealth + "\nEnergy: " + maxEnergy + "/" + maxEnergy + "\nNutrition: " + maxNutrition + "/" + maxNutrition + "\nStrength: " + strength + "\nWeakness: " + weakness;
		if (crewMember.getHasSpacePlague()) {
			actualString = actualString + "\nAfflicted by space plague";
		}
		assertEquals(actualString, crewMember.toString());
		crewMember.addHealth(-1);
		crewMember.addEnergy(-1);
		crewMember.addNutrition(-1);
		crewMember.setHasSpacePlague(true);
		actualString = name + ", " + type + "\nHealth: " + (maxHealth - 1) + "/" + maxHealth + "\nEnergy: " + (maxEnergy - 1) + "/" + maxEnergy + "\nNutrition: " + (maxNutrition - 1) + "/" + maxNutrition + "\nStrength: " + strength + "\nWeakness: " + weakness + "\nAfflicted by space plague";
		assertEquals(actualString, crewMember.toString());
		crewMember.addHealth(1);
		crewMember.addEnergy(1);
		crewMember.addNutrition(1);
		crewMember.setHasSpacePlague(false);
	}
	
	
	public static Stream<Arguments> testEndDay() {
		Stream.Builder<Arguments> builder = Stream.builder();
		for (int i = 0; i < testCases.size(); i++) {
			builder.add(Arguments.of(testCases.get(i).get(0), testCases.get(i).get(4), testCases.get(i).get(5), testCases.get(i).get(6), testCases.get(i).get(8)));
		}
		return builder.build();
	}
	
	
	public static Stream<Arguments> testRepairShip() {
		Stream.Builder<Arguments> builder = Stream.builder();
		for (int i = 0; i < testCases.size(); i++) {
			builder.add(Arguments.of(testCases.get(i).get(0), testCases.get(i).get(7), testCases.get(i).get(8)));
		}
		return builder.build();
	}
	
	
	public static Stream<Arguments> testGetTypeInfo() {
		Stream.Builder<Arguments> builder = Stream.builder();
		for (int i = 0; i < testCases.size(); i++) {
			builder.add(Arguments.of(testCases.get(i).get(0), testCases.get(i).get(8), testCases.get(i).get(9), testCases.get(i).get(10), testCases.get(i).get(11)));
		}
		return builder.build();
	}
	
	
	public static Stream<Arguments> testGetMaxStats() {
		Stream.Builder<Arguments> builder = Stream.builder();
		for (int i = 0; i < testCases.size(); i++) {
			builder.add(Arguments.of(testCases.get(i).get(0), testCases.get(i).get(1), testCases.get(i).get(2), testCases.get(i).get(3), testCases.get(i).get(8)));
		}
		return builder.build();
	}
	
	
	public static Stream<Arguments> testToString() {
		Stream.Builder<Arguments> builder = Stream.builder();
		for (int i = 0; i < testCases.size(); i++) {
			builder.add(Arguments.of(testCases.get(i).get(0), testCases.get(i).get(1), testCases.get(i).get(2), testCases.get(i).get(3), testCases.get(i).get(8), testCases.get(i).get(9), testCases.get(i).get(10), testCases.get(i).get(11)));
		}
		return builder.build();
	}
	
	
	public static Stream<Arguments> testDefault() {
		Stream.Builder<Arguments> builder = Stream.builder();
		for (int i=0; i < testCases.size(); i++) {
			builder.add(Arguments.of(testCases.get(i).get(0), testCases.get(i).get(8)));
		}
		return builder.build();
	}
}
