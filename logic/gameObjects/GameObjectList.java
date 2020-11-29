package logic.gameObjects;

import logic.*;

import java.util.ArrayList;

public class GameObjectList {

	public GameObjectList(Level level) {
		objects = new ArrayList <GameObject> ();
		Vampire.remaining = level.getNumberOfVampires();
	}
	
	public void addSlayer(int pos_x, int pos_y, Game game) {
		GameObject newSlayer = new Slayer();
		
		newSlayer.setGame(game);
		newSlayer.setPositions(pos_x, pos_y);
		objects.add(newSlayer);
		
	}
	
	public void reset() { objects.clear(); }
	
	public void advance() {
		for (GameObject elem : objects) elem.updatePosition();
	}
	
	public void attack() {
		for (IAttack elem : objects) elem.attack();
	}
	
	public void addVampire(Level level, Game game) {
		int pos_x = level.getDimX() - 1;
		int pos_y = Player.rand.nextInt(level.getDimY());
		
		if (Vampire.remaining != 0) {
			GameObject newVampire = new Vampire();
			newVampire.setGame(game);
			
			if (game.isPositionEmpty(pos_x, pos_y)) {
				newVampire.setPositions(pos_x, pos_y);
				objects.add(newVampire);
				
				Vampire.remaining--;
				Vampire.onBoard++;
			}
		}
		
	}
	
	public void removeDeadObjects() {
		ArrayList <GameObject> returnArray = new ArrayList<GameObject>();
		
		for (GameObject elem : objects)
			if (elem.isAlive()) returnArray.add(elem);
		
		objects = returnArray;
	}
	
	public boolean isFinished() { return ( (Vampire.onBoard == 0 && Vampire.remaining == 0) || Vampire.hasArrived == true); }
	
	//Additional methods
	
	public boolean isPositionEmpty(int pos_x, int pos_y) { 
		
		for (GameObject elem : objects) 
			if (elem.isInPosition(pos_y, pos_x)) return false;
		
		return true;
	}
	
	public IAttack getAttackableInPosition(int pos_x, int pos_y) {
		
		for (GameObject elem: objects)
			if (elem.isInPosition(pos_y, pos_x)) return elem;
		
		return null;
	}
	
	public String getPositionToString(int x, int y) {
		
		for (GameObject elem : objects)
			if (elem.isInPosition(y, x)) return elem.toString();
		
		return "  ";
	}
	
	private ArrayList <GameObject> objects;

}
