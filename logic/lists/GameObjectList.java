package logic.lists;

import logic.*;
import logic.gameObjects.*;
import java.util.ArrayList;

public class GameObjectList {

	public GameObjectList() {
		objects = new ArrayList <GameObject> ();
	}
	
	public void addObject(GameObject newObject) { objects.add(newObject); }
	
	public void pushVampires() {
		for (IAttack elem : objects) 
			elem.receiveGarlicPush();
	}
	
	public void eraseVampires() {
		for (IAttack elem : objects) 
			elem.receiveLightFlash();
	}
	
	public void reset() { objects.clear(); }
	
	public void advance() {
		for (GameObject elem : objects)
			elem.updatePosition();
	}
	
	public void attack() {
		for (IAttack elem : objects) 
			elem.attack();
	}
	
	public void removeDeadObjects() {
		ArrayList <GameObject> returnArray = new ArrayList<GameObject>();
		
		for (GameObject elem : objects)
			if (elem.isAlive()) returnArray.add(elem);
		
		objects = returnArray;
	}
	
	public String getListInfo() {
		StringBuilder returnInfo = new StringBuilder();
		
		for (GameObject elem : objects) 
			returnInfo.append(elem.getInfo() + "\n");
		
		return returnInfo.toString();
	}
	
	public boolean isFinished() { return Vampire.isFinished(); }
	
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
	
	public String getPositionToString(int pos_x, int pos_y) {
		
		for (GameObject elem : objects) 
			if (elem.isInPosition(pos_y, pos_x)) return elem.toString();
		
		return "";
	}
	
	private ArrayList <GameObject> objects;

}
