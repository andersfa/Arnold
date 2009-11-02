package client;

import java.util.Scanner;

public class Command {

	private String input;
	private Scanner scan;

	public Command() {
		input = "";
		scan = new Scanner(System.in);
	}

	public void enterCommand() {
		while (!input.equals("j") || !input.equals("c")) {
			System.out.println("Enter c to create or j to join");
			input = scan.nextLine();
		}
	}

}
