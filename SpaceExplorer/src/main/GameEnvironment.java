package main;

import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import main.CrewMemberTypes.Alien;
import main.CrewMemberTypes.CrewMember;
import main.CrewMemberTypes.Cyborg;
import main.CrewMemberTypes.Human;
import main.CrewMemberTypes.Lizard;
import main.CrewMemberTypes.Robot;
import main.CrewMemberTypes.Unicorn;

public class GameEnvironment {
	private Ship ship;
	private Shop shop;
	private InOutHandler inOut;
	private int partsToFind;
	private int partsFound;
	private boolean partsHere;
	
	private int dayNumber;
	private int maxDays;
	private int currentPlanet;
	private ArrayList<String> PLANET_ARRAY = new ArrayList<>(Arrays.asList("Europa", "Titan", 
			"Io", "Callisto", "Oberon", "Umbriel", "Rhea", "Phoebe", "Ananke", "Dactyl"));
	private final int[] ATTACK_PROBABILITY = {20, 20, 60};
	private final int[] ASTEROID_PROBABILITY = {30, 70};
	private final Consumable[] FOOD_ITEMS = {new Consumable("Space Ration"), 
			new Consumable("Water"), new Consumable("Banana"), 
			new Consumable("Alien Meat"), new Consumable("Coffee"), 
			new Consumable("Egg")};
	private final Consumable[] MEDICAL_ITEMS = {new Consumable("Space Plague Cure"),
			new Consumable("Band-Aid"), new Consumable("First Aid Kit")};
	private final Integer[] FINDABLE_MONEY = {10, 20, 30};
	
	
	/**
	 * Sets up default variables for a new game, and calls createGame to get the user-chosen variables
	 */
	public GameEnvironment() {
		// shop = new Shop(items)
		inOut = new InOutHandler();
		partsHere = true;
		dayNumber = 1;
		currentPlanet = 0;
	}
	
	
	public void createGame() {
		inOut.print("How many in-game days would you like the game to last? (3-10)");
		maxDays = inOut.collectInt(3, 10);
		partsToFind = 2 * maxDays / 3;
		
		inOut.print("How many crew members would you like? (2-4)");
		int numMembers = inOut.collectInt(2, 4);
		
		ArrayList<CrewMember> selectedMembers = new ArrayList<CrewMember>();
		ArrayList<CrewMember> availableList = new ArrayList<CrewMember>(Arrays.asList(new Human(ship), new Robot(ship), new Cyborg(ship), 
					new Alien(ship), new Lizard(ship), new Unicorn(ship)));
		for (int i = 0; i < numMembers; i++) {
			inOut.print("Choose crew member " + (i + 1) + ":");
			for (CrewMember person : availableList) {
				ArrayList<String> typeList = (ArrayList<String>) person.getTypeInfo().values();
				String typeString = Arrays.toString(typeList.toArray());
				inOut.print(availableList.indexOf(person) + ") " + typeString.substring(1, typeString.length()));
			}
			CrewMember selectedPerson = availableList.get(inOut.collectInt(1, 6) - 1);
			inOut.print("What would you like to name this " + selectedPerson.getTypeInfo().get("Type") + "?");
			String name = inOut.collectString();
			try {
				selectedMembers.add(selectedPerson.getClass().getConstructor(ship.getClass(), String.class).newInstance(ship, name));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		inOut.print("What would you like the name of your ship to be?");
		ship = new Ship(inOut.collectString());
		
		//startGameLoop()		
	}
	
	
	/**
	 * Conducts all processes related to ending the day
	 */
	public void newDay() {
		// End the day for each user. Also add to the score if the crew member has max status
		for (CrewMember person : ship.getCrewMembers()) {
			person.endDay();
			HashMap <String, Integer> status = person.getStatus();
			HashMap <String, Integer> maxStatus = person.getMaxStats();
			if (status.get("Health") == maxStatus.get("Health")) ship.addScore(10);
			if (status.get("Energy") == maxStatus.get("Energy")) ship.addScore(10);
			if (status.get("Nutrition") == maxStatus.get("Nutrition")) ship.addScore(10);			
		}
		ship.addMoney(20);
		
		int sumAttackProbability = Arrays.stream(ATTACK_PROBABILITY).sum();
		int randInt = (new Random()).nextInt(sumAttackProbability);
		HashMap <Consumable, Integer> inventory = ship.getInventory();
		if (randInt < ATTACK_PROBABILITY[0] && inventory.size() > 0) {
			Consumable key = (Consumable) pickRandom(inventory.keySet().toArray());
			inOut.print("Aliens have attacked your ship! They stole your " + 
					key.getName() + "!");
			inOut.print("You now have " + (inventory.get(key) - 1) + " " + 
					key.getName() + "(s) remaining");
			ship.removeItem(key);
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
	}
	
	
	/**
	 * Searches a planet for spaceship parts, money, food or medical items.
	 * @param crewMember the crew member to search with
	 * @return a FindableItem representing what was found
	 */
	public void searchPlanet(CrewMember crewMember) {
		Random r = new Random();
		int[] searchProbability = crewMember.getSearchingProbabilities();
		int totalSum = Arrays.stream(searchProbability).sum();
		int randInt = r.nextInt(totalSum);
		
		crewMember.completeAction();
		
		// Finds the item to return based on what the searching probabilities are
		if (randInt < searchProbability[0]) {
			inOut.print(crewMember.getName() + " found a ship part!");
			inOut.print("You now have " + partsFound + " out of " + partsToFind + 
					" parts");
		} else if (randInt < searchProbability[2]) {
			Consumable foundItem;
			if (randInt < searchProbability[1]) {
				foundItem = (Consumable) pickRandom(FOOD_ITEMS);
			} else {
				foundItem = (Consumable) pickRandom(MEDICAL_ITEMS);
			}
			ship.addItem(foundItem);
			inOut.print(crewMember.getName() + " found a " + foundItem.getName() + "!");
		} else if (randInt < searchProbability[3]) {
			int moneyFound = (Integer) pickRandom(FINDABLE_MONEY);
			inOut.print(crewMember.getName() + " found $" + moneyFound + "!");
			ship.addMoney(moneyFound);
		} else {
			inOut.print(crewMember.getName() + " didn't find anything!");
		}
	}
	
	
	/**
	 * Finds an item to use in the inventory and uses it
	 * @param crewMember the crew member to use the item on
	 * @return a boolean representing whether or not an item was used
	 */
	public boolean useItem(CrewMember crewMember) {
		HashMap<Consumable, Integer> inventory = ship.getInventory();
		// Converts the inventory keys to an array
		ArrayList<Consumable> keys = new ArrayList<Consumable>();
		inventory.keySet().stream()
				.sorted((a, b) -> (a.getClassification() + a.getName()).compareTo(
						b.getClassification() + b.getName()))
				.forEach(x -> keys.add(x));
		
		// Returns to previous menu if there are no items in the inventory
		int size = keys.size();
		if (size == 0) {
			inOut.print("No items in the ship inventory!");
			inOut.print("Returning to Crew Member Actions...");
			return false;
		}

		// Displays all the items in the inventory as options
		int i;
		for (i = 0; i < size; i++) {
			Consumable item = keys.get(i);
			String classification = item.getClassification();
			String name = item.getName();
			String description = item.getDescription();
			inOut.print((i + 1) + ") " + classification + " item: " + name + ", " 
			+ description + ", Quantity: " + inventory.get(item));
		}

		inOut.print((i + 1) + ") Back to Crew Member Actions");
		System.out.flush();

		// Collects the user input
		int choice = inOut.collectInt(1, i + 1);
		
		// Uses the selected item and reduces the quantity of that item in the inventory
		if (choice < (i + 1)) {
			Consumable item = keys.get(choice - 1);
			
			item.useItem(crewMember);
			
			ship.removeItem(item);
			
			crewMember.completeAction();
			inOut.print(crewMember.getName() + " used a " + item.getName());
			return true;
		} else {
			return false;
		}
	}
	
	
	/** 
	 * Makes the crew member sleep and gain 4 energy
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
	}
	
	
	/**
	 * Repairs the ship by "REPAIR_AMOUNT"
	 */
	public void repairShip(CrewMember crewMember) {
		crewMember.completeAction();
		ship.addShipShields(crewMember.getRepairAmount());
	}
	
	
	/**
	 * Finds another crew member and pilots the ship to another planet with them
	 */
	public boolean pilotShip(CrewMember crewMember) {
		ArrayList<CrewMember> crewMembers = ship.getCrewMembers();
		
		// This part displays options for all other crew members that have actions remaining
		ArrayList<CrewMember> membersWithActions = new ArrayList<CrewMember>();
		for (int i = 0; i < crewMembers.size(); i++) {
			CrewMember person = crewMembers.get(i);
			
			// Ensure that only other crew members that have actions are shown
			if (person.getActions() > 0 && crewMember != person) {
				membersWithActions.add(person);
				int index = membersWithActions.size();
				inOut.print(index + ") " + person.getName() + ", " + person.getTypeInfo().get("Type"));
			}
		}
		
		int index = membersWithActions.size();
		// Returns to the previous menu if there are no other crew members to pilot the ship with
		if (index == 0) {
			inOut.print("No other crew members have any remaining actions!");
			inOut.print("Returning to Crew Member Actions...");
			return false;
		}
		
		inOut.print((index + 1) + ") Back to Crew Member Actions");
		System.out.flush();

		// Collects the user input
		int choice = inOut.collectInt(1, index + 1);
		
		// Completes the action for this crew member and the other chosen one
		if (choice < (index + 1)) {
			CrewMember person = membersWithActions.get(choice - 1);
			crewMember.completeAction();
			person.completeAction();
			
			currentPlanet++;
			if (currentPlanet >= PLANET_ARRAY.size()) currentPlanet = 0;
			inOut.print(crewMember.getName() + " and " + person.getName() + 
					" flew the ship to " + PLANET_ARRAY.get(currentPlanet));
			
			int sumAsteroidProbability = Arrays.stream(ASTEROID_PROBABILITY).sum();
			int randInt = (new Random()).nextInt(sumAsteroidProbability);
			if (randInt < ASTEROID_PROBABILITY[0]) {
				inOut.print("Oh no! You have piloted into an asteroid field! Your ship lost 3 shields!");
				ship.addShipShields(-3);
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Gets a random crew member from an ArrayList of crew members
	 * @param list an ArrayList to chose a crew member from
	 * @return a random crew member from the list
	 */
	public Object pickRandom(ArrayList<CrewMember> list) {
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(list.size());
		return list.get(randInt);
	}
	
	
	/**
	 * Gets a random item from an Object array
	 * @param list an Object array to chose an item from
	 * @return a random item from the array
	 */
	public Object pickRandom(Object[] objects) {
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(objects.length);
		return objects[randInt];
	}
	
	
}
