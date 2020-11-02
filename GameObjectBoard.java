package logic;

import characters.*;

public class GameObjectBoard {
	
	public GameObjectBoard(Level level) {
		this.level = level;
		this.vampireList = new VampireList(level.getNumberOfVampires());
		this.slayerList = new SlayerList();
		numberOfSlayers = 0;
	}
	
	public VampireList getVampireList() { return vampireList; }
	
	public SlayerList getSlayerList() { return slayerList; }
	
	public boolean checkPosition(int dim_x, int dim_y) {
		
		for (Slayer elem : slayerList.getData()) {
			if (elem != null && elem.isInPosition(dim_x, dim_x)) return false;
		}
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null && elem.isInPosition(dim_x, dim_y)) return false; 
		}
		
		return true;
	}
	
	public void fillSlayer(int dim_x, int dim_y) {
		Slayer newSlayer = new Slayer(dim_x, dim_y);
		
		slayerList.getData()[numberOfSlayers] = newSlayer;
		numberOfSlayers++;
		
	}
	
	public void advance() {
		
		for (Vampire elem : vampireList.getData())
			if (elem != null && checkPosition(elem.getDim_x() - 1, elem.getDim_y()) && elem.getCycles() % 2 == 0) elem.updateDim_x();
		
	}
	
	public void attack() {
		
		for (Slayer eachSlayer : slayerList.getData()) {
			for (Vampire eachVampire : vampireList.getData()) {
				if (eachSlayer != null && eachVampire != null) {
					if (eachSlayer.getDim_y() == eachVampire.getDim_y()) eachVampire.updateHealth();
				}
			}	
		}
		
		for (Vampire eachVampire : vampireList.getData()) {
			for (Slayer eachSlayer : slayerList.getData()) {
				if (eachSlayer != null && eachVampire != null) {
					if (eachSlayer.getDim_y() == eachVampire.getDim_y() && eachSlayer.getDim_x() + 1 == eachVampire.getDim_x()) eachSlayer.updateHealth();
				}
			}
		}
	}
	
	public boolean addVampire() { return Player.rand.nextDouble() < level.getVampireFrequency(); }
	
	public VampireList fillVampire() {
		Vampire newVampire = new Vampire (level);
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null && elem.isInPosition(newVampire.getDim_x(), newVampire.getDim_y())) return vampireList;
		}
		
		for (Slayer elem : slayerList.getData()) {
			if (elem != null && elem.isInPosition(newVampire.getDim_x(), newVampire.getDim_x())) return vampireList;
		}
		
		for (int i = 0; i < vampireList.getLength(); i++) {
			if (vampireList.at(i) == null) { 
				vampireList.getData()[i] = newVampire; 
				break;
			}
		}
		
		return vampireList;
	}
	
	public void removeVampires(int counterDeads) {
		VampireList newVampireList = new VampireList(vampireList.getLength() - counterDeads);
		int i = 0;
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null && elem.getHealth() != 0) {
				newVampireList.getData()[i] = elem;
				i++;
			}
		}
		
		vampireList = newVampireList;
		
	}
	
	public void removeSlayers() {
		SlayerList newSlayerList = new SlayerList();
		int i = 0;
		
		for (Slayer elem : slayerList.getData()) 
			if (elem != null && elem.getHealth() != 0) {
				newSlayerList.getData()[i] = elem;
				i++;
			}
		
		slayerList = newSlayerList;
	
	}
	
	public void removeDeadObjects() {
		int counterDeads = 0;
		int aux = counterDeads;
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null && elem.getHealth() <= 0) counterDeads++;
		}
		
		if (counterDeads != 0 && aux != counterDeads) {
			removeVampires(counterDeads);
			aux = counterDeads;
		}
		
		removeSlayers();
		
	}
	
	public boolean checkEnd() {
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null) {
				if (elem.getDim_x() == 0) {
					System.out.println("Vampires win."); 
					return true;
				}
			}
			
		}
		
		if (vampireList.getLength() == 0) {
			System.out.println("Player wins");
			return true;
		}
		
		return false;
	}
	
	//Attributes
	private Level level;
	private VampireList vampireList;
	private SlayerList slayerList;
	private int numberOfSlayers;
}
