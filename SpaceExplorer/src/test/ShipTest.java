package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Consumable;
import main.Ship;
import main.CrewMemberTypes.CrewMember;
import main.CrewMemberTypes.Human;

class ShipTest {
	
	private Ship ship;
	
	@BeforeEach
	void setUp() {
		ship = new Ship();
	}
	
	@Test
	void testGetName() {
		assertEquals("The Milano", ship.getName());
		Ship newShip = new Ship("Star Destroyer");
		assertEquals("Star Destroyer", newShip.getName());
	}

	@Test
	void testAddAndGetMoney() {
		assertEquals(100, ship.getMoney());
		ship.addMoney(10);
		assertEquals(110, ship.getMoney());
		ship.addMoney(-18);
		assertEquals(92, ship.getMoney());
		
	}

	@Test
	void testGetCrewMembers() {
		ArrayList<CrewMember> dummyMembers = new ArrayList<CrewMember>();
		assertEquals(dummyMembers, ship.getCrewMembers());
		CrewMember person = new Human(ship);
		ship.getCrewMembers().add(person);
		dummyMembers.add(person);
		assertEquals(dummyMembers, ship.getCrewMembers());
	}

	@Test
	void testInventory() {
		HashMap<Consumable, Integer> dummyInventory = new HashMap<Consumable, Integer>();
		assertEquals(dummyInventory, ship.getInventory());
		Consumable bandAid = new Consumable("Band-Aid");
		dummyInventory.put(bandAid, 1);
		ship.addItem(bandAid);
		assertEquals(dummyInventory, ship.getInventory());
		dummyInventory.put(bandAid, 2);
		ship.addItem(bandAid);
		assertEquals(dummyInventory, ship.getInventory());
		dummyInventory.put(bandAid, 1);
		ship.removeItem(bandAid);
		assertEquals(dummyInventory, ship.getInventory());
		dummyInventory.remove(bandAid);
		ship.removeItem(bandAid);
		assertEquals(dummyInventory, ship.getInventory());
	}

	@Test
	void testAddAndGetShipShields() {
		assertEquals(5, ship.getShipShields());
		ship.addShipShields(10);
		assertEquals(10, ship.getShipShields());
		ship.addShipShields(-15);
		assertEquals(0, ship.getShipShields());
		ship.addShipShields(7);
		assertEquals(7, ship.getShipShields());
	}

	@Test
	void testAddAndGetScore() {
		assertEquals(0, ship.getScore());
		ship.addScore(50);
		assertEquals(50, ship.getScore());
		ship.addScore(-30);
		assertEquals(20, ship.getScore());
		ship.addScore(-30);
		assertEquals(-10, ship.getScore());
	}

	@Test
	void testToString() {
		assertEquals("The Milano\nShield level: 5/10", ship.toString());
	}

}