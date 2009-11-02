package lib.board;

import lib.Player;
import lib.board.map.Map;

public class Board {

	private Map map;

	public Board(Map map, int amountOfPlayers) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < map.getSize()[1]; i++) {
			if (i != 0) {
				s += "\n";
			}
			for (int j = 0; j < map.getSize()[0]; j++) {
				s += map.getLevel()[i][j] + " ";
			}
		}
		return s;
	}

}
