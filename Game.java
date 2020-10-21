package logic;

import java.util.Random;
import view.GamePrinter;
import characters.*;

public class Game {

	public static final Random rand = new Random();
	
	public Game(Long seed, Level level) {
		
		this.seed = seed;
		this.level = level;
		this.player = new Player();
		printedGame = new GamePrinter(this, setDim_x(this.level), setDim_y(this.level));
		gameBoard = new GameObjectBoard(level);
		
	}
	
	//SET DIMENSIONS
	public int setDim_x(Level level) {
		if (level.name() == "EASY") return 8;
		if (level.name() == "HARD") return 7;
		if (level.name() == "INSANE") return 5;
		else return - 1;
	}
	
	public int setDim_y(Level level) {
		if (level.name() == "EASY") return 4;
		if (level.name() == "HARD") return 3;
		if (level.name() == "INSANE") return 6;
		else return - 1;
	}
	
	//Getters
	public Long getSeed() {
		return seed;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GameObjectBoard getGameObjectBoard() {
		return gameBoard;
	}
	
	public String getPositionToString(int i, int j) {
		String returnCharacter = " - ";
		
		if (gameBoard.getVampires().getData().length != 0) {
			for (Vampire elem : gameBoard.getVampires().getData()) 
				if (elem.getDim_x() == i && elem.getDim_y() == j) returnCharacter = elem.toString();
		}
		
		return returnCharacter;
	 }
	
	public void update() {
		addVampire();
		player.setCoins();
	}
	
	public void addVampire() {
		
		Vampire newVampire = new Vampire(level);
		gameBoard.addVampire(newVampire);
	}
	
	public boolean checkEnd() {
		return false;
	}
	
	public String toString() {
		return printedGame.toString();
	}
	

	
	private Long seed;
	private Level level;
	private Player player;
	private GamePrinter printedGame;
	private GameObjectBoard gameBoard;
}
