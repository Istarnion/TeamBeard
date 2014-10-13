final class NewString {

	private final String s;

	public NewString(String s) {
		this.s = s;
	}

	public NewString(NewString ns) {
		this.s = ns.getLegacyString();
	}

	public String removeChar(char c) {
		char[] ca = s.toCharArray();

		String output = "";
		for(char l : ca) {
			if(l != c) {
				output += l;
			}
		}

		return output;
	}

	public String shorten() {
		String[] sa = s.split(" ");
		String output = "";
		for(String s : sa) {
			output += s.charAt(0);
		}

		return output;
	}

	public String getLegacyString() {
		return s;
	}

	// For testing
	public static void main(String[] args) {
		NewString nuString;
		if(args.length == 0) {
			nuString = new NewString("Tæøå");
		}
		else {
			String input = "";
			for(String in : args) {
				input += (in+" ");
			}
			input.trim();
			nuString = new NewString(input);
		}

		System.out.println("Original: "+nuString.getLegacyString());
		System.out.println("Shortened: "+nuString.shorten());
		System.out.println("Removed 'e' : "+nuString.removeChar('e'));
	}
}
