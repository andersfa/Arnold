package server;

import java.net.Socket;
import java.util.ArrayList;

import lib.board.Board;
import lib.board.map.SmallMap;


public class GameServer {

	private Board board;
	private GameConnection[] gConnections;
	
	public GameServer(int amountOfPlayers){
		this.board = new Board(new SmallMap(), amountOfPlayers); 
		this.gConnections = new GameConnection[amountOfPlayers];
	}

	public Board getBoard() {
		return board;
	}
	
	public GameConnection[] getgConnections() {
		return gConnections;
	}

	public boolean addGameConnection(GameConnection connection){
		for (int i = 0; i < gConnections.length; i++) {
			if (gConnections[i] == null) {
				gConnections[i] = connection;
				new Thread(connection).start();
				return true;
			}
		}
		return false;
	}
}
