
public class WordCountPair implements Comparable<WordCountPair> {
	public String word;
	public int count;
	
	public WordCountPair(String s, int count) {
		this.word = s;
		this.count = count;
	}

	@Override
	public int compareTo(WordCountPair wcp) {
		return this.count - wcp.count;
	}
}
