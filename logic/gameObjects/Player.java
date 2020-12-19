package logic.gameObjects;

import logic.Game;

import java.util.Random;

public class Player {

	public static final String ENOUGH_COINS = "[ERROR]: Not enough coins";
	public static final Random rand = new Random(Game.getSeed());
	public static final int VALUE_LIGHT = 50;
	public static final int VALUE_GARLIC = 10;
	public final int INITIAL_COINS = 50;
	public final int SUPER_COINS = 1000;
	
	public Player() {
		this.coins = INITIAL_COINS;
	}
	
	public int getCoins() { return coins; }
	
	public void addCoins() { coins += SUPER_COINS; }
	
	public void bought(int cost) { coins -= cost; }
	
	public void resetCoins() { coins = INITIAL_COINS; }
	
	public void updateCoins() { if (Player.rand.nextFloat() >= 0.5) coins += 10; }
	
	public void addBankCoins(int coins) { this.coins += (coins * 0.1); }
	
	private int coins;
}
