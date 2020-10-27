package characters;

public class VampireList {

	public VampireList(int numberOfVampires) {
		data = new Vampire[numberOfVampires];
	}
	
	public Vampire[] getData() {
		return data;
	}
	
	public void setData(Vampire[] data) {
		this.data = data;
	}
	
	public Vampire[] removeVampire(int i) {
		System.arraycopy(data, i + 1, data[i], i, data.length - 1 - i);
		return data;
	}
	
	private Vampire[] data;
}
