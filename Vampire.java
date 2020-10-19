package characters;

import logic.Game;
import java.util.Random;

public class Vampire {
	
	public static final Random rand = new Random();
	
	public Vampire() {
		behavior = false;
		health = 5;
		damage = 1;
		speed = 1;
		setDim_x();
		setDim_y();
		setFrequency();
		position = -1;
	}
	
	public boolean getBheavior() {
		return behavior;
	}
	
	public void setBehavior(boolean behavior) {
		this.behavior = behavior;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setDim_x() {
		if (game.getLevel().name() == "EASY") this.dim_x = 7;
		if (game.getLevel().name() == "HARD") this.dim_x = 6;
		if (game.getLevel().name() == "INSANE") this.dim_x = 4;
	}
	
	public int getDim_x() {
		return dim_x;
	}
	
	public void setDim_y() {
		if (game.getLevel().name() == "EASY") this.dim_y = rand.nextInt(5);
		if (game.getLevel().name() == "HARD") this.dim_y = rand.nextInt(4);
		if (game.getLevel().name() == "INSANE") this.dim_y = rand.nextInt(7);
	}
	
	public int getDim_y() {
		return dim_y;
	}
	
	public int getPosition() {
		int column = 0;
		
		if (game.getLevel().name() == "EASY") column = 7;
		if (game.getLevel().name() == "HARD") column = 6;
		if (game.getLevel().name() == "INSANE") column = 4;
		
		position = dim_x * column + dim_y;
		return position;
	}
	
	public double getFrequency() {
		return frequency;
	}
	
	public void setFrequency() {
		if (game.getLevel().name() == "EASY") this.frequency = 0.1;
		if (game.getLevel().name() == "HARD") this.frequency = 0.2;
		if (game.getLevel().name() == "INSANE") this.frequency = 0.3;
	}
	
	public String toString() {
		return "V[" + getHealth() + "]";
	}
	
	private boolean behavior;
	private int health;
	private int damage;
	private int speed;
	private int dim_x, dim_y;
	private double frequency;
	private int position;
	
	//Reference to game
	private Game game;
}
