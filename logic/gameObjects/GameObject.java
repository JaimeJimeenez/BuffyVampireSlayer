package logic.gameObjects;

import logic.Game;

abstract public class GameObject implements IAttack{

	public static final int HARM = 1;
	
	public GameObject(int pos_x, int pos_y, Game game) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.game = game;
	}
	
	protected boolean isAlive() { return health > 0; }
	
	public int getPos_X() { return pos_x; }
	
	public int getPos_Y() { return pos_y; }
	
	protected void setHealth(int health) { this.health = health; }
	
	abstract public void attack();
	
	abstract protected void updatePosition();
	
	public boolean isInPosition(int pos_y, int pos_x) { return (this.pos_x == pos_x && this.pos_y == pos_y); } 
	
	public String toString() { return symbol + " [" + health + "]"; }
	
	protected String symbol;
	protected int health;
	protected int pos_x;
	protected int pos_y;
	protected Game game;
}