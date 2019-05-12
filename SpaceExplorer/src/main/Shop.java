package main;

import java.util.HashMap;
import main.Consumable;

public class Shop {
	
	private HashMap<Consumable, Integer> shopItems;
	
	/**
	 * Class constructor, with no additional entries
	 */
	public Shop() {
		Consumable spacePlagueCure = new Consumable("Space Plague Cure");
		Consumable bandAid = new Consumable("Band-Aid");
		Consumable firstAidKit = new Consumable("First Aid Kit");
		Consumable spaceRation = new Consumable("Space Ration");
		Consumable water = new Consumable("Water");
		Consumable banana = new Consumable("Banana");
		Consumable alienMeat = new Consumable("Alien Meat");
		Consumable coffee = new Consumable("Coffee");
		Consumable egg = new Consumable("Egg");
		shopItems = new HashMap<Consumable, Integer>();
		shopItems.put(spacePlagueCure, 1);
		shopItems.put(bandAid, 1);
		shopItems.put(firstAidKit, 1);
		shopItems.put(spaceRation, 1);
		shopItems.put(water, 1);
		shopItems.put(banana, 1);
		shopItems.put(alienMeat, 1);
		shopItems.put(coffee, 1);
		shopItems.put(egg, 1);
	}
	
	/**
	 * Class constructor 
	 * @param items temporary name for the shop items being passed into the object
	 */
	public Shop(HashMap<Consumable, Integer> items) {
		shopItems = items;
	}
	
	/**
	 * Retrieves the details of the things in the shop
	 * @return the items in the shop and their quantities in a HashMap
	 */
	public HashMap<Consumable, Integer> getShopItems() {
		return shopItems;
	}
	
	/**
	 * Decreases the quantity of an item in the shop by 1
	 * Removes it from the shop if quantity drops to 0
	 * @param item the item for sale to be purchased
	 */
	public void sellShopItem(Consumable item) {
		int value = shopItems.get(item);
		if (value >= 2) {
			shopItems.put(item, value - 1);
		}
		else {
			shopItems.remove(item);
		}
	}
}
