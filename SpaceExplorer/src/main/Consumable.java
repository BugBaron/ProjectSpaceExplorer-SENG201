package main;

import main.CrewMemberTypes.CrewMember;

public class Consumable {
	
	private int price;
	private int nutritionRestore = 0;
	private int energyRestore = 0;
	private int healthRestore = 0;
	private boolean cureSpacePlague = false;
	private String description;
	private String name;
	private String classification;
	
	/** 
	 * Class constructor
	 * @param tempName the name of consumable item being created
	 */
	public Consumable(String tempName) {
		name = tempName;
		switch(name) {
		case "Space Plague Cure": price = 25;
			cureSpacePlague = true;
			description = "Cures the Space Plague"; 
			classification = "Medical";
			break;
		case "Band-Aid": price = 10;
			healthRestore = 2;
			description = "Restores 2 Health"; 
			classification = "Medical";
			break;
		case "First Aid Kit": price = 23;
			healthRestore = 5;
			description = "Restores 5 Health";
			classification = "Medical";
			break;
		case "Space Ration": price = 18;
			nutritionRestore = 4;
			description = "Restores 4 Nutrition";
			classification = "Food";
			break;
		case "Water": price = 10;
			nutritionRestore = 2;
			description = "Restores 2 Nutrition";
			classification = "Food";
			break;
		case "Banana": price = 10;
			nutritionRestore = 2;
			description = "Restores 2 Nutrition";
			classification = "Food";
			break;
		case "Alien Meat": price = 22;
			nutritionRestore = 5;
			description = "Restores 5 Nutrition";
			classification = "Food";
			break;
		case "Coffee": price = 13;
			nutritionRestore = 2;
			energyRestore = 1;
			description = "Restores 2 Nutrition and 1 Energy";
			classification = "Food";
			break;
		case "Egg": price = 14;
			nutritionRestore = 3;
			description = "Restores 3 Nutrition";
			classification = "Food";
			break;
		default: System.out.println(name + " is not a valid consumable");
		}		
	}
	
	/**
	 * Retrieves the price information for the item
	 * @return price an integer value for the price of the item
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Applies the item to a crew member
	 * @param person a specific crew member who gains the uses of the item
	 */
	public void useItem(CrewMember person) {
		person.addHealth(healthRestore);
		person.addEnergy(energyRestore);
		person.addNutrition(nutritionRestore);
		if (this.cureSpacePlague == true) {
			person.setHasSpacePlague(false);
		}
	}
	
	/**
	 * Retrieves the name of the item
	 * @return name the name attached to the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retrieves the description of the item
	 * @return description the details of what the item does in English rather than code
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Retrieves the classification of the item: either Food or Medical
	 * @return classification which category the item fits in
	 */
	public String getClassification() {
		return classification;
	}
	
	/**
	 * Tests this consumable for equality with another one
	 * @param other the consumable to test this consumable with
	 * @return whether the two consumables are equal
	 */
	public boolean equals(Consumable other) {
		return this.name == other.name;
	}
}

