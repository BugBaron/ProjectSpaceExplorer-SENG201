package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import main.Consumable;
import main.Inventory;

class InventoryTest {
	
	private Inventory inventory;

	@Test
	void testGetItems() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		HashMap<Consumable, Integer> tempInventory = new HashMap<Consumable, Integer>();
		tempInventory.put(new Consumable("Space Plague Cure"), 1);
		tempInventory.put(new Consumable("Band-Aid"), 2);
		inventory = new Inventory(tempInventory);
		
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testAddItem() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetKeys() {
		fail("Not yet implemented");
	}

}
