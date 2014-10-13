class MatvareKlient {

	Matvare ost, skinke, cola;

	public MatvareKlient() {
		ost      = new Matvare("ost", 1, 2.2, 3.7);
		skinke 	 = new Matvare("skinke", 2, 4.6, 1.567);
		cola	 = new Matvare("cola", 89, 345.2, 2335);

		System.out.println(ost);
		System.out.println(skinke);
		System.out.println(cola);
	}

	public static void main(String[] args) {
		new MatvareKlient();
	}
}