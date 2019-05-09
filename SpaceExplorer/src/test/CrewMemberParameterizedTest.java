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
import java.util.stream.Stream;

import main.Ship;
import main.CrewMemberTypes.CrewMember;

class CrewMemberParameterizedTest {
	
	private static Ship newShip;
	private static ArrayList<ArrayList<Object>> testCases;
	
	@BeforeAll
	private static void setUpBeforeAll() throws FileNotFoundException {
		newShip = new Ship();
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
		assertEquals(energyRegen, (int) statusAfter.get("Energy"));
		assertEquals(statusBefore.get("Health"), statusAfter.get("Health"));
		crewMember.addEnergy(20);
		crewMember.addHealth(5);
		crewMember.addNutrition(-20);
		crewMember.endDay();
		statusAfter.putAll(crewMember.getStatus());
		assertEquals(nutritionRegen, (int) statusAfter.get("Nutrition"));
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
		assertEquals(0, (int)crewMember.getStatus().get("Health"));
		crewMember.addHealth(4);
		assertEquals(4, (int)crewMember.getStatus().get("Health"));
		crewMember.addHealth(-2);
		assertEquals(2, (int)crewMember.getStatus().get("Health"));
		crewMember.addHealth(20);
		assertEquals(crewMember.getMaxStats().get("Health"), crewMember.getStatus().get("Health"));
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testAddEnergy(CrewMember crewMember, String name) {
		crewMember.addEnergy(-20);
		assertEquals(0, (int) crewMember.getStatus().get("Energy"));
		crewMember.addEnergy(4);
		assertEquals(4, (int) crewMember.getStatus().get("Energy"));
		crewMember.addEnergy(-2);
		assertEquals(2, (int) crewMember.getStatus().get("Energy"));
		crewMember.addEnergy(20);
		assertEquals(crewMember.getMaxStats().get("Energy"), crewMember.getStatus().get("Energy"));
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testAddNutrition(CrewMember crewMember, String name) {
		crewMember.addNutrition(-20);
		assertEquals(0, (int) crewMember.getStatus().get("Nutrition"));
		crewMember.addNutrition(4);
		assertEquals(4, (int) crewMember.getStatus().get("Nutrition"));
		crewMember.addNutrition(-2);
		assertEquals(2, (int) crewMember.getStatus().get("Nutrition"));
		crewMember.addNutrition(20);
		assertEquals(crewMember.getMaxStats().get("Nutrition"), crewMember.getStatus().get("Nutrition"));
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetActions(CrewMember crewMember, String name) {
		assertEquals(2, (int)crewMember.getActions());
		crewMember.completeAction();
		assertEquals(1, (int)crewMember.getActions());
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}
	
	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testGetRepairAmount")
	void testGetRepairAmount(CrewMember crewMember, int repairAmount, String name) {
		assertEquals(repairAmount, crewMember.getRepairAmount());
	}
	
	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testCompleteAction(CrewMember crewMember, String name) {
		int initialActions = crewMember.getActions();
		int initialEnergy = crewMember.getStatus().get("Energy");
		int initialNutrition = crewMember.getStatus().get("Nutrition");
		crewMember.completeAction();
		assertEquals(1, initialActions - crewMember.getActions());
		assertEquals(1, initialEnergy - crewMember.getStatus().get("Energy"));
		assertEquals(1, initialNutrition - crewMember.getStatus().get("Nutrition"));
		crewMember.endDay();
		crewMember.getStatus().putAll(crewMember.getMaxStats());
	}
	
	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetName(CrewMember crewMember, String name) {
		assertEquals(name, crewMember.getName());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testDefault")
	void testGetOrSetHasSpacePlague(CrewMember crewMember, String name) {
		crewMember.setHasSpacePlague(false);
		assertEquals(false, crewMember.getHasSpacePlague());
		crewMember.setHasSpacePlague(true);
		assertEquals(true, crewMember.getHasSpacePlague());
		crewMember.setHasSpacePlague(false);
		assertEquals(false, crewMember.getHasSpacePlague());
	}

	
	@ParameterizedTest(name = "Test {index}: {1}")
	@MethodSource("testGetTypeInfo")
	void testGetTypeInfo(CrewMember crewMember, String name, String type, String strength, String weakness) {
		Map <String, String> typeInfo = crewMember.getTypeInfo();
		assertEquals(type, typeInfo.get("Type"));
		assertEquals(strength, typeInfo.get("Strength"));
		assertEquals(weakness, typeInfo.get("Weakness"));
	}

	
	@ParameterizedTest(name = "Test {index}: {4}")
	@MethodSource("testGetMaxStats")
	void testGetMaxStats(CrewMember crewMember, int maxHealth, int maxEnergy, int maxNutrition, String name) {
		Map <String, Integer> maxStats = crewMember.getMaxStats();
		assertEquals(maxHealth, (int) maxStats.get("Health"));
		assertEquals(maxEnergy, (int) maxStats.get("Energy"));
		assertEquals(maxNutrition, (int) maxStats.get("Nutrition"));
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
	
	
	public static Stream<Arguments> testGetRepairAmount() {
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
