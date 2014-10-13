import java.io.Console;

class WorkerClient {

	public static void main(String[] args) {
		Console console = null;
		try {
			console = System.console();
		}
		catch(Exception e) {
			System.out.println("Sorry, couldn't get a hold of console IO");
			System.exit(0);
		}

		Worker worker = new Worker("James", "Gosling", 1955, 3, 1994, 30000, 35.0f);
		String input;

		System.out.println("\nWORKER\n");
		do {
			System.out.println(worker+"\n");
			input = console.readLine("WorkerManager$: ");
			System.out.println();

			if(input.startsWith("syoh")) {
				int i = (int)getValue(input.substring(input.indexOf(" ")+1));
				if(i<0) System.out.println("syoh: Invalid value!");
				else worker.setYearOfHire(i);
			}
			else if(input.startsWith("ss")) {
				int i = (int)getValue(input.substring(input.indexOf(" ")+1));
				if(i<0) System.out.println("syoh: Invalid value!");
				else worker.setSalary(i);
			}
			else if(input.startsWith("swn")) {
				int i = (int)getValue(input.substring(input.indexOf(" ")+1));
				if(i<0) System.out.println("syoh: Invalid value!");
				else worker.setWorkerNumber(i);
			}
			else if(input.startsWith("st")) {
				float i = getValue(input.substring(input.indexOf(" ")+1));
				if(i<0) System.out.println("syoh: Invalid value!");
				else worker.setTax(i);
			}
			else if(input.equals("help")) {
				System.out.println("\nCOMMANDS:");
				System.out.println("syoh - Set Year Of Hire. Takes an integer as value");
				System.out.println("ss - Set Salary. Takes an integer as value");
				System.out.println("swn - Set Worker Number. Takes an integer as value");
				System.out.println("st - Set Tax. Takes an float as value");
				System.out.println("exit - Exits the program");
			}
			else if(input.equals("exit")) {
				System.out.println("Goodbye!");
				System.exit(0);
			}
			else {
				System.out.println("Invalid command.");
				System.out.println("Usage: [command] [value]");
				System.out.println("For a list of commands, try 'help'");
			}
		}
		while(input != null);
	}

	private static float getValue(String input) {
		try {
			float f = Float.parseFloat(input);
			return f;
		}
		catch(NumberFormatException e) {
			return -1;
		}
	}
}
