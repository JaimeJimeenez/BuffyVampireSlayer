package characters;

public class Vampire {
	
	public Vampire() {
		behavior = false;
		health = 5;
		damage = 1;
		speed = 1;
	}
	
	
	public boolean getBehavior() {
		return behavior;
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
	
	public String toString() {
		return "V[" + getHealth() + "]";
	}
	
	private boolean behavior;
	private int health;
	private int damage;
	private int speed;
}
