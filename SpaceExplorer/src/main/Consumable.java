package main;

public class Consumable {
	
	
	
	public Consumable() {}
	
	public int getPrice() {
		return 25;
	}
	
	public String getName() {
		return "Space Plague Cure";
	}
	
	public String getDescription() {
		return "Cures the space plague";
	}
	
	public String getClassification() {
		return "Medical";
	}

	public void useItem(CrewMember person) {
		person.setHasSpacePlague(false);
	}
	
}
