package characters;

public class SlayerList {

	public SlayerList() {
		data = new Slayer[32]; //Provisional
	}
	
	public Slayer[] getData() {
		return data;
	}
	
	public void setData(Slayer[] data) {
		this.data = data;
	}
	
	public Slayer[] removeSlayer(int i) {
		System.arraycopy(data, i + 1, data[i], i, data.length - 1 - i);
		return data;
	}
	
	private Slayer[] data;
}
