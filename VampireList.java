package characters;

import logic.Level;

public class VampireList {

	public VampireList(Level level) {
		if (level.getName() == "easy") data = new Vampire[3];
		if (level.getName() == "hard") data = new Vampire[5];
		if (level.getName() == "insane") data = new Vampire[10];
	}
	
	public Vampire[] getData() {
		return data;
	}
	
	private Vampire[] data;
}
