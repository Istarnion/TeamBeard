class MultTable {

	public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("\nYou need to input two integers! Try again.");
			System.exit(0);
		}

		int start = 0;
		int end = 0;
		try {
			start = Integer.parseInt(args[0]);
			end = Integer.parseInt(args[1]);
		}
		catch(Exception e) {
			System.out.println("\nYou did not input two valid numbers! Try again!");
			System.exit(0);
		}

		for(int i=start; i<=end; i++) {
			if(i == 0) continue;
			System.out.println("\n"+i+" MULTIPLICATION TABLE:");
			System.out.println("-----------------------");
			for(int n=1; n<=10; n++) {
				System.out.println(i+" * "+n+" = "+(i*n));
			}

		}

	}

}
