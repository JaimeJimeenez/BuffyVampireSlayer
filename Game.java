package logic;

import java.util.Random;
import view.GamePrinter;
import characters.*;

public class Game {

	public static final Random rand = new Random();
	
	public Game(Long seed, Level level) {
		
		this.seed = seed;
		this.level = level;
		cycles = 1;
		this.player = new Player();
		printedGame = new GamePrinter(this, setDim_x(), setDim_y());
		gameBoard = new GameObjectBoard(level);
	}
	
	//SET DIMENSIONS
	public int setDim_x() {
		return level.getDim_x();
	}
	
	public int setDim_y() {
		return level.getDim_y();
	}
	
	//Getters
	public Long getSeed() {
		return seed;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getCycles() {
		return cycles;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GameObjectBoard getGameObjectBoard() {
		return gameBoard;
	}
	
	public String getPositionToString(int i, int j) {
		
		for (Vampire elem : gameBoard.getVampires().getData())
			if (elem != null && elem.isInPosition(j, i)) return elem.toString();
		
		
		for (Slayer elem : gameBoard.getSlayers().getData()) 
			if (elem != null && elem.isInPosition(j, i)) return elem.toString();

		return " - ";
	 }
	
	public void updateCycles() {
		cycles++;
	}
	
	public void addSlayer(int dim_x, int dim_y) {
		
		if (player.getCoins() >= 50 && gameBoard.checkPosition(dim_x, dim_y)) {
				gameBoard.fillSlayers(dim_x - 1, dim_y - 1);
				player.setCoins();
		}
		else System.out.println("Error: Can not add the Slayer requested.");
		
	}
	
	public void update() {
		
		gameBoard.advance();
		if (gameBoard.addVampire()) gameBoard.fillVampires();
		player.updateCoins();
		gameBoard.attack();
		gameBoard.removeDeadObjects();
		gameBoard.updateVampireCycles();
		checkEnd();
	}
	
	public void dataVampires() {
		gameBoard.dataVampires();
	}
	
	public boolean checkEnd() {
		return gameBoard.checkEnd();
	}
	
	public String toString() {
		return printedGame.toString();
	}

	private Long seed;
	private Level level;
	private int cycles;
	private Player player;
	private GamePrinter printedGame;
	private GameObjectBoard gameBoard;
	
}
