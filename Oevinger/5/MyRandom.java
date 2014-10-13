import java.util.Random;

class MyRandom {

	private Random rnd;

	public MyRandom() {
		rnd = new Random();
	}

	public int nextInt(int lowInclude, int highInclude) {
		return (lowInclude+rnd.nextInt((highInclude-lowInclude+1)));
	}

	public double nextDouble(double lowEx, double highEx) {
		return ((lowEx+Double.MIN_VALUE)+rnd.nextDouble()*(highEx-lowEx));
	}


	// Testing
	public static void main(String[] args) {
		MyRandom random = new MyRandom();

		// INT
		System.out.println("\nTesting nextInt(), interval [456, 490]:");

		boolean b = true;

		int h = 460;
		int l = 460;

		for(int i=0; i<200000; i++) {
			int n = random.nextInt(456, 490);
			if(n > 490 || n < 456) {
				b = false;
				break;
			}

			if(n>h) h = n;
			else if(n<l) l = n;
		}

		if(b) {
			if(h == 490 && l == 456) System.out.println("Success!");
			else System.out.println("We kept within the limits, but never reached end values.");
		}
		else {
			System.out.println("Failed! We broke the limits.");
		}

		// DOUBLE
		System.out.println("\nTesting nextDouble(), interval <238, 250>:");

		b = true;

		for(int i=0; i<200000; i++) {
			double n = random.nextDouble(238, 250);
			if(n >= 250 || n <= 238) {
				b = false;
				if(n >= 250) System.out.println("Break high!");
				if(n <= 238) System.out.println("Break low!");
				break;
			}
		}

		if(b) {
			System.out.println("Success!");
		}
		else {
			System.out.println("Failed! We broke the limits.");
		}
	}
}
