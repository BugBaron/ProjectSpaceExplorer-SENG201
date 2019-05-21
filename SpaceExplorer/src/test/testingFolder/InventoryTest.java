package test.testingFolder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Consumable;
import main.Inventory;

public class InventoryTest {
	private final Consumable SPACE_PLAGUE_CURE = new Consumable("Space Plague Cure");
	private final Consumable BAND_AID = new Consumable("Band-Aid");
	private final Consumable COFFEE = new Consumable("Coffee");
	
	private Inventory inventory;
	@BeforeEach
	void setUp() {
		HashMap<Consumable, Integer> tempInventory = new HashMap<Consumable, Integer>();
		tempInventory.put(SPACE_PLAGUE_CURE, 1);
		tempInventory.put(BAND_AID, 2);
		inventory = new Inventory(tempInventory);
	}
	
	@Test
	void testGetItems() {
		HashMap<Consumable, Integer> tempInventory = new HashMap<Consumable, Integer>();
		tempInventory.put(SPACE_PLAGUE_CURE, 1);
		tempInventory.put(BAND_AID, 2);
		assertEquals(tempInventory, inventory.getItems());
	}

	@Test
	void testSize() {
		assertEquals(2, inventory.size());
		inventory.getItems().remove(SPACE_PLAGUE_CURE);
		assertEquals(1, inventory.size());
	}

	@Test
	void testGet() {
		assertEquals(1, inventory.get(SPACE_PLAGUE_CURE));
		assertEquals(2, inventory.get(BAND_AID));
	}

	@Test
	void testAddItem() {
		inventory.addItem(SPACE_PLAGUE_CURE);
		assertEquals(2, inventory.get(SPACE_PLAGUE_CURE));
		inventory.addItem(COFFEE);
		assertEquals(1, inventory.get(COFFEE));
		Consumable newCoffee = new Consumable("Coffee");
		inventory.addItem(newCoffee);
		for (Consumable i : inventory.getKeys()) {
			System.out.println(i.getName());
		}
		//assertEquals(2, inventory.get(COFFEE));
		assertEquals(2, inventory.get(newCoffee));
	}

	@Test
	void testRemoveItem() {
		inventory.removeItem(BAND_AID);
		assertEquals(1, inventory.get(BAND_AID));
		inventory.removeItem(BAND_AID);
		assertEquals(0, inventory.get(BAND_AID));
	}

	@Test
	void testGetKeys() {
		ArrayList<Consumable> testKeys = new ArrayList<Consumable>(Arrays.asList(BAND_AID, SPACE_PLAGUE_CURE));
		assertEquals(testKeys, inventory.getKeys());
		inventory.addItem(COFFEE);
		inventory.removeItem(SPACE_PLAGUE_CURE);
		ArrayList<Consumable> newTestKeys = new ArrayList<Consumable>(Arrays.asList(COFFEE, BAND_AID));
		assertEquals(newTestKeys, inventory.getKeys());
	}

}
