package test.testingFolder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
import main.Consumable;
import main.Shop;
import java.util.HashMap;

class ShopTest {
	
	Consumable egg = new Consumable("Egg");
	Consumable spaceRation = new Consumable("Space Ration");
	Consumable bandAid = new Consumable("Band-Aid");
	HashMap<Consumable, Integer> shopItems = new HashMap<Consumable, Integer>();

	@Test
	void testGetShopItems() {
		shopItems.put(egg, 2);
		shopItems.put(spaceRation, 1);
		shopItems.put(bandAid, 10);
		Shop shop = new Shop(shopItems);
		HashMap<Consumable, Integer> result = shop.getShopItems();
		assertTrue(result.equals(shopItems));
	}

	@Test
	void testSellShopItem() {
		shopItems.put(egg, 2);
		shopItems.put(spaceRation, 1);
		shopItems.put(bandAid, 10);
		Shop shop = new Shop(shopItems); //Contains 2 eggs, 1 space ration and 10 band-aids
		shop.sellShopItem(egg); // Shop now contains 1 egg
		shop.sellShopItem(spaceRation); // Shop now contains no space rations
		shop.sellShopItem(bandAid); // Shop now contains 9 band-aids
		HashMap<Consumable, Integer> results = shop.getShopItems();
		assertTrue(results.get(egg).equals(1));
		assertTrue(results.get(bandAid).equals(9));
		assertFalse(results.containsKey(spaceRation));
	}
	
}
