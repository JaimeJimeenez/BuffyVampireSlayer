package logic;

import view.GamePrinter;
import characters.*;

public class Game {

	
	public Game(Long seed, Enum level) {
		this.seed = seed;
		this.level = level;
		
		player = new Player();
		slayerList = new SlayerList();
		vampireList = new VampireList(level);
		cycles = 0;
	}
	
	public Enum getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}
	
	public SlayerList getSlayerList() {
		return slayerList;
	}
	
	public VampireList getVampireList() {
		return vampireList;
	}
	
	//Methods of the game
	public void update() {
		player.setCoins();
		//Advance a position vampire
	}
	
	public void attack() {
		for (Vampire elem : vampireList.getData()) {
			//if (elem.getPosition() == Slayer.position - 1) Slayer.setHealth(Slayer.getHealth() - elem.getDamage());
		}
	}
	
	public boolean checkEnd() {
		slayerList.checkDeaths();
		return true;
	}
	
	public int getCycles() {
		return cycles;
	}
	
	public void updateCycles() {
		cycles++;
	}
	
	private Long seed;
	private Enum level;
	private GamePrinter game;
	private SlayerList slayerList;
	private VampireList vampireList;
	private Player player;
	private int cycles;
}
