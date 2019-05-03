package main;

import java.util.HashMap;

public class Shop {
	
	private HashMap<Consumable, Integer> shopItems;
	
	
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
