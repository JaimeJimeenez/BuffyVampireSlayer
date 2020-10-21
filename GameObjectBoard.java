package logic;

import characters.*;

public class GameObjectBoard {
	
	public GameObjectBoard(Level level) {
		this.vampireList = new VampireList(level);
		this.slayerList = new SlayerList();
	}
	
	public VampireList getVampires() {
		return vampireList;
	}
	
	public void addVampire(Vampire newVampire) {
		for (int i = 0; i < vampireList.getData().length; i++) 
			if (vampireList.getData()[i] == null) vampireList.getData()[i] = newVampire;
	}
	
	private VampireList vampireList;
	private SlayerList slayerList;
}
