package main;

import java.util.Arrays;
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
	private Inventory inventory;
	private Inventory shop;
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
	
	
	public static void main(String[] args) {
		GameEnvironment gameEnvironment = new GameEnvironment();
		gameEnvironment.createGame();
	}
	
	
	/**
	 * Class constructor for the GameEnvironment
	 */
	public GameEnvironment() {
		inOut = new InOutHandler();
	}
	
	
	public void createGame() {
		shop = new Inventory(true);
		inventory = new Inventory(false);
		partsHere = true;
		dayNumber = 1;
		currentPlanet = 0;
		
		inOut.print("How many in-game days would you like the game to last? (3-10)");
		maxDays = inOut.collectInt(3, 10);
		partsToFind = 2 * maxDays / 3;
		
		inOut.print("How many crew members would you like? (2-4)");
		int numMembers = inOut.collectInt(2, 4);
		
		ArrayList<CrewMember> selectedMembers = new ArrayList<CrewMember>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<CrewMember> availableList = new ArrayList<CrewMember>(Arrays.asList(new Human(ship), new Robot(ship), new Cyborg(ship), 
					new Alien(ship), new Lizard(ship), new Unicorn(ship)));
		for (int i = 0; i < numMembers; i++) {
			inOut.print("Choose crew member " + (i + 1) + ":");
			for (CrewMember person : availableList) {
				HashMap<String, String> typeInfo = person.getTypeInfo();
				inOut.print((availableList.indexOf(person) + 1) + ") " + typeInfo.get("Type") + ", " 
				+ typeInfo.get("Strength") + ", " + typeInfo.get("Weakness"));
			}
			CrewMember selectedPerson = availableList.get(inOut.collectInt(1, 6) - 1);
			inOut.print("What would you like to name this " + selectedPerson.getTypeInfo().get("Type") + "?");
			String name = inOut.collectString();
			selectedMembers.add(selectedPerson);
			names.add(name);
		}
		
		inOut.print("What would you like the name of your ship to be?");
		String shipName = inOut.collectString();
		if (shipName.length() == 0) {
			ship = new Ship();
		} else {
			ship = new Ship(shipName);
		}
		
		for (int i = 0; i < selectedMembers.size(); i++) {
			String type = selectedMembers.get(i).getTypeInfo().get("Type");
			String name = names.get(i);
			ArrayList<CrewMember> shipMembers = ship.getCrewMembers();
			CrewMember crewMember;
			switch (type) {
			case "Human": if (name.length() == 0) { crewMember = new Human(ship); } 
				else {crewMember = new Human(ship, name); } break;
			case "Robot": if (name.length() == 0) { crewMember = new Robot(ship); } 
				else {crewMember = new Robot(ship, name); } break;
			case "Cyborg": if (name.length() == 0) { crewMember = new Cyborg(ship); } 
				else {crewMember = new Cyborg(ship, name); } break;
			case "Alien": if (name.length() == 0) { crewMember = new Alien(ship); } 
				else {crewMember = new Alien(ship, name); } break;
			case "Lizard": if (name.length() == 0) { crewMember = new Lizard(ship); } 
				else {crewMember = new Lizard(ship, name); } break;
			case "Unicorn": if (name.length() == 0) { crewMember = new Unicorn(ship); } 
				else {crewMember = new Unicorn(ship, name); } break;
			default: crewMember = new Human(ship);
			}
			shipMembers.add(crewMember);
		}
		
		gameLoop();
	}
	
	
	public void gameLoop() {
		inOut.print("Day number: " + dayNumber + "/" + maxDays);
		inOut.print("1) View Crew Member and/or do a Crew Member Action");
		inOut.print("2) View Spaceship status");
		inOut.print("3) Visit the nearest space outpost");
		inOut.print("4) Continue to next day");
		int choice = inOut.collectInt(1, 4);
		
		switch (choice) {
		case 1: selectCrewMember(); break;
		case 2:	inOut.print(ship.getName());
			inOut.print("Shield level: " + ship.getShipShields() + "/10");
			inOut.print("Spaceship pieces found: " + partsFound + "/" + partsToFind);
			inOut.print("Press enter to return to the control panel");
			inOut.collectString();
			gameLoop();
		case 3:	goToOutpost(); break;
		case 4:	newDay(); break;
		default: gameLoop();
		}
	}
	
	
	public void selectCrewMember() {
		ArrayList<CrewMember> crewMembers = ship.getCrewMembers();
		int index = 0;
		
		// This part displays options for all crew members that have actions remaining
		ArrayList<CrewMember> membersWithActions = new ArrayList<CrewMember>();
		ArrayList<CrewMember> membersWithoutActions = new ArrayList<CrewMember>();
		for (int i = 0; i < crewMembers.size(); i++) {
			CrewMember person = crewMembers.get(i);
			
			// Ensure that only crew members that have actions are shown
			if (person.getActions() > 0) {
				membersWithActions.add(person);
				index = membersWithActions.size();
				inOut.print(index + ") " + person.getName() + ", " + person.getTypeInfo().get("Type"));
			} else { // Add crew members without actions to another list
				membersWithoutActions.add(person);
			}
		}
		
		// This part displays options for crew members without actions remaining
		for (int i = 0; i < membersWithoutActions.size(); i++) {
			CrewMember person = membersWithoutActions.get(i);
			inOut.print((index + i + 1) + ") " + person.getName() + ", " + person.getTypeInfo().get("Type") 
					+ " (No actions remaining)");
		}
		
		inOut.print((crewMembers.size() + 1) + ") Back to control panel");

		// Collects the user input
		int choice = inOut.collectInt(1, crewMembers.size() + 1);
		
		// Completes the action for this crew member and the other chosen one
		if (choice < (index + 1)) {
			completeAction(membersWithActions.get(choice - 1));
		} else if (choice < (crewMembers.size() + 1)) {
			inOut.print(membersWithoutActions.get(choice - index - 1).toString());
			inOut.print("");
			inOut.print("Actions remaining: 0");
			inOut.print("Press enter to Crew Member selection");
			inOut.collectString();
			selectCrewMember();
		} else {
			gameLoop();
		}
		
	}
	
	
	public void completeAction(CrewMember crewMember) {
		inOut.print(crewMember.toString());
		inOut.print("");
		inOut.print("Actions remaining: " + crewMember.getActions());
		inOut.print("1) Eat food or use medical supplies");
		inOut.print("2) Sleep");
		inOut.print("3) Repair Ship Shields");
		inOut.print("4) Search planet");
		inOut.print("5) Pilot the ship to a new planet");
		inOut.print("6) Back to Crew Member selection");
		int choice = inOut.collectInt(1, 6);
		
		switch (choice) {
		case 1: if (useItem(crewMember)) { 
				gameLoop(); 
			} else { 
				completeAction(crewMember); 
			}; 
			break;
		case 2: sleep(crewMember); gameLoop(); break;
		case 3: repairShip(crewMember); gameLoop(); break;
		case 4: if (!searchPlanet(crewMember)) gameLoop(); break;
		case 5: if (pilotShip(crewMember)) { 
				gameLoop(); 
			} else { 
				completeAction(crewMember); 
			}; 
			break;
		default: selectCrewMember();
		}
	}
	
	public void viewShop() {
		ArrayList<Consumable> keys = shop.getKeys();
		Integer size = keys.size();
		
		// Displays all the items in the shop as options
		int i;
			for (i = 0; i < size; i++) {
				Consumable item = keys.get(i);
				String classification = item.getClassification();
				String name = item.getName();
				String description = item.getDescription();
				inOut.print((i + 1) + ") " + classification + " item: " + name + ", " 
				+ description + ", " + shop.get(item) 
				+ " in stock, $" + item.getPrice());
			}
		inOut.print((i + 1) + ") Back to Outpost");
		int choice = inOut.collectInt(1, i + 1);
		
		
		// TODO this is a real mess of a section
		if (choice != i + 1) {
			Consumable item = keys.get(choice - 1);
			boolean isPurchasing = true;
			while (isPurchasing && shop.get(item) != 0) {
				inOut.print(item.getName());
				inOut.print(item.getDescription());
				inOut.print("Price: $" + item.getPrice());
				inOut.print("Quantity: " + shop.get(item));
				
				if (item.getPrice() > ship.getMoney()) {
					inOut.print("You cannot afford this item");
					inOut.print("Press enter to return to the shop");
					inOut.collectString();
					viewShop();
				} else {
					inOut.print("1) Purchase item");
					inOut.print("2) Back to shop");
					switch (inOut.collectInt(1, 2)) {
					case 1: shop.removeItem(item); inventory.addItem(item); 
						ship.addMoney(-item.getPrice()); break;
					case 2: isPurchasing = false; break;
					}
				} 
				viewShop();
			} 
		} else {
			goToOutpost();
		}
	}
	
	public void viewInventory() {
		ArrayList<Consumable> keys = inventory.getKeys();
		Integer size = keys.size();
		// Displays all the items in the shop as options
		int i;
			for (i = 0; i < size; i++) {
				Consumable item = keys.get(i);
				String classification = item.getClassification();
				String name = item.getName();
				String description = item.getDescription();
				inOut.print(classification + " item: " + name + ", " 
				+ description + ", Quantity: " + inventory.get(item));
			}
		inOut.print("Press enter to continue");
		inOut.collectString();
		goToOutpost();
	}
	
	public void goToOutpost() {
		inOut.print("1) View Objects For Sale");
		inOut.print("2) View Inventory");
		inOut.print("3) Back to Control Panel");
		int choice = inOut.collectInt(1,  3);
		
		switch (choice) {
		case 1: viewShop(); break;
		case 2: viewInventory(); break;
		default: gameLoop();
		}
	}


	/**
	 * Conducts all processes related to ending the day
	 */
	public void newDay() {
		// End the day for each user. Also add to the score if the crew member has max status
		int i;
		int size = ship.getCrewMembers().size();
		int removedMembers = 0;
		for (i = 0; i < size; i++) {
			CrewMember person = ship.getCrewMembers().get(i - removedMembers);
			HashMap <String, Integer> status = person.getStatus();
			HashMap <String, Integer> maxStatus = person.getMaxStats();
			if (status.get("Health") == maxStatus.get("Health")) ship.addScore(10);
			if (status.get("Energy") == maxStatus.get("Energy")) ship.addScore(10);
			if (status.get("Nutrition") == maxStatus.get("Nutrition")) ship.addScore(10);
			person.endDay();
			if (!ship.getCrewMembers().contains(person)) removedMembers = removedMembers + 1;
		}
		dayNumber = dayNumber + 1;
		ship.addMoney(20);
		
		// Ends the game if there are no more crew members left alive, or if the last day ended
		if (ship.getCrewMembers().size() == 0 || dayNumber > maxDays) {
			endGame(false);
		}
		
		int sumAttackProbability = Arrays.stream(ATTACK_PROBABILITY).sum();
		int randInt = (new Random()).nextInt(sumAttackProbability);
		if (randInt < ATTACK_PROBABILITY[0] && inventory.size() > 0) {
			Consumable key = (Consumable) pickRandom(inventory.getKeys());
			inOut.print("Aliens have attacked your ship! They stole your " + 
					key.getName() + "!");
			inventory.removeItem(key);
			inOut.print("You now have " + (inventory.get(key)) + " " + 
					key.getName() + "(s) remaining");
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
		partsHere = true;
		inOut.print("Daily Score: " + ship.getDailyScore());
		inOut.print("Press enter to continue");
		inOut.collectString();
		
		gameLoop();
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
					endGame(true);
				} else {
					inOut.print("You now have " + partsFound + " out of " + partsToFind + 
							" parts");
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
			int moneyFound = (Integer) pickRandom(FINDABLE_MONEY);
			inOut.print(crewMember.getName() + " found $" + moneyFound + "!");
			ship.addMoney(moneyFound);
		} else {
			inOut.print(crewMember.getName() + " didn't find anything!");
		}
		return false;
	}
	
	
	/**
	 * Finds an item to use in the inventory and uses it
	 * @param crewMember the crew member to use the item on
	 * @return a boolean representing whether or not an item was used
	 */
	public boolean useItem(CrewMember crewMember) {
		ArrayList<Consumable> keys = inventory.getKeys();
		
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

		// Collects the user input
		int choice = inOut.collectInt(1, i + 1);
		
		// Uses the selected item and reduces the quantity of that item in the inventory
		if (choice < (i + 1)) {
			Consumable item = keys.get(choice - 1);
			
			item.useItem(crewMember);
			
			inventory.removeItem(item);
			
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
	 * Repairs the ship by the repair amount that the crew member has
	 * @param crewMember the crew member to complete the action
	 */
	public void repairShip(CrewMember crewMember) {
		crewMember.completeAction();
		ship.addShipShields(crewMember.getRepairAmount());
	}
	
	
	/**
	 * Finds another crew member and pilots the ship to another planet with them
	 * @param crewMember the crew member to complete the action
	 * @return whether another crew member was chosen or not
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
	 * Prints an end game screen for the player after they have found all the parts,
	 * lost all their crew members or run out of time
	 * @param isVictory whether or not the player won the game
	 */
	public void endGame(boolean isVictory) {
		if (isVictory) {
			inOut.print("You have piloted the ship " + ship.getName() + " to victory"); 
			inOut.print("and found all the missing parts of your ship!");
			inOut.print("Well done!");
		} else if (ship.getCrewMembers().size() == 0) {
			inOut.print("Oh no! All of the members of your crew have died!");
			inOut.print("The " + ship.getName() + " repair mission ends in tragedy");
		} else {
			inOut.print("It has been " + maxDays + " days");
			inOut.print(ship.getName() + " is now damaged beyond repair");
		}
		inOut.print("");
		inOut.print("Final Score: " + ship.getTotalScore());
		inOut.print("");
		inOut.print("Would you like to play again?");
		inOut.print("1) Yes!");
		inOut.print("2) No, thank you");
		if (inOut.collectInt(1, 2) == 1) createGame();
		
	}
	
	
	/**
	 * Gets a random crew member from an ArrayList of crew members
	 * @param list an ArrayList to chose a crew member from
	 * @return a random crew member from the list
	 */
	public Object pickRandom(ArrayList<?> list) {
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(list.size());
		return list.get(randInt);
	}
	
	
	/**
	 * Gets a random item from an Object array
	 * @param list an Object array to chose an item from
	 * @return a random item from the array
	 */
	public Object pickRandom(Object[] list) {
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(list.length);
		return list[randInt];
	}
	
	
}
