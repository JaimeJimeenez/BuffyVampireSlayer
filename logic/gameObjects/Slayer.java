package logic.gameObjects;

public class Slayer extends GameObject {
	
	public Slayer() {
		setHealth(3);
		setGame(game);
	}
	
	@Override
	protected void updatePosition() {
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {
		health -= damage;
		if (health <= 0) game.removeDeadObjects();
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
	
	public String toString() { return "S ["+ health + "]"; } 
	
}
