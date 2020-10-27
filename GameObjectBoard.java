package logic;

import java.util.Random;

import characters.*;

public class GameObjectBoard {
	
	public static final Random rand = new Random();
	
	public GameObjectBoard(Level level) {
		this.vampireList = new VampireList(level.getNumberOfVampires());
		this.slayerList = new SlayerList();
		this.level = level;
		numberOfSlayers = 0;
		numberOfVampires = 0;
	}
	
	public VampireList getVampires() {
		return vampireList;
	}
	
	public SlayerList getSlayers() {
		return slayerList;
	}
	
	public boolean checkPosition(int dim_x, int dim_y) {
		
		for (Slayer elem : slayerList.getData()) {
			if (elem != null && elem.isInPosition(dim_x, dim_y)) {
					System.out.println("Error: A Slayer is already in that position.");
					return false;
			}
		}
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null && elem.isInPosition(dim_x, dim_y)) {
					System.out.println("Error: A Vampire is already in that position.");
					return false;
				}
			}
		
		return true;
	}
	
	public boolean addVampire() {
		return (rand.nextDouble() < level.getVampireFrequency());
	}
	
	public VampireList fillVampires() {
		Vampire newVampire = new Vampire(level);
		
		for (Slayer elem : slayerList.getData()) {
			if (elem != null && elem.isInPosition(newVampire.getDim_x(), newVampire.getDim_y())) return vampireList;
		}
		
		for (Vampire elem : vampireList.getData()) {
			if (elem != null && elem.isInPosition(newVampire.getDim_x(), newVampire.getDim_y())) return vampireList;
		}
		
		if (numberOfVampires < level.getNumberOfVampires()) {
			vampireList.getData()[numberOfVampires] = newVampire;
			numberOfVampires++;
		}
		
		return vampireList;
	}
	
	public void dataVampires() {
		int onBoard = 0;
		int remaining = 0;
		
		for (Vampire elem : vampireList.getData()) {
			if (elem == null) remaining++;
			if (elem != null && elem.getHealth() != 0) onBoard++;
		}
		
		System.out.println("Remaining vampires: " +remaining);
		System.out.println("Vampires on board: " +onBoard);
		
	}
	
	public SlayerList fillSlayers(int dim_x, int dim_y) {
		Slayer newSlayer = new Slayer(dim_x, dim_y);
		
		slayerList.getData()[numberOfSlayers] = newSlayer;
		numberOfSlayers++;
		
		return slayerList;
	}
	
	public void advance() {
		for (Vampire eachVampire: vampireList.getData()) 
			for (Slayer eachSlayer : slayerList.getData()) {
				if (eachVampire != null && eachSlayer != null && !eachSlayer.isInPosition(eachVampire.getDim_x() - 1, eachVampire.getDim_y()) && eachVampire.getCycles() % 2 == 0) eachVampire.updateDim_x(eachVampire.getDim_x() - 1);
			}
	}
	
	public void updateVampireCycles() {
		for (Vampire elem : vampireList.getData())  if (elem != null) elem.setCycles();
	}
	
	public void attack() {
		
		for (Slayer eachSlayer : slayerList.getData()) {
			for (Vampire eachVampire : vampireList.getData()) {
				if (eachSlayer != null && eachVampire != null) {
					if (eachSlayer.getDim_y() == eachVampire.getDim_y()) eachVampire.setHealth(eachVampire.getHealth() - 1);
				}
			}	
		}
		
		for (Vampire eachVampire : vampireList.getData()) {
			for (Slayer eachSlayer : slayerList.getData()) {
				if (eachSlayer != null && eachVampire != null) {
					if (eachSlayer.getDim_y() == eachVampire.getDim_y() && eachSlayer.getDim_x() + 1 == eachVampire.getDim_x()) eachSlayer.setHealth(eachSlayer.getHealth() - 1);
				}
			}
		}
	}
	
	public SlayerList removeSlayer(int i) {
		System.arraycopy(slayerList.getData(), i + 1, slayerList.getData()[i], i, slayerList.getData().length - 1  - i);
		return slayerList;
	}
	
	//Remaining
	public void removeDeadObjects() {
		
		for (int i  = 0; i < vampireList.getData().length; i++) {
			if (vampireList.getData()[i] != null && vampireList.getData()[i].getHealth() == 0) vampireList.setData(vampireList.removeVampire(i));
		}
		
		for (int i = 0; i < slayerList.getData().length; i++) {
			if (slayerList.getData()[i] != null && slayerList.getData()[i].getHealth() == 0) this.slayerList = removeSlayer(i);
		}
		
	}

	public boolean checkEnd() {
		int cont = 0;
		
		for (Vampire elem : vampireList.getData()) {
			
			if (elem != null) {
				if (elem.getHealth() == 0) cont++;
				if (elem.getDim_x() == 0) {
					System.out.println("You loose.");
					return true;
				}
			}
		}
		
		if (cont == level.getNumberOfVampires()) {
			System.out.println("You win");
			return true;
		}
		
		return false;
	}
	
	private VampireList vampireList;
	private SlayerList slayerList;
	private Level level;
	private int numberOfSlayers;
	private int numberOfVampires;
}
