package test.testingFolder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import main.Consumable;
import main.Ship;
import main.CrewMemberTypes.CrewMember;
import main.CrewMemberTypes.Human;

public class ConsumableTest {
	
	private final Consumable spacePlagueCure = new Consumable("Space Plague Cure");
	private final Consumable bandAid = new Consumable("Band-Aid");
	private final Consumable firstAidKit = new Consumable("First Aid Kit");
	private final Consumable spaceRation = new Consumable("Space Ration");
	private final Consumable water = new Consumable("Water");
	private final Consumable banana = new Consumable("Banana");
	private final Consumable alienMeat = new Consumable("Alien Meat");
	private final Consumable coffee = new Consumable("Coffee");
	private final Consumable egg = new Consumable("Egg");
	
	@Test
	void testGetName() {
		assertEquals("Space Plague Cure", spacePlagueCure.getName());
		assertEquals("Band-Aid", bandAid.getName());
		assertEquals("First Aid Kit", firstAidKit.getName());
		assertEquals("Space Ration", spaceRation.getName());
		assertEquals("Water", water.getName());
		assertEquals("Banana", banana.getName());
		assertEquals("Alien Meat", alienMeat.getName());
		assertEquals("Coffee", coffee.getName());
		assertEquals("Egg", egg.getName());
	}
	
	@Test
	void testGetPrice() {
		assertEquals(25, spacePlagueCure.getPrice());
		assertEquals(10, bandAid.getPrice());
		assertEquals(23, firstAidKit.getPrice());
		assertEquals(18, spaceRation.getPrice());
		assertEquals(10, water.getPrice());
		assertEquals(10, banana.getPrice());
		assertEquals(22, alienMeat.getPrice());
		assertEquals(13, coffee.getPrice());
		assertEquals(14, egg.getPrice());
	}
	
	@Test
	void testMedicalGetDescription() {
		String spacePlagueCureDescription = "Cures the Space Plague";
		String bandAidDescription = "Restores 2 Health";
		String firstAidKitDescription = "Restores 5 Health";
		assertEquals(spacePlagueCureDescription, spacePlagueCure.getDescription());
		assertEquals(bandAidDescription, bandAid.getDescription());
		assertEquals(firstAidKitDescription, firstAidKit.getDescription());
	}
	
	@Test
	void testFoodGetDescription() {
		String spaceRationDescription = "Restores 4 Nutrition";
		String waterDescription = "Restores 2 Nutrition";
		String bananaDescription = "Restores 2 Nutrition";
		String alienMeatDescription = "Restores 5 Nutrition";
		String coffeeDescription = "Restores 2 Nutrition\nand 1 Energy";
		String eggDescription = "Restores 3 Nutrition";
		assertEquals(spaceRationDescription, spaceRation.getDescription());
		assertEquals(waterDescription, water.getDescription());
		assertEquals(bananaDescription, banana.getDescription());
		assertEquals(alienMeatDescription, alienMeat.getDescription());
		assertEquals(coffeeDescription, coffee.getDescription());
		assertEquals(eggDescription, egg.getDescription());
	}
	
	@Test
	void testMedicalGetClassification() {
		String medical = "Medical";
		assertEquals(medical, spacePlagueCure.getClassification());
		assertEquals(medical, bandAid.getClassification());
		assertEquals(medical, firstAidKit.getClassification());
	}
	
	@Test
	void testFoodGetClassification() {
		String food = "Food";
		assertEquals(food, spaceRation.getClassification());
		assertEquals(food, water.getClassification());
		assertEquals(food, banana.getClassification());
		assertEquals(food, alienMeat.getClassification());
		assertEquals(food, coffee.getClassification(), food);
		assertEquals(food, egg.getClassification());
	}
	
	@Test
	void testSpacePlagueCureUseItem() {
		Ship ship = new Ship();
		CrewMember member = new Human(ship);
		member.setHasSpacePlague(true);
		spacePlagueCure.useItem(member);
		assertEquals(false, member.getHasSpacePlague());
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
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
		assertEquals(false, member.getHasSpacePlague()); //Ensure variables remain unchanged
	}
	
	@Test
	void testEquals() {
		Consumable thisBanana = new Consumable("Banana");
		Consumable thisBandAid = new Consumable("Band-Aid");
		assertTrue(thisBanana.equals(thisBanana));
		assertTrue(thisBanana.equals(banana));
		assertFalse(thisBanana.equals(thisBandAid));
	}
}
