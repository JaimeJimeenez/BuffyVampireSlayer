package characters;

import logic.Level;

public class Vampire {
	
	public Vampire(Level level) {
		health = 5;
		damage = 1;
		dim_x = level.getDim_x() - 1;
		dim_y = Player.rand.nextInt(level.getDim_y());
		cycles = 0;
	}
	
	public int getHealth() { return health; }
	
	public int getDamage() { return damage; }
	
	public int getDim_x() { return dim_x; }
	
	public int getDim_y() { return dim_y; }
	
	public int getCycles() { return cycles; }
	
	public void updateHealth() { health--; }
	
	public void updateCycles() { cycles++; }
	
	public void updateDim_x() { dim_x--; }
	
	public boolean isInPosition(int dim_x, int dim_y) { return (this.dim_x == dim_x && this.dim_y == dim_y); }
	
	public String toString() { return "V[ " + health + " ] ";}
	
	//Attributes
	private int health;
	private int damage;
	private int dim_x;
	private int dim_y;
	private int cycles;
}
