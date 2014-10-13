import java.io.*;

class Prime {

	public static void main(String[] args) throws IOException {
			Console c = System.console();

			String s = c.readLine("\nPlease input a number: ");
			while (!s.isEmpty()) {
				int n = 0;
				try {
					n = Integer.parseInt(s);

					if(isPrime(n)) {
						System.out.println("The number you inputed, "+n+", IS a prime number!");
					}
					else {
						System.out.println("The number you inputed, "+n+", is NOT a prime number");
					}
				}
				catch(Exception e) {
					System.out.println("That's not a valid number.");
				}

				s = c.readLine("\nPlease input a number: ");
			}
			System.out.println("\n Goodbye!");
	}

	private static boolean isPrime(int n) {
		if(n <= 1) return false;
		if(n == 2) return true;
		for(int i=2; i<n; i++) {

			if(n%i == 0) return false;

		}
		return true;
	}

}
