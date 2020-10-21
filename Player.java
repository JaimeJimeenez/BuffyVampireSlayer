package characters;

import java.util.Random;

public class Player {
	
	public static final Random rand = new Random();
	
	public Player() {
		coins = 50;
	}
	
	public int getCoins() {
		return coins;
	}
	

	public void setCoins() {
		if (rand.nextBoolean()) coins += 10; 
	}

	private int coins;
}
