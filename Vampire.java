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
	
	public int setDim_x(Level level) {
		if (level.name() == "EASY") return 7;
		if (level.name() == "HARD") return 6;
		if (level.name() == "INSANE") return 4;
		else return -1;
	}
	
	public int setDim_y(Level level) {
		if (level.name() == "EASY") return rand.nextInt(4);
		if (level.name() == "HARD") return rand.nextInt(3);
		if (level.name() == "INSANE") return rand.nextInt(5);
		else return - 1;
	}
	
	public String toString() {
		return "V[ " + getHealth() + " ]";
	}
	
	int health;
	int damage;
	int speed;
	int dim_x, dim_y;
}
