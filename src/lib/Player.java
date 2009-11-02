package lib;

import lib.board.Board;

public class Player {

	private String name;
	private Board board;
	private int points;
	private int[] position;

	public Player(String name) {
		this.name = name;
		points = 0;
		position = new int[2];
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public int[] getPosition() {
		return position;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void incrementPoints(int value) {
		points += value;
	}

	public void deductPoints(int value) {
		points -= value;
	}

	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}

	public void move(String direction) {
		System.out.println("Not implemented");
	}
	
	public void shoot() {
		
	}

	@Override
	public String toString() {
		return name + "[" + points + "]";
	}

}
