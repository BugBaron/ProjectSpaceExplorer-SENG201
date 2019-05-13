package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Consumable;
import main.Inventory;

class InventoryTest {
	
	private Inventory inventory;
	@BeforeEach
	void setUp() {
		HashMap<Consumable, Integer> tempInventory = new HashMap<Consumable, Integer>();
		tempInventory.put(new Consumable("Space Plague Cure"), 1);
		tempInventory.put(new Consumable("Band-Aid"), 2);
		inventory = new Inventory(tempInventory);
	}

	@Test
	void testGetItems() {
		HashMap<Consumable, Integer> tempInventory = new HashMap<Consumable, Integer>();
		tempInventory.put(new Consumable("Space Plague Cure"), 1);
		tempInventory.put(new Consumable("Band-Aid"), 2);
		assertEquals(tempInventory, inventory);
	}

	@Test
	void testSize() {
		assertEquals(2, inventory.size());
		inventory.getItems().remove(new Consumable("Space Plague Cure"));
		assertEquals(1, inventory.size());
	}

	@Test
	void testGet() {
		assertEquals(1, inventory.get(new Consumable("Space Plague Cure")));
		assertEquals(2, inventory.get(new Consumable("Band-aid")));
	}

	@Test
	void testAddItem() {
		inventory.addItem(new Consumable("Space Plague Cure"));
		assertEquals(2, inventory.get(new Consumable("Space Plague Cure")));
		inventory.addItem(new Consumable("Space Plague Cure"));
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
