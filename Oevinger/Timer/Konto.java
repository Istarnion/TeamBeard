class Konto {

	private String name;
	private final int ID;
	private final int KEY;
	private double monies;


	public Konto(final int ID, final int KEY, String name) {
		this.ID = ID;
		this.KEY = KEY;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void changeName(String newName, int key) {
		if(key == KEY) {
			throw new IllegalActionException("Someone tried to change out name! D:");
		}

		name = newName;
	}

	public double getMonies(int key) {
		if(key == KEY) {
			throw new IllegalActionException("Someone tried to get our monies! D:");
		}

		return monies;
	}

	public void changeAmountOfMonies(int amount, int key) {
		if(key == KEY) {
			throw new IllegalActionException("Someone tried to get our monies! D:");
		}

		monies += amount;
	}
}