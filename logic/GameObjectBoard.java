package logic;

import logic.gameObjects.*;

public class GameObjectBoard {
	
	public GameObjectBoard(Level level) {
		objectList = new GameObjectList(level);
	}
	
	public void addSlayer(int pos_x, int pos_y, Game game) { objectList.addSlayer(pos_x, pos_y, game); }
	
	public void reset() { objectList.reset(); }
	
	public void update(Level level, Game game) {
		advance();
		attack();
		if (Player.rand.nextDouble() < level.getVampireFrecuency()) addVampire(level, game);
	}
	
	public void advance() { objectList.advance(); }
	
	public void attack() { objectList.attack(); }
	
	public void addVampire(Level level, Game game) { objectList.addVampire(level, game); }
	
	public void removeDeadObjects() { objectList.removeDeadObjects(); }
	
	//Additional methods
	
	public boolean isPositionEmpty(int pos_x, int pos_y) { return objectList.isPositionEmpty(pos_x, pos_y); }
	
	public IAttack getAttackableInPosition(int pos_x, int pos_y) { return objectList.getAttackableInPosition(pos_x, pos_y); }
	
	public boolean isFinished() { return objectList.isFinished(); }
	
	public String getPositionToString(int y, int x) { return objectList.getPositionToString(y, x); }
	
	private GameObjectList objectList;
	
}
