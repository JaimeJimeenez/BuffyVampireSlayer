package characters;

public class VampireList {

	public VampireList(int numberOfVampires) { data = new Vampire[numberOfVampires]; }
	
	public Vampire[] getData() { return data; }
	
	public int getLength() { return data.length; }
	
	public Vampire at(int i) { return data[i]; }
	
	public void dataVampires() {
		int onBoard = 0;
		int remaining = 0;
		
		for (Vampire elem : data) {
			if (elem == null) remaining++;
			if (elem != null && elem.getHealth() != 0) onBoard++;
		}
		
		System.out.println("Remaining vampires: " + remaining);
		System.out.println("Vampires on the board: " + onBoard);
		
	}
	
	public void updateVampireCycles() {
		for (Vampire elem : data)  if (elem != null) elem.updateCycles();
	}
	
	private Vampire[] data;
	
}
