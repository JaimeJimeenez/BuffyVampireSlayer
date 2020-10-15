package characters;

public class Slayer {
	
	public Slayer() {
		behavior = false;
		cost = 50;
		health = 3;
		frequency = 1;
		damage = 1;
		range = 0;
	}
	
	public boolean getBehavior() {
		return behavior;
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
	
	public int getRange() {
		return range;
	}
	
	public String toString() {
		return "S[" + getHealth() + "]";
	}
	
	private boolean behavior;
	private int cost;
	private int health;
	private int frequency;
	private int damage;
	private int range;
}
