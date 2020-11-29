package logic;

import view.*;
import control.Controller;
import logic.gameObjects.*;

public class Game implements IPrintable{
	
	public Game(Long seed, Level level) {
		Game.seed = seed;
		this.level = level;
		
		this.printable = this;
		this.gameBoard = new GameObjectBoard(level);
		this.printedGame = new GamePrinter(printable, level.getDimX(), level.getDimY());
		
		this.player = new Player();
		this.cycles = 0;
		this.userExit = false;
		
		Vampire.initVampireData();
		Vampire.remaining = level.getNumberOfVampires();
	}
	
	public static Long getSeed() { return seed; }
	
	public Level getLevel() { return level; }
	
	public int getDim_X() { return level.getDimX(); }
	
	public int getDim_Y() { return level.getDimY(); }
	
	public boolean addSlayer(int pos_x, int pos_y) {
		
		if (!isPositionEmpty(pos_x, pos_y) || (pos_x == getDim_X() - 1)) {
			System.out.println(Controller.invalidPositionMsg);
			return false;
		}
		
		if (player.getCoins() >= Player.VALUE_SLAYER) {
			player.boughtSlayer();
			gameBoard.addSlayer(pos_x, pos_y, this);
			return true;
		}
		
		System.out.println("Not enough coins");
		return false;
	}
	
	public void update() {
		player.updateCoins();
		gameBoard.update(level, this);
		cycles++;
	}
	
	public void reset() {
		cycles = 0;
		Vampire.remaining = level.getNumberOfVampires();
		Vampire.initVampireData();
		player.resetCoins();
		gameBoard.reset();
		
	}
	
	public void exitGame() { userExit = true; }
	
	//Additional methods
	
	public boolean isPositionEmpty(int pos_x, int pos_y) { return gameBoard.isPositionEmpty(pos_x, pos_y); }
	
	public IAttack getAttackableInPosition(int pos_x, int pos_y) { return gameBoard.getAttackableInPosition(pos_x, pos_y); }
	
	public boolean isFinished() {
		
		if (userExit) return true;
		
		return gameBoard.isFinished();
	}
	
	public String getWinnerMessage() {
		
		if (userExit) return "Nobody wins...";
		
		if (Vampire.hasArrived) return "Vampires win!";
		
		return "Player wins";
	}
	
	public void removeDeadObjects() { gameBoard.removeDeadObjects(); } // To be modified
	
	@Override
	public String getPositionToString(int y, int x) { return gameBoard.getPositionToString(y, x); }
	
	
	@Override
	public String getInfo() { 
		StringBuilder returnInfo = new StringBuilder();
		
		returnInfo.append("Number of cycles: " + cycles + "\n");
		returnInfo.append("Coins: " + player.getCoins() + "\n");
		returnInfo.append("Remaining vampires: " + Vampire.remaining + "\n");
		returnInfo.append("Vampires on the board: " + Vampire.onBoard + "\n");
		
		return returnInfo.toString();
	}
	
	public String toString() { return printedGame.toString(); }
	
	private static Long seed;
	private Level level;
	private IPrintable printable;
	private GameObjectBoard gameBoard;
	private GamePrinter printedGame;
	private Player player;
	private int cycles;
	private boolean userExit;
	
}
