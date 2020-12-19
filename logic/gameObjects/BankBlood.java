package logic.gameObjects;

import logic.Game;

public class BankBlood extends Slayer {
	
	public final int HEALTH = 3;
	
	public BankBlood(int pos_x, int pos_y, int imputCoins, Game game, Player player) {
		super(pos_x, pos_y, game);
		
		health = HEALTH;
		this.player = player;
		this.imputCoins = imputCoins;
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {
		setHealth(0);
		return true;
	}
	
	@Override
	protected void updatePosition() { player.addBankCoins(imputCoins); }

	public String toString() { return "B [" + imputCoins + "]"; }
	
	private int imputCoins;
	private Player player;
}
