package characters;

import logic.Level;

public class VampireList {

	public VampireList(Enum level) {
		if (level.name() == "EASY") data = new Vampire[3];
		else if (level.name() == "HARD") data = new Vampire[5];
		else data = new Vampire[10];
	}
	
	public Vampire[] getData() {
		return data;
	}
	
	public boolean checkDeaths() {
		
		for (Vampire elem : data)
			if (elem.getHealth() != 0) return false;
		
		return true;
	}
	
	public void remainingVampires() {
		int remaining = 0;
		int onBoard = 0;
		
		for (Vampire elem : data) {
			if (elem.getPosition() == -1) remaining++;
			else if (elem.getPosition() != -1 && elem.getHealth() != 0) onBoard++;
		}
		System.out.println("Remaining vampires: " +remaining);
		System.out.println("Vampires on board: " +onBoard);
	}
	
	private Vampire[] data;
}
