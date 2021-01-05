package logic;

import view.*;

import control.Controller;
import logic.gameObjects.*;

public class Game implements IPrintable{
	
	public Game(Long seed, Level level) {
		Game.seed = seed;
		this.level = level;
		
		printable = this;
		gameBoard = new GameObjectBoard();
		printedGame = new GamePrinter(printable, level.getDimX(), level.getDimY());
		
		player = new Player();
		cycles = 0;
		userExit = false;
		
		Vampire.initVampireData(level);
	}
	
	public static Long getSeed() { return seed; }
	
	public int getDim_X() { return level.getDimX(); }
	
	public int getDim_Y() { return level.getDimY(); }
	
	public void addSlayer(int pos_x, int pos_y) {
		GameObject newSlayer = new Slayer(pos_x, pos_y, this);
		
		gameBoard.addObject(newSlayer);
		player.bought(Slayer.COST);
	}
	
	public void reset() {
		cycles = 0;
		Vampire.initVampireData(level);
		player.resetCoins();
		gameBoard.reset();
	}
	
	public void exitGame() { userExit = true; }
	
	public void update() {
		player.updateCoins();
		gameBoard.update(this);
		cycles++;
	}
	
	public void pushVampires() { 
		gameBoard.pushVampires();
		player.bought(Player.VALUE_GARLIC);
	}
	
	public void eraseVampires() {
		gameBoard.eraseVampires();
		player.bought(Player.VALUE_LIGHT);
	}
	
	public void addBloodBank(int pos_x, int pos_y, int imputCoins) {
		GameObject newBank = new BankBlood(pos_x, pos_y, imputCoins, this, player);
		
		gameBoard.addObject(newBank);
		player.bought(imputCoins);
	}
	
	public void addCoins() { player.addCoins(); }
	
	//Add Vampires
	public void isPossibleToAddVampire() {
		if (Player.rand.nextDouble() < level.getVampireFrecuency()) {
			int pos_y = Player.rand.nextInt(level.getDimY());
			if (isPossibleToAdd(level.getDimX() - 1, pos_y)) addVampire(level.getDimX() - 1, pos_y);
		}
	}
	
	public void isPossibleToAddDracula() {
		if (Player.rand.nextDouble() < level.getVampireFrecuency()) {
			int pos_y = Player.rand.nextInt(level.getDimY());
			if (isPossibleToAdd(level.getDimX() - 1, pos_y) && !Dracula.isAlive) addDracula(level.getDimX() - 1, pos_y);
		}
	}
	
	public void isPossibleToAddExplosive() {
		if (Player.rand.nextDouble() <  level.getVampireFrecuency()) {
			int pos_y = Player.rand.nextInt(level.getDimY());
			if (isPossibleToAdd(level.getDimX() - 1, pos_y)) addExplosiveVampire(level.getDimX() - 1, pos_y);
		}
	}
	
	public void addVampiresIf() {	
		isPossibleToAddVampire();
		isPossibleToAddDracula();
		isPossibleToAddExplosive();
	}
	
	public void addVampire(int pos_x, int pos_y) {
		GameObject newVampire = new Vampire(pos_x, pos_y, this);
		gameBoard.addObject(newVampire);
	}
	
	public void addDracula(int pos_x, int pos_y) {
		GameObject dracula = new Dracula(pos_x, pos_y, this);
		gameBoard.addObject(dracula);
	}
	
	public void addExplosiveVampire(int pos_x, int pos_y) {
		GameObject newExplosive = new ExplosiveVampire(pos_x, pos_y, this);
		gameBoard.addObject(newExplosive);
	}
	
	public String serialize() {
		StringBuilder returnInfo = new StringBuilder();
		
		returnInfo.append("Cycles: " + cycles + "\n");
		returnInfo.append("Coins: " + player.getCoins() + "\n");
		returnInfo.append("Level: " +  level.name() + "\n");
		returnInfo.append("Remaining Vampires: " + Vampire.remaining + "\n");
		returnInfo.append("Vampires on Board: " + Vampire.onBoard + "\n");
		returnInfo.append("\nGame Object List: \n");
		returnInfo.append(gameBoard.getListInfo());
		
		return returnInfo.toString();
	}
	
	public boolean canPlayerBuy(int cost) { return player.getCoins() >= cost; }
	
	public boolean isPossibleToAdd(int pos_x, int pos_y) { return Vampire.remaining != 0 && isPositionValid(pos_x, pos_y); }
	
	public boolean isPositionValid(int pos_x, int pos_y) { return isPositionEmpty(pos_x, pos_y) && (pos_x >= 0 && pos_y >= 0) && pos_y < level.getDimY(); }
	
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
	
	@Override
	public String getPositionToString(int y, int x) { return gameBoard.getPositionToString(y, x); }
	
	@Override
	public String getInfo() { 
		StringBuilder returnInfo = new StringBuilder();
		
		returnInfo.append("Number of cycles: " + cycles + "\n");
		returnInfo.append("Coins: " + player.getCoins() + "\n");
		returnInfo.append("Remaining vampires: " + Vampire.remaining + "\n");
		returnInfo.append("Vampires on the board: " + Vampire.onBoard + "\n");
		if (Dracula.isAlive) returnInfo.append(Dracula.ALIVE + "\n");
		
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