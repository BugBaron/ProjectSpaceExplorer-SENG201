package main;

import java.util.Arrays;
import java.util.Collections;
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
	
	/**
	 * Creates a new shop
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
	 * Class constructor for the GameEnvironment
	 */
	public GameEnvironment() {
		inOut = new InOutHandler();
	}
	
	
	/**
	 * Gets the InOutHandler of this GameEnvironment
	 * @return the InOutHandler
	 */
	public InOutHandler getInOut() {
		return inOut;
	}
	
	public void createGame(int maxDays, Ship ship) {
		shop = createShop();
		inventory = new Inventory();
		partsHere = true;
		dayNumber = 1;
		currentPlanet = 0;
		
		this.maxDays = maxDays;
		partsToFind = 2 * maxDays / 3;
	}
	
	// TODO this needs to be remade to work with the GUI
	public void createGame() {
		shop = createShop();
		inventory = new Inventory();
		partsHere = true;
		dayNumber = 1;
		currentPlanet = 0;
		
		//inOut.print("How many in-game days would you like the game to last? (3-10)");
		maxDays = inOut.collectInt(3, 10);
		partsToFind = 2 * maxDays / 3;
		
		//inOut.print("How many crew members would you like? (2-4)");
		int numMembers = inOut.collectInt(2, 4);
		
		ArrayList<CrewMember> selectedMembers = new ArrayList<CrewMember>();
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<CrewMember> availableList = new ArrayList<CrewMember>(Arrays.asList(
				new Human(ship), new Robot(ship), new Cyborg(ship), 
				new Alien(ship), new Lizard(ship), new Unicorn(ship)));
		for (int i = 0; i < numMembers; i++) {
			//inOut.print("Choose crew member " + (i + 1) + ":");
			for (CrewMember person : availableList) {
				//HashMap<String, String> typeInfo = person.getTypeInfo();
				//inOut.print((availableList.indexOf(person) + 1) + ") " + typeInfo.get("Type") + ", " 
				//+ typeInfo.get("Strength") + ", " + typeInfo.get("Weakness"));
			}
			CrewMember selectedPerson = availableList.get(inOut.collectInt(1, 6) - 1);
			//inOut.print("What would you like to name this " + selectedPerson.getTypeInfo().get("Type") + "?");
			String name = inOut.collectString();
			selectedMembers.add(selectedPerson);
			String new_name = name;
			Integer j = 2;
			while (names.contains(new_name)) {
				if (!name.isEmpty()) {
					new_name = name + " (" + j + ")";
					j ++;
				}
				else if (Collections.frequency(selectedMembers, selectedPerson) > 1)  {
					Integer number = Collections.frequency(selectedMembers, selectedPerson);
					new_name = Integer.toString(number);
				}
				else {
					break;
				}
			}
			names.add(new_name);
		}
		
		//inOut.print("What would you like the name of your ship to be?");
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
		
		//gameLoop();
	}
	
	
	
	
	/**
	 * Gets a list containing all the crew members of the ship
	 * @return the crew members of the ship
	 */
	public ArrayList<CrewMember> getCrewMembers() {
		return ship.getCrewMembers();
	}
	
	
	/**
	 * Gets a list containing all crew members with actions in the ship
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
	 * Gets a string representing the ship status
	 * @return a string representing the ship status
	 */
	public String getShipString() {
		String returnString = ship.toString() + "\n" + 
				"Spaceship pieces found: " + partsFound + "/" + partsToFind;
		return returnString;
	}
	
	
	/**
	 * Gets a string to show the number of days completed and the days remaining
	 * @return a string of the day number
	 */
	public String getDayString() {
		return "Day number: " + dayNumber + "/" + maxDays;
	}
	
	
	/**
	 * Purchases an item from the shop
	 * @param item the item to be purchased
	 */
	public void purchaseItem(Consumable item) {
		shop.removeItem(item); 
		inventory.addItem(item);
		inOut.print("You just purchased a " + item.getName() + "!");
		ship.addMoney(-item.getPrice());
	}
	
	/**
	 * Gets the inventory of the ship
	 * @return the inventory of the ship
	 */
	public Inventory getInventory() {
		return inventory;
	}


	/**
	 * Gets the shop of the planet
	 * @return the shop of the planet
	 */
	public Inventory getShop() {
		return shop;
	}
	
	
	/**
	 * Gets the total money collected on the ship
	 * @return the money on the ship
	 */
	public int getMoney() {
		return ship.getMoney();
	}
	
	
	/**
	 * Conducts all processes related to ending the day
	 */
	public boolean newDay() {
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
			return true; // Indicates that the game has ended
			//endGame(false);
		} else {
			//inOut.print(false); // Indicates that the game has not ended
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
			inOut.print(false); // Indicates that the random events have no more messages
			inOut.print("Daily Score: " + ship.getDailyScore());
			return false; 
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
					//endGame(true);
					return true;
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
	 * @param item the item to use
	 */
	public void useItem(CrewMember crewMember, Consumable item) {
		// Uses the selected item and reduces the quantity of that item in the inventory
		crewMember.completeAction();
		item.useItem(crewMember);
		
		inventory.removeItem(item);
		
		inOut.print(crewMember.getName() + " used a " + item.getName());
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
		inOut.print(crewMember.getName() + " slept and restored to " + 
				crewMember.getStatus().get("Energy") + " energy");
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
	 * @param person the other crew member to complete the action
	 */
	public void pilotShip(CrewMember crewMember, CrewMember person) {
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
	}
	
	
	// TODO needs some sections edited to work with the GUI
	/**
	 * Prints an end game screen for the player after they have found all the parts,
	 * lost all their crew members or run out of time
	 * @param isVictory whether or not the player won the game
	 */
	public void endGame(boolean isVictory) {
		if (isVictory) {
			inOut.print("Congratulations!");
			inOut.print("You have piloted the ship " + ship.getName() + " to victory"); 
			inOut.print("and found all the missing parts of your ship!");
			inOut.print("Well done!");
		} else if (ship.getCrewMembers().size() == 0) {
			inOut.print("Sorry, you lost!");
			inOut.print("Oh no! All of the members of your crew have died!");
			inOut.print("The " + ship.getName() + " repair mission ends in tragedy");
		} else {
			inOut.print("Sorry, you lost!");
			inOut.print("It has been " + maxDays + " days");
			inOut.print(ship.getName() + " is now damaged beyond repair");
		}
		inOut.print("\n");
		inOut.print("Final Score: " + ship.getTotalScore());
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
