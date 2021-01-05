package logic.gameObjects;

import logic.Level;
import logic.Game;

public class Vampire extends GameObject{
	
	public static int onBoard;
	public static int remaining;
	public static boolean hasArrived;
	public final int HEALTH = 5;
	public final String SYMBOL = "V";
	
	
	public static final String noRemaining = "[ERROR]: No more remaining vampires left";
	
	public Vampire(int pos_x, int pos_y, Game game) {
		super(pos_x, pos_y, game);
		
		symbol = SYMBOL;
		health = HEALTH;
		cycles = 2;
		updateVampireData();
	}
	
	public static boolean isFinished() { return (onBoard == 0 && remaining == 0) || hasArrived == true; }
	
	public static void initVampireData(Level level) {
		onBoard = 0;
		remaining = level.getNumberOfVampires();
		hasArrived = false;
		Dracula.isAlive = false;
	}
	
	public static void updateVampireData() {
		onBoard++;
		remaining--;
	}
	
	@Override
	public void updatePosition() {
		
		cycles--;
		if (game.isPositionEmpty(pos_x - 1, pos_y) && cycles <= 0) {
			pos_x--;
			cycles = 2;
		}
		if (pos_x == -1) Vampire.hasArrived = true;
		
	}
	
	@Override
	public boolean receiveGarlicPush() {
		
		pos_x++;
		if (pos_x == game.getDim_X()) {
			setHealth(0);
			Vampire.onBoard--;
		}
		
		return true;
	}
	
	@Override
	public boolean receiveLightFlash() {
		setHealth(0);
		Vampire.onBoard--;
		return true;
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) { 
		health -= damage;
		if (!isAlive()) Vampire.onBoard--;
		return true; 	
	}
	
	@Override
	public void attack() {
		
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(pos_x - 1, pos_y);
			
			if (other != null) other.receiveVampireAttack(HARM);
		}
		
	}
	
	@Override
	public String getInfo() { return symbol + ";" + pos_x + ";" +  pos_y + ";" +  health + ";" +  cycles; }
	
	protected int cycles;
}
