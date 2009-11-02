package server;

public class Server {

	public static void main(String[] args) {
		LoginServer login = new LoginServer(16);
		login.run();
	}

}
