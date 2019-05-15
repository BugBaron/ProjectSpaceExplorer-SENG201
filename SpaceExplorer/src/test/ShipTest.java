package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertEquals(0, ship.getTotalScore());
		ship.addScore(50);
		assertEquals(50, ship.getTotalScore());
		ship.addScore(-30);
		assertEquals(20, ship.getTotalScore());
		ship.addScore(-30);
		assertEquals(-10, ship.getTotalScore());
		assertEquals(-10, ship.getDailyScore());
		assertEquals(-10, ship.getTotalScore());
		assertEquals(0, ship.getDailyScore());
	}

	@Test
	void testToString() {
		assertEquals("The Milano\nShield level: 5/10", ship.toString());
	}

}
