import java.io.*;
import java.util.Arrays;

class TextAnalyzer {

	private int[] letters;

	public TextAnalyzer() throws IOException {
		Console c = System.console();

		String input = c.readLine("Please input a string: ");
		while(!input.isEmpty()) {
			populate(input);

			System.out.println("\nYou inputted "+numLetters()+" letters.");
			System.out.println("The most frequent letter (or last) was: "+getMostFrquent());

			String s = c.readLine("Is there any letter you'd like to know how often you wrote? Input: ");
			while(!s.isEmpty()) {
				System.out.println("The letter "+s.charAt(0)+" came up "+numInstances(s.charAt(0))+" times.");
				s = c.readLine("Another letter? ");
			}

			input = c.readLine("Please input a string: ");
		}

		System.out.println("Goodbye!");
	}

	private void populate(String s) {
		letters = new int[30];

		char c;
		int code;
		for(int i=0; i<s.length(); i++) {
			c = s.charAt(i);
			code = c;
			if      (code == 230 || code == 198)                          letters[26]++;	// Æ or æ
			else if (code == 248 || code == 216)                          letters[27]++;	// Ø or ø
			else if (code == 229 || code == 197)                          letters[28]++;	// Å or å
			else if (code < 65 || (code > 90 && code < 97) || code > 122) letters[29]++;	// non-letter
			else {
				if(code <= 90) {	// Capital letters A-Z
					letters[code-65]++;
				}
				else {				// lower case letters a-z
					letters[code-97]++;
				}
			}
		}
	}

	public char getMostFrquent() {
		if(letters == null) return 0;

		int index = 0;
		for(int i=0; i<29; i++) {
			if(letters[i]>letters[index]) index = i;
		}

		return (char)(65+index);
	}

	public int numInstances(char c) {
		if(letters == null) return -1;

		int code = c;

		if      (code == 230 || code == 198)                          return letters[26];	// Æ or æ
		else if (code == 248 || code == 216)                          return letters[27];	// Ø or ø
		else if (code == 229 || code == 197)                          return letters[28];	// Å or å
		else if (code < 65 || (code > 90 && code < 97) || code > 122) return letters[29];	// non-letter
		else {
			if(code <= 90) {	// Capital letters A-Z
				return letters[code-65];
			}
			else {				// lower case letters a-z
				return letters[code-97];
			}
		}
	}

	public int numDifferentLetters() {
		if(letters == null) return -1;

		int a=0;
		for(int i=0; i<29; i++) {
			if(letters[i]>0) a++;
		}
		return a;
	}

	public int numLetters() {
		if(letters == null) return -1;

		int a=0;
		for(int i=0; i<29; i++) {
			a += letters[i];
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
		new TextAnalyzer();
	}
}
