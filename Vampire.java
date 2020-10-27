package characters;

import java.util.Random;
import logic.Level;

public class Vampire {
	
	public static final Random rand = new Random();
	
	public Vampire(Level level) {
		health = 5;
		damage = 1;
		speed = 1;
		this.dim_x = setDim_x(level);
		this.dim_y = setDim_y(level);
		cycles = 0;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getDim_x() {
		return dim_x;
	}
	
	public int getDim_y() {
		return dim_y;
	}
	
	public int getCycles() {
		return cycles;
	}
	
	public void setCycles() {
		cycles++;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int setDim_x(Level level) {
		return level.getDim_x() - 1;
	}
	
	public int setDim_y(Level level) {
		return rand.nextInt(level.getDim_y());
	}
	
	public boolean isInPosition(int dim_x, int dim_y) {
		return (this.dim_x == dim_x && this.dim_y == dim_y);
	}
	
	public void updateDim_x(int dim_x) {
		this.dim_x = dim_x;
	}
	
	public void updateDim_y(int dim_y) {
		this.dim_y = dim_y;
	}
	
	public String toString() {
		return "V[" + getHealth() + "]";
	}
	
	int health;
	int damage;
	int speed;
	int dim_x, dim_y;
	int cycles;
}
