package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import main.Consumable;
import main.CrewMember;
import main.Ship;
import main.CrewMemberTypes.Human;

class ConsumableTest {
	
	Consumable spacePlagueCure = new Consumable("Space Plague Cure");
	Consumable bandAid = new Consumable("Band-Aid");
	Consumable firstAidKit = new Consumable("First Aid Kit");
	Consumable spaceRation = new Consumable("Space Ration");
	Consumable water = new Consumable("Water");
	Consumable banana = new Consumable("Banana");
	Consumable alienMeat = new Consumable("Alien Meat");
	Consumable coffee = new Consumable("Coffee");
	Consumable egg = new Consumable("Egg");
	
	@Test
	void testGetName() {
		assertEquals(spacePlagueCure.getName(), "Space Plague Cure");
		assertEquals(bandAid.getName(), "Band-Aid");
		assertEquals(firstAidKit.getName(), "First Aid Kit");
		assertEquals(spaceRation.getName(), "Space Ration");
		assertEquals(water.getName(), "Water");
		assertEquals(banana.getName(), "Banana");
		assertEquals(alienMeat.getName(), "Alien Meat");
		assertEquals(coffee.getName(), "Coffee");
		assertEquals(egg.getName(), "Egg");
	}
	
	@Test
	void testGetPrice() {
		assertEquals(spacePlagueCure.getPrice(), 25);
		assertEquals(bandAid.getPrice(), 10);
		assertEquals(firstAidKit.getPrice(), 23);
		assertEquals(spaceRation.getPrice(), 18);
		assertEquals(water.getPrice(), 10);
		assertEquals(banana.getPrice(), 10);
		assertEquals(alienMeat.getPrice(), 22);
		assertEquals(coffee.getPrice(), 13);
		assertEquals(egg.getPrice(), 14);
	}
	
	@Test
	void testMedicalGetDescription() {
		String spacePlagueCureDescription = "Cures the Space Plague";
		String bandAidDescription = "Restores 2 Health";
		String firstAidKitDescription = "Restores 5 Health";
		assertEquals(spacePlagueCure.getDescription(), spacePlagueCureDescription);
		assertEquals(bandAid.getDescription(), bandAidDescription);
		assertEquals(firstAidKit.getDescription(), firstAidKitDescription);
	}
	
	@Test
	void testFoodGetDescription() {
		String spaceRationDescription = "Restores 4 Nutrition";
		String waterDescription = "Restores 2 Nutrition";
		String bananaDescription = "Restores 2 Nutrition";
		String alienMeatDescription = "Restores 5 Nutrition";
		String coffeeDescription = "Restores 2 Nutrition and 1 Energy";
		String eggDescription = "Restores 3 Nutrition";
		assertEquals(spaceRation.getDescription(), spaceRationDescription);
		assertEquals(water.getDescription(),waterDescription);
		assertEquals(banana.getDescription(), bananaDescription);
		assertEquals(alienMeat.getDescription(), alienMeatDescription);
		assertEquals(coffee.getDescription(), coffeeDescription);
		assertEquals(egg.getDescription(), eggDescription);
	}
	
	@Test
	void testMedicalGetClassification() {
		String medical = "Medical Item";
		assertEquals(spacePlagueCure.getClassification(), medical);
		assertEquals(bandAid.getClassification(), medical);
		assertEquals(firstAidKit.getClassification(), medical);
	}
	
	@Test
	void testFoodGetClassification() {
		String food = "Food Item";
		assertEquals(spaceRation.getClassification(), food);
		assertEquals(water.getClassification(), food);
		assertEquals(banana.getClassification(), food);
		assertEquals(alienMeat.getClassification(), food);
		assertEquals(coffee.getClassification(), food);
		assertEquals(egg.getClassification(), food);
	}
	
	@Test
	void testSpacePlagueCureUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.setHasSpacePlague(true);
		spacePlagueCure.useItem(member);
		assertEquals(member.getHasSpacePlague(), false);
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); //Ensure variables remain unchanged
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
	}
	
	@Test
	void testBandAidUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addHealth(-2); //Human now has health 6
		bandAid.useItem(member); //Human now has health 8
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8);
		assertTrue(status.get("Nutrition")==10); //Ensure variables remain unchanged
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
	
	@Test
	void testFirstAidKitUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addHealth(-5); //Human now has health 3
		firstAidKit.useItem(member); //Human now has health 8
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8);
		assertTrue(status.get("Nutrition")==10); //Ensure variables remain unchanged
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
	
	@Test
	void testSpaceRationUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addNutrition(-4); //Human now has nutrition 6
		spaceRation.useItem(member); //Human now has nutrition 10
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); 
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
	
	@Test
	void testWaterUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addNutrition(-2); //Human now has nutrition 8
		water.useItem(member); //Human now has nutrition 10
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); 
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
	
	@Test
	void testBananaUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addNutrition(-2); //Human now has nutrition 8
		banana.useItem(member); //Human now has nutrition 10
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); 
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
	
	@Test
	void testAlienMeatUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addNutrition(-5); //Human now has nutrition 5
		alienMeat.useItem(member); //Human now has nutrition 10
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); 
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}

	@Test
	void testCoffeeUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addNutrition(-2); //Human now has nutrition 6
		member.addEnergy(-1); //Human now has energy 9
		coffee.useItem(member); //Human now has nutrition 10 and energy 10
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); 
		assertTrue(status.get("Energy")==10); 
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
	
	@Test
	void testEggUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.addNutrition(-3); //Human now has nutrition 7
		egg.useItem(member); //Human now has nutrition 10
		HashMap<String, Integer> status = member.getStatus();
		assertTrue(status.get("Health")==8); //Ensure variables remain unchanged
		assertTrue(status.get("Nutrition")==10); 
		assertTrue(status.get("Energy")==10); //Ensure variables remain unchanged
		assertEquals(member.getHasSpacePlague(), false); //Ensure variables remain unchanged
	}
}
