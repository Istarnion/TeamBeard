class Fraction {

	private int num;
	private int denom;

	public Fraction(int numerator) {
		this.num = numerator;
		denom = 1;
	}

	public Fraction(int numerator, int denominator) {
		if(denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero!");
		}
		this.num = numerator;
		this.denom = denominator;
	}

	public int getNum() {
		return num;
	}

	public int getDenom() {
		return denom;
	}

	public Fraction add(int i) {
		return add(new Fraction(i));
	}

	public Fraction add(Fraction f) {
		if(denom == f.getDenom()) {
			num += f.getNum();
		}
		else {
			num = (num*f.getDenom() + f.getNum()*denom);
			denom *= f.getDenom(); 
		}

		shorten();

		return this;
	}

	public Fraction subtract(int i) {
		return subtract(new Fraction(i));
	}

	public Fraction subtract(Fraction f) {
		if(denom == f.getDenom()) {
			num -= f.getNum();
		}
		else {
			num = (num*f.getDenom() - f.getNum()*denom);
			denom *= f.getDenom(); 
		}

		shorten();

		return this;
	}

	public Fraction multiply(int i) {
		num *= i;

		shorten();

		return this;
	}

	public Fraction multiply(Fraction f) {
		num *= f.getNum();
		denom *= f.getDenom();

		shorten();

		return this;
	}

	public double getDecimal() {
		return (num/denom);
	}

	private void shorten() {
		for(int n=Math.abs(Math.abs(num)>Math.abs(denom) ? num : denom); n>0; n--) {
			if(n==0) continue;

			if(num % n == 0 && denom % n == 0) {
				num /= n;
				denom /= n;
			}
		}
	}

	@Override
	public String toString() {
		return ("("+num+"/"+denom+")");
	}

	public static void fractionTester() {
		Fraction f = new Fraction(5);
		Fraction f1 = new Fraction(2, 6);
		Fraction f2 = new Fraction(1, 2);
		Fraction f3 = new Fraction(-32, 64);

		System.out.println(f+" + "+f1+" = "+(f.add(f1))+"    Expected: (16/3)");
		System.out.println(f1+" - "+f2+" = "+(f1.subtract(f2))+"    Expected: (-1/6)");
		System.out.println(f2+" * "+f3+" = "+(f2.multiply(f3))+" Expected: (-1/4)");
	}

	public static void main(String[] args) {
		fractionTester();
	}

}
