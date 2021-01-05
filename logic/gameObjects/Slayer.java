package logic.gameObjects;

import logic.Game;

public class Slayer extends GameObject {
	
	public final static int COST = 50;
	public final String SYMBOL = "S";
	public final int HEALTH = 3;
	
	public Slayer(int pos_x, int pos_y, Game game) {
		super(pos_x, pos_y, game);
		
		symbol = SYMBOL;
		health = HEALTH;
	}
	
	@Override
	public void updatePosition() {
	}
	
	@Override
	public boolean receiveDraculaFlash() {
		setHealth(0);
		return true;
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {
		health -= damage;
		return true;
	}
	
	@Override
	public void attack() {
		
		if (isAlive()) {
			for (int i = pos_x + 1; i < game.getDim_X(); i++) {
				IAttack other = game.getAttackableInPosition(i, pos_y);
				
				if (other != null) {
					other.receiveSlayerAttack(HARM);
					break;
				}
			}
		}
		
	}
	
	public String getInfo() { return symbol + ";" + pos_x + ";" +  pos_y + ";" + health; }
}
