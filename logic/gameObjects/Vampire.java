package logic.gameObjects;

public class Vampire extends GameObject{
	
	public static int onBoard;
	public static int remaining;
	public static boolean hasArrived;
	
	public Vampire() {
		setHealth(5);
		setGame(game);
		this.cycles = 0;
	}
	
	public static void initVampireData() {
		onBoard = 0;
		hasArrived = false;
	}
	
	public boolean checkCycles() { return (cycles % 2 == 0 && cycles != 0); }
	
	@Override
	protected void updatePosition() {
		
		cycles++;
		if (game.isPositionEmpty(pos_x - 1, pos_y) && checkCycles()) pos_x--;
		if (pos_x == -1) Vampire.hasArrived = true;
		
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) { 
		health -= damage;
		if (health <= 0) {
			game.removeDeadObjects();
			Vampire.onBoard--;
		}
		return true; 	
	}
	
	@Override
	public void attack() {
		
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(pos_x - 1, pos_y);
			
			if (other != null) {
				other.receiveVampireAttack(HARM);
				cycles--;
			}
		}
		
	}
	
	public String toString() { return "V [" + health + "]"; }
	
	private int cycles;
}
