package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains a mapping of Consumables to integers, to represent the quantity of
 * relevant items
 * @author Daniel Harris and Rebekah McKinnon
 *
 */
public class Inventory {
	
	/** The map to store the quantities of each item in */
	private HashMap<Consumable, Integer> items;
	
	
	/**
	 * Class constructor, with no additional entries
	 * @param isShop whether or not to make this a shop inventory
	 */
	public Inventory() {
		items = new HashMap<Consumable, Integer>();
	}
	
	
	/**
	 * Class constructor 
	 * @param newItems the items that this inventory should start with 
	 */
	public Inventory(HashMap<Consumable, Integer> newItems) {
		items = newItems;
	}
	
	
	/**
	 * Retrieves the details of the things in the inventory
	 * @return the items in the shop and their quantities
	 */
	public HashMap<Consumable, Integer> getItems() {
		return items;
	}
	
	
	/**
	 * Gets the number of items in the inventory
	 * @return the number of items in the inventory
	 */
	public int size() {
		return items.size();
	}
	
	
	/**
	 * Gets the quantity of an item in the inventory
	 * @param item an item in the inventory
	 * @return the quantity of the item in the inventory
	 */
	public int get(Consumable item) {
		if (items.containsKey(item)) {
			return items.get(item);
		} else {
			return 0;
		}
	}
	
	
	/**
	 * Increases the quantity of an item in this inventory by 1
	 * Adds it to the inventory with a quantity of 1 if it is not present
	 * @param item the item to add to the inventory
	 */
	public void addItem(Consumable item) {
		if (items.containsKey(item)) {
			items.put(item, items.get(item) + 1);
		} else {
			items.put(item, 1);
		}
	}
	
	
	/**
	 * Decreases the quantity of an item in the inventory by 1
	 * Removes it from the inventory if quantity drops to 0
	 * @param item the item to be removed
	 */
	public void removeItem(Consumable item) {
		int value = items.get(item);
		if (value > 1) {
			items.put(item, value - 1);
		}
		else {
			items.remove(item);
		}
	}
	
	
	/** 
	 * Gets an ArrayList of all the items in the inventory, without their quantities,
	 * sorted according to their type and name
	 * @return a list of the items in the inventory
	 */
	public ArrayList<Consumable> getKeys() {
		// Converts the inventory keys to an array
		ArrayList<Consumable> keys = new ArrayList<Consumable>();
		items.keySet().stream()
				.sorted((a, b) -> (a.getClassification() + a.getName()).compareTo(
						b.getClassification() + b.getName()))
				.forEach(x -> keys.add(x));
		return keys;
	}
	
	
}