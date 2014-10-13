class TextEditor {

	private String s;

	public TextEditor(String s) {
		this.s = s;
	}

	public float averageWordLength() {
		int counter = 0;
		float totalLength = 0;
		for(String word : s.split("[\\p{Blank} \\t\\n\\r\\n\\s]")) {
			if(!word.isEmpty()) {
				counter++;
				totalLength += word.length();
			}
		}
		if(counter < 1) return -1;
		return (totalLength/counter);
	}

	public float averageWordsInSentence() {
		int numSentences = 0;
		int totalLength = 0;
		for(String sentences : s.split("[\\p{P}]")) {
			numSentences++;
			totalLength += sentences.split("[\\p{Blank} \\t\\n\\r\\n\\s]").length;
		}

		if(numSentences < 1) return -1;
		return ((float)totalLength)/((float)numSentences);
	}

	public String getText() {
		return s;
	}

	public String getTextToUpperCase() {
		return s.toUpperCase();
	}

	public int numWords() {
		return s.split("[\\p{Blank} \\t\\n\\r\\n]").length;
	}

	public String replace(String toReplace, String with) {
		s = s.replaceAll(toReplace, with);
		return s;
	}
}
