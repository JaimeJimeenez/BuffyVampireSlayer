package characters;

import java.util.Random;

public class Player {
	
	public static final Random rand = new Random();

	public Player() { coins = 50; }
	
	public int getCoins() { return coins; }
	
	public void boughtSlayer() { coins -= 50; }
	
	public void updateCoins() { if (rand.nextFloat() >= 0.5) coins += 10; }
	
	private int coins;
	
}
