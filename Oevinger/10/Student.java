class Student {

	private String name;
	private int numTasks;

	public Student(String name) {
		this.name = name;
		numTasks = 0;
	}

	public String getName() {
		return name;
	}

	public int getNumTasks() {
		return numTasks;
	}

	public void incNumTasks(int n) {
		numTasks+=n;
	}

	@Override
	public String toString() {
		return "Student: "+name+". Tasks delivered: "+numTasks;
	}

	//----TEST CLIENT----//
	public static void main(String[] args) {
		Student s = new Student("Darth Vader");
		System.out.println(s);

		s.incNumTasks(7);
		System.out.println(s);

		s.incNumTasks(-7);
		System.out.println(s);		
	}
}
