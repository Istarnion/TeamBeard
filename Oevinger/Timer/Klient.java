import java.io.*;

class Klient {
	
	public static void main(String[] args) throws Exception {

		Console c = System.console();
		String input;
		do {
			System.out.println("\nAWESOME BANK SYSTEM");
			System.out.println("--------------------\n");
			System.out.println("Adjust monies: 1\nShow monies: 2\nQuit:    0");

			input = c.readLine("$ ");
		}
		while(!input.isEmpty());
	}
}