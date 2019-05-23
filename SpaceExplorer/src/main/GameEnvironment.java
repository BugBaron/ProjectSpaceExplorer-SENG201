package main;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import main.CrewMemberTypes.CrewMember;

/**
 * Conducts all game logic to play the Space Explorer game.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class GameEnvironment {
	
	/** Prints output and receives input. */
	private InOutHandler inOut;
	
	/** The ship used in the game. */
	private Ship ship;
	/** The inventory of items available to the crew in the game. */
	private Inventory inventory;
	/** The shop on a planet in the game. */
	private Inventory shop;
	
	/** The parts required to win the game. */
	private int partsToFind;
	/** The number of parts found so far. */
	private int partsFound;
	/** Whether or not there are parts on this planet. */
	private boolean partsHere;
	
	/** The number of days that the game has been played for. */
	private int dayNumber;
	/** The maximum number of days. */
	private int maxDays;
	
	/** The index of the Planet Array to get the planet the ship is on. */
	private int currentPlanet;
	/** A list of planets that can be visited. */
	private final ArrayList<String> PLANET_ARRAY = new ArrayList<>(Arrays.asList("Europa", "Titan", 
			"Io", "Callisto", "Oberon", "Umbriel", "Rhea", "Phoebe", "Ananke", "Dactyl"));
	
	/** 
	 * A list of percentage probabilities. At the end of each day, 20% chance to have an item
	 * stolen, 20% chance to have a crew member infected, and 60% chance for no special event.
	 */
	private final int[] ATTACK_PROBABILITY = {20, 20, 60};
	/** 
	 * A list of percentage probabilities. When the ship is piloted, 30% chance to run into
	 * an Asteroid field, and 70% chance for no special event.
	 */
	private final int[] ASTEROID_PROBABILITY = {30, 70};
	/** A list of all the available food items. */
	private final Consumable[] FOOD_ITEMS = {new Consumable("Space Ration"),
			new Consumable("Water"), new Consumable("Banana"),
			new Consumable("Alien Meat"), new Consumable("Coffee"),
			new Consumable("Egg")};
	/** A list of all the available medical items. */
	private final Consumable[] MEDICAL_ITEMS = {new Consumable("Space Plague Cure"),
			new Consumable("Band-Aid"), new Consumable("First Aid Kit")};
	/** A list of all the amounts of money that can be found each time a planet is searched. */
	private final Integer[] FINDABLE_MONEY = {10, 20, 30};
	
	
	/**
	 * Creates a new shop.
	 * @return a new shop
	 */
	public Inventory createShop() {
		HashMap<Consumable, Integer> shopList = new HashMap<Consumable, Integer>();
		int[] possibleQuantity = {0, 1, 2};
		Random randomVar = new Random();
		int randInt;
		for (Consumable i : FOOD_ITEMS) {
			randInt = possibleQuantity[randomVar.nextInt(possibleQuantity.length)];
			if (randInt > 0) shopList.put(i, randInt);
		}
		for (Consumable i : MEDICAL_ITEMS) {
			randInt = possibleQuantity[randomVar.nextInt(possibleQuantity.length)];
			if (randInt > 0) shopList.put(i, randInt);
		}
		Inventory newShop = new Inventory(shopList);
		return newShop;
	}
	
	
	/**
	 * Class constructor for the GameEnvironment.
	 */
	public GameEnvironment() {
		inOut = new InOutHandler();
	}
	
	
	/**
	 * Gets the InOutHandler of this GameEnvironment.
	 * @return the InOutHandler
	 */
	public InOutHandler getInOut() {
		return inOut;
	}
	
	
	/**
	 * Creates a new game with the specified number of days and the 
	 * specified ship with crew members already added to it.
	 * @param totalDays the maximum number of days to repair the ship
	 * @param tempShip the ship to create the game with
	 */
	public void createGame(int totalDays, Ship tempShip) {
		shop = createShop();
		inventory = new Inventory();
		partsHere = true;
		dayNumber = 1;
		currentPlanet = 0;
		
		maxDays = totalDays;
		partsToFind = 2 * totalDays / 3;
		ship = tempShip;
	}
	
	
	/**
	 * Gets a list containing all the crew members of the ship.
	 * @return the crew members of the ship
	 */
	public ArrayList<CrewMember> getCrewMembers() {
		return ship.getCrewMembers();
	}
	
	
	/**
	 * Gets a list containing all crew members with actions in the ship.
	 * @return the crew members with actions in the ship
	 */
	public ArrayList<CrewMember> getAvailableMembers() {
		ArrayList<CrewMember> availableMembers = new ArrayList<CrewMember>();
		for (CrewMember crewMember : ship.getCrewMembers()) {
			if (crewMember.getActions() > 0) availableMembers.add(crewMember);
		}
		return availableMembers;
	}
	
	
	/**
	 * Gets a string representing the ship status.
	 * @return a string representing the ship status
	 */
	public String getShipString() {
		String returnString = ship.toString() + "\nSpaceship pieces found: "
				+ partsFound + "/" + partsToFind;
		return returnString;
	}
	
	
	/**
	 * Gets a string to show the number of days completed and the
	 * days remaining.
	 * @return a string of the day number
	 */
	public String getDayString() {
		return "Day number: " + dayNumber + "/" + maxDays;
	}
	
	
	/**
	 * Purchases an item from the shop.
	 * @param item the item to be purchased
	 */
	public void purchaseItem(Consumable item) {
		shop.removeItem(item); 
		inventory.addItem(item);
		inOut.print("You just purchased a " + item.getName() + "!");
		ship.addMoney(-item.getPrice());
	}
	
	
	/**
	 * Gets the inventory of the ship.
	 * @return the inventory of the ship
	 */
	public Inventory getInventory() {
		return inventory;
	}


	/**
	 * Gets the shop of the planet.
	 * @return the shop of the planet
	 */
	public Inventory getShop() {
		return shop;
	}
	
	
	/**
	 * Gets the total money collected on the ship.
	 * @return the money on the ship
	 */
	public int getMoney() {
		return ship.getMoney();
	}
	
	
	/**
	 * Conducts all processes related to ending the day.
	 * @return whether or not the game ended as a result of this function
	 */
	public boolean newDay() {
		// End the day for each user. Also add to the score if the crew member has max status
		int i;
		int size = ship.getCrewMembers().size();
		int removedMembers = 0;
		for (i = 0; i < size; i++) {
			CrewMember person = ship.getCrewMembers().get(i - removedMembers);
			HashMap<String, Integer> status = person.getStatus();
			HashMap<String, Integer> maxStatus = person.getMaxStats();
			if (status.get("Health") == maxStatus.get("Health")) ship.addScore(10);
			if (status.get("Energy") == maxStatus.get("Energy")) ship.addScore(10);
			if (status.get("Nutrition") == maxStatus.get("Nutrition")) ship.addScore(10);
			person.endDay();
			if (!ship.getCrewMembers().contains(person)) {
				inOut.print("Oh no! " + person.getName() + " has died!");
				removedMembers = removedMembers + 1;
			}
		}
		dayNumber = dayNumber + 1;
		ship.addMoney(20);
		
		// Ends the game if there are no more crew members left alive, or if the last day ended
		if (ship.getCrewMembers().size() == 0 || dayNumber > maxDays) {
			Object output = inOut.getOutput();
			while (output != null) {
				output = inOut.getOutput();
			}
			return true; // Indicates that the game has ended
		} else {
			int sumAttackProbability = Arrays.stream(ATTACK_PROBABILITY).sum();
			int randInt = (new Random()).nextInt(sumAttackProbability);
			if (randInt < ATTACK_PROBABILITY[0] && inventory.size() > 0) {
				Consumable key = (Consumable) pickRandom(inventory.getKeys());
				inOut.print("Aliens have attacked your ship! They stole your "
						+ key.getName() + "!");
				inventory.removeItem(key);
				inOut.print("You now have " + (inventory.get(key)) + " "
						+ key.getName() + "(s) remaining");
			} else if (randInt < ATTACK_PROBABILITY[1]) {
				ArrayList<CrewMember> healthyCrewMembers = new ArrayList<CrewMember>();
				healthyCrewMembers.addAll(ship.getCrewMembers());
				healthyCrewMembers.removeIf(x -> x.getHasSpacePlague());
				if (healthyCrewMembers.size() > 0) {
					CrewMember person = (CrewMember) pickRandom(healthyCrewMembers);
					inOut.print(person.getName() + " has contracted the space plague!");
					person.setHasSpacePlague(true);
				}
			}
			inOut.print(false); // Indicates that the random events have no more messages
			inOut.print("Daily Score: " + ship.getDailyScore());
			return false; // Indicates that the game has not ended
		}
	}
	
	
	/**
	 * Searches a planet for spaceship parts, money, food or medical items.
	 * @param crewMember the crew member to search with
	 * @return whether or not the game has ended as a result of this action
	 */
	public boolean searchPlanet(CrewMember crewMember) {
		Random r = new Random();
		int[] searchProbability = crewMember.getSearchingProbabilities();
		int totalSum = Arrays.stream(searchProbability).sum();
		int randInt = r.nextInt(totalSum);
		
		crewMember.completeAction();
		
		// Finds the item to return based on what the searching probabilities are
		if (randInt < searchProbability[0]) {
			if (partsHere) {
				inOut.print(crewMember.getName() + " found a ship part!");
				partsFound = partsFound + 1;
				ship.addScore(100);
				if (partsFound == partsToFind) {
					return true;
				} else {
					inOut.print("You now have " + partsFound + " out of " + partsToFind
							+ " parts");
					partsHere = false;
				}
			} else {
				inOut.print(crewMember.getName() + " didn't find anything!");
			}
		} else if (randInt < searchProbability[0] + searchProbability[1] + searchProbability[2]) {
			Consumable foundItem;
			if (randInt < searchProbability[0] + searchProbability[1]) {
				foundItem = (Consumable) pickRandom(FOOD_ITEMS);
			} else {
				foundItem = (Consumable) pickRandom(MEDICAL_ITEMS);
			}
			inventory.addItem(foundItem);
			inOut.print(crewMember.getName() + " found a " + foundItem.getName() + "!");
		} else if (randInt < totalSum - searchProbability[4]) {
			int moneyFound = (Integer) pickRandom((Object[]) FINDABLE_MONEY);
			inOut.print(crewMember.getName() + " found $" + moneyFound + "!");
			ship.addMoney(moneyFound);
		} else {
			inOut.print(crewMember.getName() + " didn't find anything!");
		}
		return false;
	}
	
	
	/**
	 * Uses the selected item and reduces the quantity of that item in the inventory.
	 * @param crewMember the crew member to use the item on
	 * @param item the item to use
	 */
	public void useItem(CrewMember crewMember, Consumable item) {
		crewMember.completeAction();
		item.useItem(crewMember);
		
		inventory.removeItem(item);
		
		inOut.print(crewMember.getName() + " used a " + item.getName());
	}
	
	
	/**
	 * Makes the crew member sleep and gain 4 energy.
	 * @param crewMember the crew member to make sleep
	 */
	public void sleep(CrewMember crewMember) {
		if (crewMember.getStatus().get("Energy") == 0) {
			crewMember.addEnergy(5);
			crewMember.completeAction();
		} else {
			crewMember.completeAction();
			crewMember.addEnergy(5);
		}
		inOut.print(crewMember.getName() + " slept and restored to "
				+ crewMember.getStatus().get("Energy") + " energy");
	}
	
	
	/**
	 * Repairs the ship by the repair amount that the crew member has.
	 * @param crewMember the crew member to complete the action
	 */
	public void repairShip(CrewMember crewMember) {
		crewMember.completeAction();
		ship.addShipShields(crewMember.getRepairAmount());
		inOut.print(crewMember.getName() + " restored the shield levels of " + ship.getName()
			+ " to " + ship.getShipShields());
	}
	
	
	/**
	 * Finds another crew member and pilots the ship to another planet with them.
	 * @param crewMember the crew member to complete the action
	 * @param person the other crew member to complete the action
	 */
	public void pilotShip(CrewMember crewMember, CrewMember person) {
		crewMember.completeAction();
		person.completeAction();
		
		currentPlanet++;
		if (currentPlanet >= PLANET_ARRAY.size()) currentPlanet = 0;
		inOut.print(crewMember.getName() + " and " + person.getName()
				+ " flew the ship to " + PLANET_ARRAY.get(currentPlanet));

		partsHere = true;
		shop = createShop();
		int sumAsteroidProbability = Arrays.stream(ASTEROID_PROBABILITY).sum();
		int randInt = (new Random()).nextInt(sumAsteroidProbability);
		if (randInt < ASTEROID_PROBABILITY[0]) {
			inOut.print("Oh no! You have piloted into an asteroid field! Your ship lost 3 shields!");
			ship.addShipShields(-3);
		}
	}
	
	
	/**
	 * Prints an end game screen for the player after they have found all the parts,
	 * lost all their crew members or run out of time.
	 * @param isVictory whether or not the player won the game
	 */
	public void endGame(boolean isVictory) {
		if (isVictory) {
			inOut.print("Congratulations!");
			inOut.print("You have piloted the ship " + ship.getName() + " to victory"
					+ " and found all the missing parts of your ship!");
			inOut.print("Well done!");
		} else if (ship.getCrewMembers().size() == 0) {
			inOut.print("Sorry, you lost!");
			inOut.print("Oh no! All of the members of your crew have died!");
			inOut.print("The " + ship.getName() + " repair mission ends in tragedy.");
		} else {
			inOut.print("Sorry, you lost!");
			inOut.print("It has been " + maxDays + " days.");
			inOut.print(ship.getName() + " is now damaged beyond repair.");
		}
		inOut.print("\n");
		inOut.print("Final Score: " + ship.getTotalScore());
	}
	
	
	/**
	 * Gets a random crew member from an ArrayList of crew members.
	 * @param list an ArrayList to chose a crew member from
	 * @return a random crew member from the list
	 */
	public Object pickRandom(ArrayList<?> list) {
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(list.size());
		return list.get(randInt);
	}
	
	
	/**
	 * Gets a random item from an Object array.
	 * @param list an Object array to chose an item from
	 * @return a random item from the array
	 */
	public Object pickRandom(Object[] list) {
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(list.length);
		return list[randInt];
	}
	
	
}
