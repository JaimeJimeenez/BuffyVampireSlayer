package characters;

import logic.Level;

public class VampireList {

	public VampireList(Level level) {
		if (level.name() == "EASY") data = new Vampire[3];
		if (level.name() == "HARD") data = new Vampire[5];
		if (level.name() == "INSANE") data = new Vampire[10];
	}
	
	public Vampire[] getData() {
		return data;
	}
	
	private Vampire[] data;
}
