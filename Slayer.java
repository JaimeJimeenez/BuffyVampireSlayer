package characters;

public class Slayer {
	
	public Slayer(int dim_x, int dim_y) {
		
		cost = 50;
		health = 3;
		frequency = 1;
		damage = 1;
		this.dim_x = dim_x;
		this.dim_y = dim_y;
	
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getDim_x() {
		return dim_x;
	}
	
	public int getDim_y() {
		return dim_y;
	}
	
	public boolean isInPosition(int dim_x, int dim_y) {
		return (this.dim_x == dim_x && this.dim_y == dim_y);
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public String toString() {
		return "S [" + getHealth() + "]";
	}
	
	int cost;
	int health;
	int frequency;
	int damage;
	int dim_x, dim_y;
	
}
