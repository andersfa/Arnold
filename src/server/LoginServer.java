package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import lib.Player;



public class LoginServer implements Runnable {

	private ServerSocket serverSocket;
	private ArrayList<String> log;
	
	private GameServer gameServer;
	
	public LoginServer(int amountOfPlayers){
		this.log = new ArrayList<String>();
		this.gameServer = new GameServer(amountOfPlayers);
	}
	
	@Override
	public void run() {
		try {
			print("Server start at " + GregorianCalendar.getInstance().getTime().toString() + ". Waiting for connections.");
			serverSocket = new ServerSocket(8000);
			while (true) {
				Handshake next = new Handshake(serverSocket.accept());
				new Thread(next).start();
				print("Connection accepted from " + next.getSocket().getInetAddress() + ", sending to handshake");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void print(String msg){
		System.out.println(msg);
		log.add(msg);
	}
	
	private class Handshake implements Runnable{
		
		private Socket socket;
		private BufferedReader in;
		private DataOutputStream out;
		
		public Handshake(Socket socket){
			this.socket = socket;
		}
		
		public Socket getSocket(){
			return socket;
		}
		
		public void setupStreams() throws IOException{
			out = new DataOutputStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		
		public boolean handshake(){
			try {
				//Opsæt streams
				setupStreams();
				
				
				//name request sendes
				out.writeBytes("NR\n");
				out.flush();
				print("Name request sent to " + socket.getInetAddress());
				String userName = null;
				//kører så længe man ikke har fået en brugbart brugernavn
				while(userName == null){
					String[] read = in.readLine().split(" ");
					if(read.length > 0 && read[0].equals("N")){
						GameConnection[] players = gameServer.getgConnections();
						boolean found = false;
						for(int i = 0; i < players.length; i++){
							if(players[i] != null){
								if(players[i].getName().equals(read[1]))
									found = true;
							}
						}
						if(found){
							//navn optaget, requester ny
							out.writeBytes("ND\n");
							out.flush();
							print("Denied user name " + read[1] + " from " + socket.getInetAddress());
						}
						else{
							//sætter navn, ryger herved ud af løkken.
							userName = read[1];
							print("Accepted user name " + read[1] + " from " + socket.getInetAddress());
						}
					}
					else if(read.length > 0 && read[0].equals("DC\n")){
						//Termination from client
						// TODO
					}
					else{
						out.writeBytes("DC\n");
						print("Disconnected " + socket.getInetAddress() + " from Server");
					}
				}
				//ved dette punkt er brugernavn kendt
				gameServer.addGameConnection(new GameConnection(userName, socket, in, out));
				out.writeBytes("NC\n");
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
		
		@Override
		public void run() {
			print("Attempting to handshake with: " + socket.getInetAddress());
			handshake();
		}
	}
}
