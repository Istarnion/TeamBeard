class Person {

	private String firstName;
	private String lastName;
	private int yearOfBirth;

	public Person(String fn, String ln, int yob) {
		firstName = fn;
		lastName = ln;
		yearOfBirth = yob;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	@Override
	public String toString() {
		return ("Person "+firstName+" "+lastName+", born "+yearOfBirth);
	}
}
