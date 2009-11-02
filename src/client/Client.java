package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {

	private Socket socket;

	private BufferedReader in;
	private DataOutputStream out;

	public Client() {}

	private void connect(String host, int port) {
		try {
			socket = new Socket(host, port);
			System.out.println("Connected to " + host);
		} catch (UnknownHostException e) {
			System.out.println("Host does not exist");
		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}
	
	private void setupStreams() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void terminate(String error) {
		try {
			out.writeBytes("CT\n");
			out.flush();
			System.out.println(error);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void name() {
		try {
			String name;
			Scanner scan = new Scanner(System.in);
			if (in.readLine().equals("NR")) {
				System.out.println("Type in desired username: ");
				name = scan.nextLine();
				out.writeBytes("N " + name + "\n");
				out.flush();
				while (!in.readLine().equals("NC")) {
					System.out.println("Name taken. Type in new username: ");
					name = scan.nextLine();
					out.writeBytes("N " + name + "\n");
					out.flush();
				}
				System.out.println("User created with name: " + name);
			}
			else {
				terminate("Protocol mismatch with server");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		connect("localhost", 8000);
		setupStreams();
		name();
		while (true) {

		}
	}

	public static void main(String[] args) {
		Client c = new Client();
		new Thread(c).start();
	}

}
