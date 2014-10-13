class Absence {

	private int[] studentAbsence;

	public Absence(int numStudents) {
		if(numStudents < 0) {
			throw new IllegalArgumentException("having less than one student in a class is folly!");
		}

		studentAbsence = new int[numStudents];
	}

	public int getAbsenceOfStudent(int student) {
		try {
			return studentAbsence[student-1];
		}
		catch(Exception e) {
			return -1;
		}
	}

	public float getAverageAbsence() {
		if(studentAbsence.length == 0) return 0;

		float a = 0;
		for(int i : studentAbsence) {
			a += i;
		}

		return (a/studentAbsence.length);
	}

	public int getNumStudents() {
		return studentAbsence.length;
	}

	public int numStudentsWithAbsence() {
		int a=0;

		for(int i : studentAbsence) {
			if(i>0) a++;
		}

		return a;
	}

	public int numStudentsWithoutAbsence() {
		int a=0;

		for(int i : studentAbsence) {
			if(i==0) a++;
		}

		return a;
	}

	public int adjustAbsence(int studNr, int amount) {
		try {
			studentAbsence[studNr-1]+=amount;
			return studentAbsence[studNr-1];
		}
		catch(Exception e) {
			return -1;
		}

	}

	public static void main(String[] args) {
		// Test 1:
		Absence a = new Absence(0);
		test(a);

		// test 2:
		a = new Absence(1);
		a.adjustAbsence(1, 5);
		test(a);

		// Test 3:
		a = new Absence(5);
		test(a);

		// Test 4:
		a.adjustAbsence(1, 5);
		a.adjustAbsence(3, 2);
		a.adjustAbsence(5, 1);
		test(a);

		// Test 5:
		a.adjustAbsence(1, 2);
		a.adjustAbsence(2, 1);
		a.adjustAbsence(3, 4);
		a.adjustAbsence(4, 2);
		a.adjustAbsence(5, 1);
		test(a);

	}

	private static void test(Absence a) {
		System.out.println(
			"\nNUM STUDENTS                           : "+a.getNumStudents()+
			"\nABSENCE OF STUDENT 1                   : "+a.getAbsenceOfStudent(1)+
			"\nAVERAGE ABSENCE                        : "+a.getAverageAbsence()+
			"\nNUM STUDENTS WITHOUT ABSENCE           : "+a.numStudentsWithoutAbsence()+
			"\nADJUSTING ABSENCE ON STUDENT 7 UP BY 3 : "+a.adjustAbsence(7, 3));
	}
}
