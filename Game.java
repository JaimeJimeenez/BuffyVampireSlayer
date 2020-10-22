package logic;

import java.util.Random;
import view.GamePrinter;
import characters.*;

public class Game {

	public static final Random rand = new Random();
	
	public Game(Long seed, Level level) {
		
		this.seed = seed;
		this.level = level;
		cycles = 0;
		this.player = new Player();
		printedGame = new GamePrinter(this, setDim_x(this.level), setDim_y(this.level));
		gameBoard = new GameObjectBoard(level);
	}
	
	//SET DIMENSIONS
	public int setDim_x(Level level) {
		if (level.getName() == "easy") return 8;
		if (level.getName() == "hard") return 7;
		if (level.getName() == "insane") return 5;
		else return - 1;
	}
	
	public int setDim_y(Level level) {
		if (level.getName() == "easy") return 4;
		if (level.getName() == "hard") return 3;
		if (level.getName() == "insane") return 6;
		else return - 1;
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
			if (elem != null && elem.isInPosition(j, i) && elem.getHealth() > 0) return elem.toString();
		
		
		for (Slayer elem : gameBoard.getSlayers().getData()) 
			if (elem != null && elem.isInPosition(j, i) && elem.getHealth() > 0) return elem.toString();
		
		return " - ";
	 }
	
	public void addSlayer(int dim_x, int dim_y) {
		
		//Try and catch??
		if (player.getCoins() >= 50 && gameBoard.checkPosition(dim_x, dim_y)) {
				gameBoard.fillSlayers(dim_x - 1, dim_y - 1);
				player.setCoins();
		}
		else System.out.println("Error: Can not add the Slayer requested.");
		
	}
	
	public void update() {
		
		if (cycles % 2 == 0) gameBoard.advance();
		if (gameBoard.addVampire(level)) gameBoard.fillVampires(level);
		player.updateCoins();
		gameBoard.attack();
		cycles++;
		gameBoard.removeDeadObjects();
		checkEnd();
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
