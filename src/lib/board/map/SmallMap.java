package lib.board.map;

public class SmallMap extends Map {

	public SmallMap() {
		super();
	}

	@Override
	public String[][] setupLevel() {
		setSize(9, 8);
		String[][] map = {
				{"#", "#", "#", "#", "#", "#", "#", "#", "#"},
				{"#", "w", "w", "w", "w", "w", "w", "w", "#"},
				{"#", "w", "w", "w", "w", "w", "w", "w", "#"},
				{"#", "w", "w", "#", "#", "#", "w", "w", "#"},
				{"#", "w", "w", "#", "#", "#", "w", "w", "#"},
				{"#", "w", "w", "w", "w", "w", "w", "w", "#"},
				{"#", "w", "w", "w", "w", "w", "w", "w", "#"},
				{"#", "#", "#", "#", "#", "#", "#", "#", "#"}
		};
		return map;
	}

}
