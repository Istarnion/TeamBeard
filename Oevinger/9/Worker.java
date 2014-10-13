import java.util.GregorianCalendar;
import java.util.Calendar;

class Worker {

	private Person personalia;
	private int workerNumber;
	private int yearOfHire;
	private int salary;
	private float tax;

	public Worker(Person p, int n, int y, int s, float t) {
		personalia = p;
		workerNumber = n;
		yearOfHire = y;
		salary = s;
		tax = t;
	}

	public Worker(String fn, String ln, int yob, int n, int y, int s, float t) {
		personalia = new Person(fn, ln, yob);
		workerNumber = n;
		yearOfHire = y;
		salary = s;
		tax = t;
	}

	public void setYearOfHire(int yoh) {
		yearOfHire = yoh;
	}

	public void setSalary(int s) {
		salary = s;
	}

	public void setTax(float f) {
		tax = f;
	}

	public void setWorkerNumber(int n) {
		workerNumber = n;
	}

	public String getName() {
		return (personalia.getLastName()+", "+personalia.getFirstName());
	}

	// Should have been a method in Person, not Worker, but the assignment said it should be here.
	public int getAge() {
		int currYear = new GregorianCalendar().get(Calendar.YEAR);
		return currYear-personalia.getYearOfBirth();
	}

	public int getNumYearsHired() {
		int currYear = new GregorianCalendar().get(Calendar.YEAR);
		return currYear-yearOfHire;
	}

	public float getMonthlyTax() {
		return (float)salary*(tax/100);
	}

	public int getAnnualSalary() {
		return salary*12;
	}

	public float getAnnualTax() {
		return (float)salary*10.5f*(tax/100);
	}

	public boolean beenHiredForMoreYearsThan(int years) {
		if(getNumYearsHired() >= years) return true;
		return false;
	}

	@Override
	public String toString() {
		return ("Worker is "+personalia+". Worker nr. "+workerNumber+" Was hired in "+yearOfHire+".\nEarns "+salary+" pays "+tax+" tax");
	}

	//----TEST CLIENT----//
	public static void main(String[] args) {
		Worker w = new Worker("James", "Gosling", 1955, 3, 1994, 30000, 35.0f);
		System.out.println("\n"+w);

		System.out.println("Name: "+w.getName());
		System.out.println("Age: "+w.getAge());
		System.out.println("Been hired for "+w.getNumYearsHired()+" years");
		System.out.println("Monthly tax: "+w.getMonthlyTax());
		System.out.println("Annual salary: "+w.getAnnualSalary());
		System.out.println("Annual tax: "+w.getAnnualTax());
		System.out.println("Has "+w.getName()+" been hired for more than 20 years? "+(w.beenHiredForMoreYearsThan(20)?"Yes, he has" : "No he hasn't"));
	}
}