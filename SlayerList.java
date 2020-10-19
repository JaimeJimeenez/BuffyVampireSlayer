package characters;

public class SlayerList {
	
	public SlayerList() {
		
	}
	
	public boolean checkDeaths() {
		
		for (Slayer elem : data) 
			if (elem.getHealth() != 0) return false;  
		
		return true;
	}
	
	private Slayer[] data;
}
