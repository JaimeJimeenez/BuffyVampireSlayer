package logic;

import characters.*;
import view.GamePrinter;

public class Game {
	
	public Game(Long seed, Level level) {
		
		this.seed = seed;
		this.level = level;
		this.gameBoard = new GameObjectBoard(level);
		printedGame = new GamePrinter(this, level.getDim_y(), level.getDim_x());
		
		cycles = 1;
		player = new Player();
		
		end = false;
	}
	
	public static Long getSeed() { return seed; }
	
	public Level getLevel() { return level; }
	
	public int getCycles() { return cycles; }
	
	public Player getPlayer() { return player; }
	
	public boolean getEnd() { return end; }
	
	public void setEnd(boolean end) { this.end = end; }
	
	public void getData() {
		System.out.println("Number of cycles: " + cycles);
		System.out.println("Coins: " + player.getCoins());
		gameBoard.getVampireList().dataVampires();
	}
	
	public void addSlayer(int dim_x, int dim_y) { 
		gameBoard.fillSlayer(dim_x - 1, dim_y - 1); 
		player.boughtSlayer();
	}
	
	
	public void updateCycles() { cycles++; }
	
	public void update() {
		
		gameBoard.advance();
		gameBoard.attack();
		if (gameBoard.addVampire()) gameBoard.fillVampire();
		player.updateCoins();
		gameBoard.removeDeadObjects();
		checkEnd();
		gameBoard.getVampireList().updateVampireCycles();
	}
	
	public String getPositionToString(int i, int j) {
		
		for (Vampire elem : gameBoard.getVampireList().getData()) {
			if (elem != null && elem.isInPosition( j, i )) return elem.toString();
		}
		
		for (Slayer elem : gameBoard.getSlayerList().getData()) {
			if (elem != null && elem.isInPosition( j,  i )) return  elem.toString();
		}
		
		return " - ";
	}
	
	public void checkEnd() {
		end = gameBoard.checkEnd();
	}
	
	public String toString() { return printedGame.toString(); }
	
	//Attributes
	private static Long seed;
	private Level level;
	private GameObjectBoard gameBoard;
	private GamePrinter printedGame;
	
	private int cycles;
	private Player player;
	private boolean end;
}
