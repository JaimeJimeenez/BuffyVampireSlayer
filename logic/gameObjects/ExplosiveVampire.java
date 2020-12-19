package logic.gameObjects;

import logic.Game;

public class ExplosiveVampire extends Vampire {
	
	public final String SYMBOL = "EV";
	public final int EXPLOSION = 1;
	public final int HEALTH = 5;
	
	public ExplosiveVampire(int pos_x, int pos_y, Game game) {
		super(pos_x, pos_y, game);
		
		symbol = SYMBOL;
		health = HEALTH;
		cycles = 0;
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		super.receiveSlayerAttack(damage);
		if (!isAlive()) explosion();
		return true;
	}
	
	public void explosion() {
		
		for (int i = pos_y - 1; i < pos_y - 2; i++) {
			for (int j = pos_x - 1; j < pos_x - 2; j++) {
				IAttack other = game.getAttackableInPosition(i, j);
				
				if (other != null) other.receiveSlayerAttack(EXPLOSION);
			}
		}
		
	}
	
}
