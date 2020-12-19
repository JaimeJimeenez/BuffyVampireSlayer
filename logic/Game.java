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
	
	public boolean addSlayer(int pos_x, int pos_y) {
		
		if (checkPosition(pos_x, pos_y) && pos_x < level.getDimX() - 1) {
			if (player.getCoins() >= Slayer.COST) {
				GameObject newSlayer = new Slayer(pos_x, pos_y, this);
				
				gameBoard.addObject(newSlayer);
				player.bought(Slayer.COST);
				return true;
			}
			
			System.out.println(Player.ENOUGH_COINS);
			return false;
		}
		
		System.out.println(Controller.invalidPositionMsg);
		return false;
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
	
	public boolean pushVampires() { 
		
		if (player.getCoins() >= Player.VALUE_GARLIC) {
			gameBoard.pushVampires();
			player.bought(Player.VALUE_GARLIC);
			return true;
		}
		
		System.out.println(Player.ENOUGH_COINS);
		return false;
	}
	
	public boolean eraseVampires() {
		
		if (player.getCoins() >= Player.VALUE_LIGHT) {
			gameBoard.eraseVampires();
			player.bought(Player.VALUE_LIGHT);
			return true;
		}
		
		System.out.println(Player.ENOUGH_COINS);
		return false;
	}
	
	public boolean addBloodBank(int pos_x, int pos_y, int imputCoins) {
		
		if (checkPosition(pos_x, pos_y) && pos_x < level.getDimX() - 1) {
			if (player.getCoins() >= imputCoins) {
				GameObject newBank = new BankBlood(pos_x, pos_y, imputCoins, this, player);
				gameBoard.addObject(newBank);
				player.bought(imputCoins);
				return true;
			}
			
			System.out.println(Player.ENOUGH_COINS);
			return false;
		}
		
		System.out.println(Controller.invalidPositionMsg);
		return false;
	}
	
	public void addCoins() { player.addCoins(); }
	
	//Vampires added by random
	public void addVampire() {
		
		if(Player.rand.nextDouble() < level.getVampireFrecuency()) {
			if (Vampire.remaining != 0) {
				int pos_x = level.getDimX() - 1;
				int pos_y = Player.rand.nextInt(level.getDimY());
				
				if (checkPosition(pos_x, pos_y)) {
					GameObject newVampire = new Vampire(pos_x, pos_y, this);
					gameBoard.addObject(newVampire);
				}
			}
		}
	}
	
	public void addDracula() {
		
		if(Player.rand.nextDouble() < level.getVampireFrecuency()) {
			if (Vampire.remaining != 0 && !Dracula.isAlive) {
				int pos_x = level.getDimX() - 1;
				int pos_y = Player.rand.nextInt(level.getDimY());
				
				GameObject dracula = new Dracula(pos_x, pos_y, this);
				gameBoard.addObject(dracula);
			}	
		}
	}
	
	public void addExplosiveVampire() {
		
		if (Player.rand.nextDouble() < level.getVampireFrecuency()) {
			if (Vampire.remaining != 0) {
				int pos_x = level.getDimX() - 1;
				int pos_y = Player.rand.nextInt(level.getDimY());
				
				if (checkPosition(pos_x, pos_y)) {
					GameObject newExplosive = new ExplosiveVampire(pos_x, pos_y, this);
					gameBoard.addObject(newExplosive);
				}
			}
		}
	}
	
	public void addRandomVampires() {
		addVampire();
		addDracula();
		addExplosiveVampire();
	}
	
	//Vampires added by the user
	public boolean addDracula(int pos_x, int pos_y) {
		
		if (checkPosition(pos_x, pos_y) && pos_x < level.getDimX()) {
			if (Vampire.remaining != 0) {
				if (!Dracula.isAlive) {
					GameObject dracula = new Dracula(pos_x, pos_y, this);
					gameBoard.addObject(dracula);
					return true;
				}
				
				System.out.println("[ERROR]: "  + Dracula.ALIVE);
				return false;
			}
			
			System.out.println(Vampire.noRemaining);
			return false;
		}
		
		System.out.println(Controller.invalidPositionMsg);
		return false;
	}
	
	public boolean addExplosiveVampire(int pos_x, int pos_y) {
		
		if (checkPosition(pos_x, pos_y) && pos_x < level.getDimX()) {
			if (Vampire.remaining != 0) {
				GameObject newExplosive = new ExplosiveVampire(pos_x, pos_y, this);
				gameBoard.addObject(newExplosive);
				return true;
			}
			
			System.out.println(Vampire.noRemaining);
			return false;
		}
		
		System.out.println(Controller.invalidPositionMsg);
		return false;
	}
	
	public boolean addVampire(int pos_x, int pos_y) {
		
		if (checkPosition(pos_x, pos_y) && pos_x < level.getDimX()) {
			if (Vampire.remaining != 0) {
				GameObject newVampire = new Vampire(pos_x, pos_y, this);
				gameBoard.addObject(newVampire);
				return true;
			}
			
			System.out.println(Vampire.noRemaining);
			return false;
		}
		
		System.out.println(Controller.invalidPositionMsg);
		return false;
	}
	
	public boolean checkPosition(int pos_x, int pos_y) { return isPositionEmpty(pos_x, pos_y) && (pos_x >= 0 && pos_y >= 0) && pos_y < level.getDimY(); }
	
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