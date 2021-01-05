package logic;

import logic.gameObjects.*;
import logic.lists.GameObjectList;

public class GameObjectBoard {
	
	public GameObjectBoard() {
		objectList = new GameObjectList();
	}
	
	public void addObject(GameObject newObject) { objectList.addObject(newObject); }
	
	public void pushVampires() { objectList.pushVampires(); }
	
	public void eraseVampires() { objectList.eraseVampires(); }
	
	public void reset() { objectList.reset(); }
	
	public void update(Game game) {
		objectList.advance();
		objectList.attack();
		game.addVampiresIf(); 
		objectList.removeDeadObjects();
	}
	
	public String getListInfo() { return objectList.getListInfo(); }
	
	public boolean isPositionEmpty(int pos_x, int pos_y) { return objectList.isPositionEmpty(pos_x, pos_y); }
	
	public IAttack getAttackableInPosition(int pos_x, int pos_y) { return objectList.getAttackableInPosition(pos_x, pos_y); }
	
	public String getPositionToString(int pos_y, int pos_x) { return objectList.getPositionToString(pos_y, pos_x); }
	
	public boolean isFinished() { return objectList.isFinished(); }
	
	private GameObjectList objectList;
	
}
