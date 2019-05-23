package test.testingFolder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import main.*;
import main.CrewMemberTypes.*;

class GameEnvironmentTest {

	private GameEnvironment gameEnvironment;
	private InOutHandler inOut;
	private Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		gameEnvironment = new GameEnvironment();
		inOut = gameEnvironment.getInOut();
		ship = new Ship();
	}

	@Test
	void testCreateShop() {
		Inventory shop = gameEnvironment.createShop();
		ArrayList<Consumable> shopKeys = shop.getKeys();
		for (Consumable item : shopKeys) {
			int quantity = shop.get(item);
			assertTrue(quantity >= 0);
			assertTrue(quantity <= 2);
		}
	}

	@Test
	void testGameEnvironment() {
		
	}

	@Test
	void testCreateGame() {
		gameEnvironment.createGame(3, ship);
		assertEquals(gameEnvironment.getDayString(), "Day number: 1/3");
		assertTrue(gameEnvironment.getInventory() instanceof Inventory);
	}

	@Test
	void testGetCrewMembers() {
		CrewMember crew1 = new Human(ship);
		CrewMember crew2 = new Cyborg(ship);
		CrewMember crew3 = new Lizard(ship);
		CrewMember crew4 = new Unicorn(ship);
		gameEnvironment.createGame(3, ship);
		ArrayList<CrewMember> gameCrew = gameEnvironment.getCrewMembers();
		ArrayList<CrewMember> shipCrew = ship.getCrewMembers();
		assertEquals(gameCrew, shipCrew);
	}

	@Test
	void testGetAvailableMembers() {
		CrewMember crew1 = new Human(ship);
		CrewMember crew2 = new Cyborg(ship);
		CrewMember crew3 = new Lizard(ship);
		CrewMember crew4 = new Unicorn(ship);
		crew4.completeAction();
		crew4.completeAction();
		ship.getCrewMembers().add(crew1);
		ship.getCrewMembers().add(crew2);
		ship.getCrewMembers().add(crew3);
		ship.getCrewMembers().add(crew4);
		gameEnvironment.createGame(3, ship);
		ArrayList<CrewMember> availableCrew = gameEnvironment.getAvailableMembers();
		assertTrue(availableCrew.contains(crew1));
		assertTrue(availableCrew.contains(crew2));
		assertTrue(availableCrew.contains(crew3));
		assertFalse(availableCrew.contains(crew4));
	}

	@Test
	void testGetShipString() {
		gameEnvironment.createGame(3, ship);
		String shipString = ship.toString() + "\n" + 
				"Spaceship pieces found: 0/2";
		assertEquals(shipString, gameEnvironment.getShipString());
	}

	@Test
	void testGetDayString() {
		gameEnvironment.createGame(5, ship);
		assertEquals(gameEnvironment.getDayString(), "Day number: 1/5");
		gameEnvironment.newDay();
		assertEquals(gameEnvironment.getDayString(), "Day number: 2/5");
		gameEnvironment.newDay();
		assertEquals(gameEnvironment.getDayString(), "Day number: 3/5");
	}

	@Test
	void testPurchaseItem() {
		gameEnvironment.createGame(3, ship);
		Inventory shop = gameEnvironment.getShop();
		ArrayList<Consumable> shopItems = shop.getKeys();
		Consumable item = shopItems.get(0);
		gameEnvironment.purchaseItem(item);
		assertEquals(gameEnvironment.getInventory().get(item), 1);
	}

	@Test
	void testGetInventory() {
		gameEnvironment.createGame(3,  ship);
		Inventory inventory = gameEnvironment.getInventory();
		assertTrue(inventory instanceof Inventory);
	}

	@Test
	void testGetShop() {
		gameEnvironment.createGame(3,  ship);
		Inventory shop = gameEnvironment.getShop();
		assertTrue(shop instanceof Inventory);
	}

	@Test
	void testGetMoney() {
		gameEnvironment.createGame(5, ship);
		assertEquals(gameEnvironment.getMoney(), 100);
		gameEnvironment.newDay();
		assertEquals(gameEnvironment.getMoney(), 120);
	}

	@Test
	void testNewDay() {
		gameEnvironment.createGame(3, ship);
		boolean completion = gameEnvironment.newDay();
		assertTrue(completion);
		assertEquals(gameEnvironment.getMoney(), 120);
		String dayString = "Day number: 2/3";
		assertEquals(gameEnvironment.getDayString(), dayString);
	}

	@Test
	void testSearchPlanet() {
		CrewMember crew1 = new Lizard(ship);
		gameEnvironment.createGame(3,  ship);
		String shipString = gameEnvironment.getShipString();
		String result = ship.toString() + "\n" + 
				"Spaceship pieces found: 0/2";
		assertEquals(shipString, result);
		gameEnvironment.searchPlanet(crew1);
		Object output = inOut.getOutput();
		if (output == "Mark Zuckerberg found a ship part!") {
			shipString = gameEnvironment.getShipString();
			result = ship.toString() + "\n" + 
					"Spaceship pieces found: 1/2";
			assertEquals(shipString, result);
		}
	}

	@Test
	void testUseItem() {
		CrewMember crew1 = new Human(ship);
		gameEnvironment.createGame(3,  ship);
		Inventory inventory = gameEnvironment.getInventory();
		inventory.addItem(new Consumable("Banana"));
		assertEquals(inventory.get(new Consumable("Banana")), 1);
		gameEnvironment.useItem(crew1, new Consumable("Banana"));
		assertEquals(inventory.get(new Consumable("Banana")), 0);
		assertEquals(crew1.getActions(), 1);
	}
	@Test
	void testSleep() {
		CrewMember crew1 = new Human(ship);
		gameEnvironment.createGame(3,  ship);
		gameEnvironment.sleep(crew1);
		assertTrue(crew1.getStatus().get("Energy") == 10);
		assertEquals(crew1.getActions(), 1);
		crew1.addEnergy(-8);
		gameEnvironment.sleep(crew1);
		assertTrue(crew1.getStatus().get("Energy") == 6);
		assertEquals(crew1.getActions(), 0);
	}

	@Test
	void testRepairShip() {
		CrewMember crew1 = new Human(ship);
		gameEnvironment.createGame(3,  ship);
		gameEnvironment.repairShip(crew1);
		String result = "The Milano\n" +
				"Shield level: 7/10";
		assertEquals(ship.toString(), result);
		assertEquals(crew1.getActions(), 1);
	}

	@Test
	void testPilotShip() {
		CrewMember crew1 = new Human(ship);
		CrewMember crew2 = new Cyborg(ship);
		gameEnvironment.createGame(5, ship);
		gameEnvironment.pilotShip(crew1, crew2);
		String expected = "Donald Trump and Elon Musk flew the ship to Titan";
		Object result = inOut.getOutput();
		assertEquals(expected, result);
	}

	@Test
	void testEndGame() {
		gameEnvironment.createGame(3, ship);
		gameEnvironment.endGame(true);
		Object output = inOut.getOutput();
		assertEquals(output, "Congratulations!");
		while (output != null) {
			output = inOut.getOutput();
		}
		gameEnvironment.endGame(false);
		output = inOut.getOutput();
		assertEquals(output, "Sorry, you lost!");
		output = inOut.getOutput();
		assertEquals(output, "Oh no! All of the members of your crew have died!");
		while (output != null) {
			output = inOut.getOutput();
		}
	}

	@Test
	void testPickRandomArrayListOfQ() {
		ArrayList<Consumable> items = new ArrayList<Consumable>();
		items.add(new Consumable("Banana"));
		items.add(new Consumable("First Aid Kit"));
		items.add(new Consumable("Space Plague Cure"));
		Object item = gameEnvironment.pickRandom(items);
		assertTrue(items.contains(item));
	}

	@Test
	void testPickRandomObjectArray() {
		Object[] items = {1, 2, 3};
		Object item = gameEnvironment.pickRandom(items);
		assertTrue(Arrays.stream(items).anyMatch(item::equals));
	}

}
