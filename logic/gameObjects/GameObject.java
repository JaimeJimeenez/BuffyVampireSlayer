package logic.gameObjects;

import logic.Game;

abstract public class GameObject implements IAttack{

	public static final int HARM = 1;
	
	public GameObject() {
		
	}
    
	public int getHealth() { return health; } 
	
	protected boolean isAlive() { return health > 0; }
	
	protected void setHealth(int health) { this.health = health; }
	
	public void setPositions(int pos_x, int pos_y) { 
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	abstract public void attack();
	
	abstract protected void updatePosition();
	
	public boolean isInPosition(int pos_y, int pos_x) { return (this.pos_x == pos_x && this.pos_y == pos_y); } 
	
	protected int health;
	protected int pos_x;
	protected int pos_y;
	protected Game game;
}