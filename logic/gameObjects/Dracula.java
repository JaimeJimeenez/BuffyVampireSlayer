package logic.gameObjects;

import logic.Game;

public class Dracula extends Vampire {
	
	public final static String ALIVE =  "Dracula is alive";
	public static boolean isAlive;
	public final int HEALTH = 5;
	public final String SYMBOL = "D";
	
	public Dracula(int pos_x, int pos_y, Game game) {
		super(pos_x, pos_y, game);
		
		symbol = SYMBOL;
		health = HEALTH;
		cycles = 0;
		isAlive = true;
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		super.receiveSlayerAttack(damage);
		if (!isAlive()) isAlive = false;
		return true;
	}
	
	@Override
	public boolean receiveGarlicPush() {
		super.receiveGarlicPush();
		if(!isAlive()) isAlive = false;
		return true;
	}
	
	@Override
	public boolean receiveLightFlash() {
		return true;
	}
	
	@Override
	public void attack() {
		
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(pos_x - 1, pos_y);
			
			if (other != null) other.receiveDraculaFlash();
		}
	}
	
}
