class Valuta {

	private double krValue;
	private String name;
	private String code;

	public Valuta(String name, String code, double krValue) {
		this.name = name;
		this.krValue = krValue;
		this.code = code;
		if(krValue == 0) {
			System.out.println("krValue should not be zero!");
		}
	}

	public double getKrValue() {
		return krValue;
	}

	public void setKrValue(double krValue) {
		this.krValue = krValue;
	}

	public double toKr(double amount) {
		return amount*krValue;
	}

	public double toVal(double krAmount) {
		return krAmount/krValue;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return name;
	}

}
