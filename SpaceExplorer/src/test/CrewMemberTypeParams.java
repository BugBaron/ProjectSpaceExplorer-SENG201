package test;

import java.util.ArrayList;

import main.Ship;
import main.CrewMemberTypes.Human;
import main.CrewMemberTypes.Robot;
import main.CrewMemberTypes.Unicorn;
import main.CrewMemberTypes.Cyborg;
import main.CrewMemberTypes.Alien;
import main.CrewMemberTypes.Lizard;

class CrewMemberTypeParams {
	public static ArrayList<Object> getHuman(Ship ship, String name) {
		ArrayList<Object> returnList = new ArrayList<>();
		if (name.length() == 0) {
			returnList.add(new Human(ship));
		} else {
			returnList.add(new Human(ship, name));
		}
		returnList.add(8);
		returnList.add(10);
		returnList.add(10);
		returnList.add(1);
		returnList.add(0);
		returnList.add(0);
		returnList.add(2);
		if (name.length() == 0) {
			returnList.add("Donald Trump");
		} else {
			returnList.add(name);
		}
		returnList.add("Human");
		returnList.add("1 health restored at end of each day");
		returnList.add("2 less maximum health (8 Health)");
		returnList.add(false);
		return returnList;
	}
	
	
	public static ArrayList<Object> getRobot(Ship ship, String name) {
		ArrayList<Object> returnList = new ArrayList<>();
		if (name.length() == 0) {
			returnList.add(new Robot(ship));
		} else {
			returnList.add(new Robot(ship, name));
		}
		returnList.add(10);
		returnList.add(5);
		returnList.add(10);
		returnList.add(0);
		returnList.add(0);
		returnList.add(2);
		returnList.add(2);
		if (name.length() == 0) {
			returnList.add("Terminator");
		} else {
			returnList.add(name);
		}
		returnList.add("Robot");
		returnList.add("2 nutrition restored at end of each day");
		returnList.add("5 less maximum energy (5 Energy)");
		returnList.add(false);
		return returnList;
	}
	
	
	public static ArrayList<Object> getCyborg(Ship ship, String name) {
		ArrayList<Object> returnList = new ArrayList<>();
		if (name.length() == 0) {
			returnList.add(new Cyborg(ship));
		} else {
			returnList.add(new Cyborg(ship, name));
		}
		returnList.add(10);
		returnList.add(10);
		returnList.add(10);
		returnList.add(0);
		returnList.add(0);
		returnList.add(0);
		returnList.add(2);
		if (name.length() == 0) {
			returnList.add("Elon Musk");
		} else {
			returnList.add(name);
		}
		returnList.add("Cyborg");
		returnList.add("40% chance of finding a part when searching a planet");
		returnList.add("10% chance to find food, medical supplies or money (10% for each)");
		returnList.add(false);
		return returnList;
	}
	
	
	public static ArrayList<Object> getAlien(Ship ship, String name) {
		ArrayList<Object> returnList = new ArrayList<>();
		if (name.length() == 0) {
			returnList.add(new Alien(ship));
		} else {
			returnList.add(new Alien(ship, name));
		}
		returnList.add(12);
		returnList.add(10);
		returnList.add(8);
		returnList.add(0);
		returnList.add(0);
		returnList.add(0);
		returnList.add(2);
		if (name.length() == 0) {
			returnList.add("Steve Jobs");
		} else {
			returnList.add(name);
		}
		returnList.add("Alien");
		returnList.add("2 more maximum health (12 Health)");
		returnList.add("2 less maximum nutrition (8 Nutrition)");
		returnList.add(false);
		return returnList;
	}
	
	
	public static ArrayList<Object> getLizard(Ship ship, String name) {
		ArrayList<Object> returnList = new ArrayList<>();
		if (name.length() == 0) {
			returnList.add(new Lizard(ship));
		} else {
			returnList.add(new Lizard(ship, name));
		}
		returnList.add(10);
		returnList.add(10);
		returnList.add(10);
		returnList.add(0);
		returnList.add(1);
		returnList.add(1);
		returnList.add(1);
		if (name.length() == 0) {
			returnList.add("Mark Zuckerberg");
		} else {
			returnList.add(name);
		}
		returnList.add("Lizard");
		returnList.add("1 energy and nutrition restored at end of each day");
		returnList.add("Repairs ship shields 1 less");
		returnList.add(false);
		return returnList;
	}
	
	
	public static ArrayList<Object> getUnicorn(Ship ship, String name) {
		ArrayList<Object> returnList = new ArrayList<>();
		if (name.length() == 0) {
			returnList.add(new Unicorn(ship));
		} else {
			returnList.add(new Unicorn(ship, name));
		}
		returnList.add(10);
		returnList.add(10);
		returnList.add(10);
		returnList.add(0);
		returnList.add(2);
		returnList.add(0);
		returnList.add(2);
		if (name.length() == 0) {
			returnList.add("Rapidash");
		} else {
			returnList.add(name);
		}
		returnList.add("Unicorn");
		returnList.add("2 energy restored at end of each day");
		returnList.add("Starts with the space plague");
		returnList.add(true);
		return returnList;
	}
	
}
