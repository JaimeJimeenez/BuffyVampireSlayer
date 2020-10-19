package characters;

import logic.Game;

public class Slayer {
	
	public Slayer(int dim_x, int dim_y) {
		behavior = false;
		cost = 50;
		health = 3;
		damage = 1;
		setDim_x(dim_x);
		setDim_y(dim_y);
		position = -1;
	}
	
	public boolean getBehavior() {
		return behavior;
	}
	
	public void setBehavior(boolean behavior) {
		this.behavior = behavior;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getDim_x() {
		return dim_x;
	}
	
	public void setDim_x(int dim_x) {
		if (dim_x < 0) System.out.println("Error: Position of x out of range.");
		else this.dim_x = dim_x;
	}
	
	public int getDim_y() {
		return dim_y;
	}
	
	public void setDim_y(int dim_y) {
		if (dim_y < 0) System.out.println("Error: Position of y out of range.");
		else this.dim_y = dim_y;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition() {
		int columns = 0;
		
		if(game.getLevel().name() == "EASY") columns = 7;
		if (game.getLevel().name() == "HARD") columns = 6;
		if (game.getLevel().name() == "INSANE") columns = 4;
		
		this.position = dim_x * columns + dim_y;
	}
	
	public String toString() {
		return "S[" + getHealth() + "]";
	}
	
	private boolean behavior;
	private int cost;
	private int health;
	private int damage;
	private int dim_x, dim_y;
	private int position;
	
	//Reference game
	private Game game;
}
