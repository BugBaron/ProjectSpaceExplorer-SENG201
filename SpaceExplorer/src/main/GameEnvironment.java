package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GameEnvironment {
	
	private int partsToFind;
	private int partsFound;
	private boolean partsHere;
	private Ship ship;
	private Shop shop;
	private int dayNumber;
	private int maxDays;
	private String currentPlanet;
	private ArrayList<String> PLANET_ARRAY = new ArrayList<>(Arrays.asList("Europa", "Titan", "Io", "Callisto", "Oberon", "Umbriel", "Rhea", "Phoebe", "Ananke", "Dactyl"));
	private final int[] ATTACK_PROBABILITY = {20, 20, 60};
	private final int[] ASTEROID_PROBABILITY = {30, 70};
	
	/**
	 * Conducts all processes related to ending the day
	 */
	public void newDay() {
		for (CrewMember person : ship.getCrewMembers()) {
			person.endDay();
		}
		int sumAttackProbability = 0;
		for (int probability : ATTACK_PROBABILITY) {
			sumAttackProbability = sumAttackProbability + probability;
		}
		Random randomVar = new Random();
		int randInt = randomVar.nextInt(sumAttackProbability);
		HashMap <Consumable, Integer> inventory = ship.getInventory();
		if (randInt < ATTACK_PROBABILITY[0] && inventory.size() > 0) {
			randInt = randomVar.nextInt(inventory.size());
			Consumable key = (Consumable) inventory.keySet().toArray()[randInt];
			System.out.println("Aliens have attacked your ship! They stole your " + key.getName() + "!");
			System.out.println("You now have " + (inventory.get(key) - 1) + " " + key.getName() + "(s) remaining");
			if (inventory.get(key) - 1 == 0) {
				inventory.remove(key);
			} else {
				inventory.put(key, inventory.get(key) - 1);
			}
		} else if (randInt < ATTACK_PROBABILITY[1]) {
			ArrayList<CrewMember> healthyCrewMembers = new ArrayList<CrewMember>();
			healthyCrewMembers.addAll(ship.getCrewMembers());
			healthyCrewMembers.removeIf(x -> x.getHasSpacePlague());
			if (healthyCrewMembers.size() > 0) {
				randInt = randomVar.nextInt(healthyCrewMembers.size());
				CrewMember person = healthyCrewMembers.get(randInt);
				System.out.println(person.getName() + " has contracted the space plague!");
				person.setHasSpacePlague(true);
			}
		}
	}
	
}
