package logic.gameObjects;

import logic.Game;

import java.util.Random;

public class Player {

	public static final Random rand = new Random(Game.getSeed());
	public static final int VALUE_SLAYER = 50;
	public final int INITIAL_COINS = 50;
	
	public Player() {
		this.coins = INITIAL_COINS;
	}
	
	public int getCoins() { return coins; }
	
	public void boughtSlayer() { this.coins -= VALUE_SLAYER; }
	
	public void resetCoins() { this.coins = INITIAL_COINS; }
	
	public void updateCoins() { if (rand.nextFloat() > 0.5) coins += 10; }
	
	private int coins;
}
