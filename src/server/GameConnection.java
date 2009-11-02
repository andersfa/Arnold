package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class GameConnection implements Runnable{

	private Socket socket;
	private BufferedReader in;
	private DataOutputStream out;
	private int x, y;
	private String playerName;
	
	public GameConnection(String playerName, Socket socket, BufferedReader in, DataOutputStream out){
		this.playerName = playerName;
		this.socket = socket;
		this.in = in;
		this.out = out;
	}
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public String getName() {
		return playerName;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Succesfully joined the gameserver");
		while(true){
			
		}
	}

}
