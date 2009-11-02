package lib.board.map;

public abstract class Map {

	private String[][] level;
	private int[] size;

	public Map() {
		size = new int[2];
		level = setupLevel();
	}

	public int[] getSize() {
		return size;
	}

	public String[][] getLevel() {
		return level;
	}

	public void setSize(int x, int y) {
		size[0] = x;
		size[1] = y;
	}

	public abstract String[][] setupLevel();

}
