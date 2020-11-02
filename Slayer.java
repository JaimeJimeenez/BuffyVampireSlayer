package characters;

public class Slayer {

	public Slayer(int dim_x, int dim_y) {
		
		this.cost = 50;
		this.health = 3;
		this.damage = 1;
		this.dim_x = dim_x;
		this.dim_y = dim_y;
	}
	
	public int getCost() { return cost; }
	
	public int getHealth() { return health; }
	
	public int getDamage() { return damage; }
	
	public int getDim_x() { return dim_x; }
	
	public int getDim_y() { return dim_y; }
	
	public void updateHealth() { health--; }
	
	public boolean isInPosition(int dim_x, int dim_y) { return (this.dim_x == dim_x && this.dim_y == dim_y); }
	
	public String toString() { return "S[ "+ health + " ]"; }
	
	
	//Attributes
	private int cost;
	private int health;
	private int damage;
	private int dim_x;
	private int dim_y;
	
}
