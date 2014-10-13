class Matvare {

	private final double carb;
	private final double fat;
	private final double energy;

	private String name;

	public Matvare(String name, double carb, double fat, double energy) {
		this.name = name;
		this.carb = carb;
		this.fat = fat;
		this.energy = energy;
	}

	public double getCarbs() {
		return carb;
	}

	public double getFat() {
		return fat;
	}

	public double getEnergy() {
		return energy;
	}

	@Override
	public String toString() {
		return ("["+name+"]: carbs: "+carb+", fat: "+fat+", energy: "+energy);
	}
}